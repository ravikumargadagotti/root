package com.nonprofit.charity.nonprofit.logics;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.nonprofit.charity.nonprofit.databaseoperations.UserCredentialsService;
import com.nonprofit.charity.nonprofit.entity.UserCredentials;
import com.nonprofit.charity.nonprofit.interfaces.InsertNewUser;
@ManagedBean
public class CreateNewUser implements InsertNewUser {

	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Override
	public boolean insertNewUser(UserCredentials userCredentials) {
		boolean result=inserting(userCredentials);
		return result;
	}
	
	private boolean inserting(UserCredentials userCredentials) {
		if(userCredentialsService.addNewRow(userCredentials)) {
			return true;
		}
		return false;
	}

}
