package com.ab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.model.CityMasterEntity;

public interface ICityRepo extends JpaRepository<CityMasterEntity, Integer>{
	
	public List<CityMasterEntity> findBystateId(int stateId);

}
