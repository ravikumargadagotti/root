package com.nonprofit.charity.nonprofit.databaseoperations;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nonprofit.charity.nonprofit.entity.UserCredentials;

@Service
@ManagedBean
public class UserCredentialsService {
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
public List<UserCredentials> getAllValues(String userName) {
		
		List<UserCredentials> users = new ArrayList<>();
		userCredentialsRepository.findByUserName(userName)
		.forEach(users :: add);
		
		return users;
	}
	
	public boolean addNewRow(UserCredentials userCredentials) {
		if(userCredentialsRepository.save(userCredentials) != null) {
			return true;
		}
		return false;
	}
	
	public UserCredentials getOneUser(Integer Id) {
		
		return UserCredentials.findOne(Id);
		
	}
	
	public List<UserCredentials> getOneUser(String userName) {
		return userCredentialsRepository.findByUserName(userName);
	}

}
