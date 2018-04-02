package com.nonprofit.charity.nonprofit.logics;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckUserCredentialsTest {
	
	@Inject
	CheckUserCredentials checkUserCredentials;
	
	@Test
	public void ifUserNameIsNull() {
		String userName=null;
		String password="password";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifPasswordIsNull() {
		String userName="test";
		String password=null;
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameAndPasswordBothNull() {
		String userName=null;
		String password=null;
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameAndPasswordIsEmpty() {
		String userName="";
		String password="";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameIsEmpty() {
		String userName="";
		String password="password";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifPasswordIsEmpty() {
		String userName="test";
		String password="";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameAndPasswordIsNotNullButWrongUserName() {
		String userName="123456";
		String password="password";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameAndPasswordIsNotNullButWrongPassword() {
		String userName="test5";
		String password="password123";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(false, result);
	}
	
	@Test
	public void ifUserNameAndPasswordIsNotNullAndCorrect() {
		String userName="test7";
		String password="password";
		boolean result=checkUserCredentials.loginCheck(userName, password);
		assertEquals(true, result);
	}
}
