package com.nonprofit.charity.nonprofit.controller;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EveryPossibleRedirectTest {

	@Inject
	EveryPossibleRedirect everyPossibleRedirect;
	
	@Test
	public void everyPossibleRedirectCodeCoverage() {
		String expectedResult = "404.jsp";
		String actualResult = everyPossibleRedirect.everyPossibleRedierect();
		assertEquals(expectedResult, actualResult);
	}
}
