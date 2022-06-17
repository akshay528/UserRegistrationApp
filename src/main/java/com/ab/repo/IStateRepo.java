package com.ab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.model.StateMasterEntity;

public interface IStateRepo extends JpaRepository<StateMasterEntity, Integer>{

	public List<StateMasterEntity> findBycountryId(int countryId);
	
}
