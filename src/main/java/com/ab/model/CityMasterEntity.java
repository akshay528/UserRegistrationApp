package com.ab.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "Cities_Table")
@Data
public class CityMasterEntity {

	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;
	
}
