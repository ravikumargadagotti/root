package com.nonprofit.charity.nonprofit.databaseoperations;

import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@ManagedBean
public class SelectExample {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> selectExample(String userName) {
		
		String sql = "select userName,password from UserCredentials where userName=?";		
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,userName);
		for (Map<String, Object> row : list) {
			//System.out.print(row.get("id"));
		    System.out.print(" "+row.get("userName"));
		    //System.out.print(" "+row.get("password"));
		    System.out.println();
		}
		return list;
	}
}
