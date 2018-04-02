package com.nonprofit.charity.nonprofit.databaseoperations;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nonprofit.charity.nonprofit.entity.PaymentInfo;

@Service
@ManagedBean
public class PaymentInfoService {
	
	@Autowired
	public PaymentInfoRepository paymentInfoRepository;
	
	public void addNewRow(PaymentInfo paymentInfo) {
		
		paymentInfoRepository.save(paymentInfo);
		
	}

}
