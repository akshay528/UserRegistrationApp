package com.ab.service;

import java.util.Map;

import com.ab.bindings.LoginForm;
import com.ab.bindings.UnlockForm;
import com.ab.bindings.UserForm;

public interface IUserMgtService {

	//to check login credintails
	public String login(LoginForm loginForm);
	
	//for registration functionalities method
	public String emailCheck(String email);
	
	public Map<Integer,String> loadCountries();

	public Map<Integer,String> loadStates(int countryId);

	public Map<Integer,String> loadCities(int stateId);
	
	//for registering new user
	public String registerUser(UserForm userForm);

	//to unlock user
	public String unlockUser(UnlockForm unlockForm);

	
	public String forgetPassword(String email);
	
	
	
}
