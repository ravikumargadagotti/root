package com.nonprofit.charity.nonprofit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nonprofit.charity.nonprofit.databaseoperations.PaymentInfoService;
import com.nonprofit.charity.nonprofit.entity.PaymentInfo;

@Controller
public class PaymentConsumer {
	@Autowired
	private PaymentInfoService paymentInfoService;
	
	@RequestMapping(method=RequestMethod.POST,value="payment")
	public void insertSomething(@ModelAttribute PaymentInfo payment) {
		paymentInfoService.addNewRow(payment);
		//return null;
	}

}
