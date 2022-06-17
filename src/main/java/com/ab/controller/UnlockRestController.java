package com.ab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.bindings.UnlockForm;
import com.ab.service.IUserMgtService;

@RestController
@RequestMapping("/unlock")
public class UnlockRestController {

	//inject service
	@Autowired
	private IUserMgtService service;
	
	@PostMapping("unlockUser")
	private String unlockUser(@RequestBody UnlockForm unlockForm) {
		return service.unlockUser(unlockForm);
	} 
	
}
