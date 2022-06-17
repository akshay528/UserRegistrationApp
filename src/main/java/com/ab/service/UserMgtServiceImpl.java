package com.ab.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.bindings.LoginForm;
import com.ab.bindings.UnlockForm;
import com.ab.bindings.UserForm;
import com.ab.emailUtil.EmailUtil;
import com.ab.model.CityMasterEntity;
import com.ab.model.CountryMasterEntity;
import com.ab.model.StateMasterEntity;
import com.ab.model.UserAccountEntity;
import com.ab.repo.ICityRepo;
import com.ab.repo.ICountryRepo;
import com.ab.repo.IStateRepo;
import com.ab.repo.IUserAccountRepo;

@Service
public class UserMgtServiceImpl implements IUserMgtService {

	//inject the repositories 
	@Autowired
	private IUserAccountRepo userRepo;
	@Autowired
	private ICountryRepo countryRepo;
	@Autowired
	private IStateRepo stateRepo;
	@Autowired
	private ICityRepo cityRepo;
	@Autowired
	private EmailUtil emailUtil;


	@Override
	public String login(LoginForm loginForm) {
		//use user repo
		UserAccountEntity entity = userRepo.findByuserEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());

		if(entity==null) {
			return "Invalid Crediantials , No Such User Found with mail id "+loginForm.getEmail();
		}

		if(entity != null && entity.getAccStatus().equals("LOCKED")) {
			return "Currently your Account was Locked";
		}

		return "==>>Login Success<<==";
	}

	@Override
	public String emailCheck(String email) {
		UserAccountEntity entity= userRepo.findByuserEmail(email);

		if(entity== null) {
			return "No such user Found with Given Email Id";
		}

		return "User Already registered with given Mail Id";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> listCountries= countryRepo.findAll();

		//to convert List to map
		Map<Integer, String> countriesMap = new HashMap<Integer, String>();

		//using foreach java 8, converting list to map
		listCountries.forEach(countries ->{
			countriesMap.put(countries.getCountryId(), countries.getCountryName());
		});

		return countriesMap;
	}

	@Override
	public Map<Integer, String> loadStates(int countryId) {
		List<StateMasterEntity> listStates= stateRepo.findBycountryId(countryId);

		//to convert List to map
		Map<Integer, String> stateMap = new HashMap<Integer, String>();

		//using foreach java 8, converting list to map
		listStates.forEach(states ->{
			stateMap.put(states.getStateId(),states.getStateName());
		});

		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(int stateId) {

		List<CityMasterEntity> listCities= cityRepo.findBystateId(stateId);


		//to convert List to map
		Map<Integer, String> cityMap = new HashMap<Integer, String>();

		//using foreach java 8, converting list to map
		listCities.forEach(cities ->{
			cityMap.put(cities.getCityId(),cities.getCityName());
		});

		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {

		//crete user account class object to copy props from user form
		UserAccountEntity entity = new UserAccountEntity();

		// copy userform data to entity
		BeanUtils.copyProperties(userForm, entity);
		//set default status for user active
		entity.setAccStatus("LOCKED");

		//generate random password and save it in db
		entity.setPassword(generateRandomPassword());

		//ssave entity object 
		 UserAccountEntity savedEntity= userRepo.save(entity);
		

		//TO DO EMAIL CODE
		String email = userForm.getUserEmail();
		String subject= "Welcome To Cognizant";
		String fileName= "MailBody.txt";
		String body = readMailBodyContent(fileName, entity);
		
		boolean isSent = emailUtil.sendEmail(email, subject, body);
		if(savedEntity!=null && isSent) {
			return "User Registered Successfully ";
		}
		
		return "Failed to Register User";
		
	}

	@Override
	public String unlockUser(UnlockForm unlockForm) {

		UserAccountEntity entity = userRepo.findByuserEmailAndPassword(unlockForm.getEmail(), unlockForm.getTempPwd());

		if(entity==null) {
			return "Incorrect Temporory Password";
		}

		if(!(unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd()))) {
			return "Password does not match";
		}
		
		entity.setPassword(unlockForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		
		userRepo.save(entity);

		return "Account Unlocked Sucessfully";
	}

	@Override
	public String forgetPassword(String email) {
		UserAccountEntity entity= userRepo.findByuserEmail(email);
		
		if(entity==null) {
			return "Invalid Email Id";
		}
		
		//TO DO SENT PASSWORD TO USER MAIL
		//EMAIL SENDING 
		String fileName="RP.txt";
		String subject="Recover Password - Cognizant ";
		String body = readMailBodyContent(fileName, entity);
				
		boolean isSent = emailUtil.sendEmail(email, subject, body);
		
		if(isSent) {
			return "Password Sent to registered Mail id";
		}
		
		return "Failed to send Mail";
	}


	//Method to generate random password 
	public String generateRandomPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	public String readMailBodyContent(String fileName, UserAccountEntity entity) {
		
		String mailBody="";
		try {
			StringBuilder sb= new StringBuilder();
			//FileReader fr = new FileReader(null)
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			
			while(line!= null) {
				sb.append(line);
				br.readLine();
			}
			
			mailBody=sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFname());
			mailBody = mailBody.replace("{LNAME}", entity.getLname());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getUserEmail());
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return mailBody;
	}

}
