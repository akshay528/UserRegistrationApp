package com.ab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.service.IUserMgtService;

@RestController
@RequestMapping("/forgot")
public class forgotPwdRestController {

	//inject service
	@Autowired
	private IUserMgtService service;
	
	@GetMapping("/forgotPwd/{email}")
	public String forgotPwd(@PathVariable String email) {
		return service.forgetPassword(email);
	}
	
}
