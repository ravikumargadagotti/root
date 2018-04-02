package com.nonprofit.charity.nonprofit.databaseoperations;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.nonprofit.charity.nonprofit.entity.PaideUser;

@Service
@ManagedBean
public class PaideUserService {

	@Autowired
	private PaideUserRepository paideUserRepository;
	
	public List<PaideUser> getAllValues(String email) {
		
		List<PaideUser> users = new ArrayList<>();
		paideUserRepository.findByemail(email)
		.forEach(users :: add);
		
		return users;
	}
	
	public void addNewRow(PaideUser user) {
		paideUserRepository.save(user);
	}
	
	public PaideUser getOneUser(Integer Id) {
		
		return paideUserRepository.findOne(Id);
		
	}
	
	public List<PaideUser> getOneUser(String email) {
		return paideUserRepository.findByemail(email);
	}
}
