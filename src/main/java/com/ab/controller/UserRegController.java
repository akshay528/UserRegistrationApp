package com.ab.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.bindings.UserForm;
import com.ab.service.IUserMgtService;

@RestController
@RequestMapping("user")
public class UserRegController {

	//inject service
	@Autowired
	private IUserMgtService service;
	
	@GetMapping("email/{emailid}")
	public String emailCheck(@PathVariable String emailid) {
		return service.emailCheck(emailid);
	}
	
		
	@GetMapping("contries")
	public Map<Integer, String> getContries(){
		return service.loadCountries();
	}
	
	@GetMapping("states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId ){
		return service.loadStates(countryId);
	}
	
	@GetMapping("cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId ){
		return service.loadCities(stateId);
	}
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserForm userForm) {
		return service.registerUser(userForm);
	}
	
	
}
