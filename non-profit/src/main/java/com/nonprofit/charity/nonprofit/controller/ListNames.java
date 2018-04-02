package com.nonprofit.charity.nonprofit.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListNames {

	Logger logg = Logger.getLogger(ListNames.class);

	@RequestMapping(method = RequestMethod.POST, value = "listNames")
	public String insertUserName(@RequestParam("names") String names, HttpServletRequest request) {

		logg.info("Came to list users");
		if (names != null && !names.isEmpty()) {
			try {
				String[] list = names.split(",");
				Random random = new Random();
				String selectedName = list[random.nextInt(list.length)];
				request.setAttribute("givenList", names);
				logg.info(selectedName);
				request.setAttribute("selectedName", selectedName);
			} catch (NullPointerException nl) {
				logg.error("Null pointer exception", nl);
				nl.printStackTrace();
			}
			return "result.jsp";
		} else {
			return "mainMenu.jsp";
		}
	}

}
