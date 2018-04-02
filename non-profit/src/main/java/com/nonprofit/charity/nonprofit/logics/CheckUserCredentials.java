package com.nonprofit.charity.nonprofit.logics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import com.nonprofit.charity.nonprofit.databaseoperations.UserCredentialsRepository;
import com.nonprofit.charity.nonprofit.entity.UserCredentials;
import com.nonprofit.charity.nonprofit.interfaces.LoginCheck;

@ManagedBean
public class CheckUserCredentials implements LoginCheck{
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	@Override
	public boolean loginCheck(String userName, String password) {
		boolean result = false;
		try {
		if(userName.isEmpty()||userName==null||userName==""||password.isEmpty()||password==null||password=="") {
			return false;
		}else {
			result = checkUsernamePassword(userName,password);
		}
		}catch(NullPointerException nu) {
			return false;
		}
		
		
		
		return result;
	}

	private boolean checkUsernamePassword(String userName, String password) {
		List<UserCredentials> users = new ArrayList<>();
		userCredentialsRepository.findByUserName(userName).forEach(users :: add);
		for(UserCredentials use : users) {
			if(userName.equals(use.getUserName())&&password.equals(use.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
		
		return false;
	}

}
