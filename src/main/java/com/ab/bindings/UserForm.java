package com.ab.bindings;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.Data;

@Data
public class UserForm {

	
	private String fname;
	
	private String  lname;
	
	private String  userEmail;
	
	
	private Long phno;
	
	private LocalDate dob;
	
	private String  gender;
	
	private Integer  cityId;
	
	private Integer  stateId;
	
	private Integer countryId;
	
}
