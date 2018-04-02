package com.nonprofit.charity.nonprofit.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.nonprofit.charity.nonprofit.entity.UserCredentials;
import com.nonprofit.charity.nonprofit.interfaces.InsertNewUser;
import com.nonprofit.charity.nonprofit.logics.CheckUserCredentials;

@Controller
public class UserController {
	
	Logger logg = Logger.getLogger(UserController.class);
	@Autowired
	private CheckUserCredentials checkUserCredentials;
	@Autowired
	private InsertNewUser insertNewUser;
	
	@RequestMapping(method=RequestMethod.POST,value="userCredentials")
	public String insertUserName(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("samePassword") String samePassword,@ModelAttribute UserCredentials userCredentials, HttpServletRequest request) {
		
		logg.info("Create new user "+userName);
		
		if(password.equals(samePassword)) {
			try {
				insertNewUser.insertNewUser(userCredentials);
			}catch(NullPointerException nl) {
				logg.error("Null pointer exception", nl);
				nl.printStackTrace();
			}
			return "mainMenu.jsp";
		}else {
			return "indexLogin.html";
		}
		
		
		//request.setAttribute("name", "This is ravi");
		//return "redierect.jsp";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="userLogin")
	public String userLogin(@RequestParam("loginUserName") String userName,@RequestParam("loginPassword") String password, HttpServletRequest request) {
		logg.info("Check user name and password "+userName);
		boolean result=checkUserCredentials.loginCheck(userName, password);
		if(result) {
			
			return "mainMenu.jsp";
		}else {
		return "indexLogin.jsp";
		}
	}

}
