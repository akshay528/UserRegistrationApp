package com.ab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.model.CountryMasterEntity;

public interface ICountryRepo extends JpaRepository<CountryMasterEntity, Integer>{

}
