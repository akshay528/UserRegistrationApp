package com.ab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.bindings.LoginForm;
import com.ab.service.IUserMgtService;

@RestController
@RequestMapping("userlogin")
public class LoginController {

	//inject the service
	@Autowired
	private IUserMgtService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.login(loginForm);
	}
	
	
}
