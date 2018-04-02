package com.nonprofit.charity.nonprofit;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.nonprofit.charity.nonprofit"})
@EntityScan("com.nonprofit.charity.nonprofit.entity")
@EnableJpaRepositories("com.nonprofit.charity.nonprofit.databaseoperations")
public class NonProfitApplication {
	static Logger logg = Logger.getLogger(NonProfitApplication.class);
	public static void main(String[] args) {
		logg.info("Main Application");
		SpringApplication.run(NonProfitApplication.class, args);
		
	}
}
