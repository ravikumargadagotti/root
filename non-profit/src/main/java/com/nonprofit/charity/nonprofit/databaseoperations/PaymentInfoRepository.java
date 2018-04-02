package com.nonprofit.charity.nonprofit.databaseoperations;

import org.springframework.data.repository.CrudRepository;

import com.nonprofit.charity.nonprofit.entity.PaymentInfo;

public interface PaymentInfoRepository extends CrudRepository<PaymentInfo, Integer> {

}
