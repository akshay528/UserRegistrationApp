package com.ab.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name= "UserAccount_Table")
@Data
public class UserAccountEntity {
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Column(name = "First_Name")
	private String fname;
	
	@Column(name = "Last_Name")
	private String  lname;
	
	@Column(name = "Email", unique = true)
	private String  userEmail;
	
	@Column(name = "Password")
	private String  password;
	
	@Column(name = "User_Mobile")
	private Long phno;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "Gender")
	private String  gender;
	
	@Column(name = "City_Id")
	private Integer  cityId;
	
	@Column(name = "State_Id")
	private Integer  stateId;
	
	@Column(name = "Country_Id")
	private Integer countryId;
	
	@Column(name = "Acc_Status")
	private String accStatus;
	
	@Column(name = "Created_Date" ,updatable = true)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "Updatated_Date" ,insertable = true)
	private LocalDate updatedDate;
}
