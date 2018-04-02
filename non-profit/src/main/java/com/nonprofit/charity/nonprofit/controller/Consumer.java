package com.nonprofit.charity.nonprofit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nonprofit.charity.nonprofit.databaseoperations.PaideUserService;
import com.nonprofit.charity.nonprofit.entity.PaideUser;

@RestController
public class Consumer {

	@Autowired
	private PaideUserService paideUserService;
	
	
	
	
	@RequestMapping("/consumer")
	public List<PaideUser> getAllUsers(@RequestParam("email") String email){
		return paideUserService.getOneUser(email);
	}
	
	
	@PostMapping(value="/consumer")
	public void somethingNew(@Valid PaideUser user) {

		System.out.println("This is FirstName: "+user);
		paideUserService.addNewRow(user);
		System.out.println("Success");

	}
	
}
