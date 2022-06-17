package com.ab.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "Country_Table")
@Data
public class CountryMasterEntity {

	@Id
	private Integer countryId;
	private String countryName;
}
