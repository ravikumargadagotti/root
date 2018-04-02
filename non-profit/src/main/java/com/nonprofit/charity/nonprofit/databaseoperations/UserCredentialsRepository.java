package com.nonprofit.charity.nonprofit.databaseoperations;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nonprofit.charity.nonprofit.entity.UserCredentials;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Integer>{
	
	public List<UserCredentials> findByUserName(@Param("userName") String userName);

}
