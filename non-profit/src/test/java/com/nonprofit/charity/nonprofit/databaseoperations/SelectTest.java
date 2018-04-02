package com.nonprofit.charity.nonprofit.databaseoperations;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTest {
	
	@Inject
	SelectExample selectExample;
	
	@Test
	public void selectTestUserName() {
		List<Map<String,Object>> result = selectExample.selectExample("test");
		String name=null,password=null;
		for(Map<String, Object> list : result) {
			name = (String) list.get("userName");
			password = (String) list.get("password");
		}		
		assertEquals("test", name);
		assertEquals("password",password);		
	}
	
	@Test
	public void selectUnknownUserName() {
		List<Map<String,Object>> result = selectExample.selectExample("taegbvahsdbfjwEF");
		String name=null,password=null;
		for(Map<String, Object> list : result) {
			name = (String) list.get("userName");
			password = (String) list.get("password");
		}		
		assertEquals(null, name);
		assertEquals(null,password);
	}

}
