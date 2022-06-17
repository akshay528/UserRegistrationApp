package com.ab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.model.UserAccountEntity;

public interface IUserAccountRepo extends JpaRepository<UserAccountEntity, Integer> {

	//to find email and password for user login
	public UserAccountEntity findByuserEmailAndPassword(String email, String pwd);
	
	//to find whether user exist with given emai id or not
	public UserAccountEntity findByuserEmail(String email);
	
}
