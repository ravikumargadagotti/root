package com.nonprofit.charity.nonprofit.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EveryPossibleRedirect  {
	
	Logger logg = Logger.getLogger(EveryPossibleRedirect.class);
	
	@RequestMapping(value = "/*")
	public String everyPossibleRedierect() {
		return "404.jsp";
	}
	

}
