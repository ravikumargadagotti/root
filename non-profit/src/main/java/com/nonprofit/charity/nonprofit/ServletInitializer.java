package com.nonprofit.charity.nonprofit;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//Initialise any thread if needed here.
		return application.sources(NonProfitApplication.class);
	}

}
