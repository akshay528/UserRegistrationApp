package com.ab.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "States_Table")
@Data
public class StateMasterEntity {

	@Id
	private Integer stateId;
	private String stateName;
	private Integer countryId;
	
}
