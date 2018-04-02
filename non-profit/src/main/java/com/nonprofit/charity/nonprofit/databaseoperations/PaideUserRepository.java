package com.nonprofit.charity.nonprofit.databaseoperations;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nonprofit.charity.nonprofit.entity.PaideUser;

@Repository
public interface PaideUserRepository extends CrudRepository<PaideUser, Integer>{
	
	public List<PaideUser> findByemail(String email);

}
