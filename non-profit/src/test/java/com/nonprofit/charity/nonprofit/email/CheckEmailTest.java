package com.nonprofit.charity.nonprofit.email;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckEmailTest {
	
	@Inject
	SendEmail sendEmail;
	
	@Test
	public void sendingEmail() {
		String[] emailList = new String[] {"grvkumar450@gmail.com"};
		
		StringBuffer messageBody = new StringBuffer();
		messageBody.append("<table width=\\\"100%\\\" border= \\\"1\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\">");
		messageBody.append("<thead>");
		messageBody.append("<tr>");
		messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
		messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
		messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
		messageBody.append("</tr>");
		messageBody.append("</thead>");
		
		messageBody.append("<tr bgcolor=\"white\">");
		messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
		messageBody.append("Test First Name");
		messageBody.append("</font></td>");
		
		
		messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
		messageBody.append("Test Last Name");
		messageBody.append("</font></td>");
		
		
		messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
		messageBody.append("test@test.com");
		messageBody.append("</font></td>");
		messageBody.append("</tr>");
		messageBody.append("</table>");
		
		boolean result = sendEmail.emailSend(emailList, "test", messageBody.toString(), "html");
		assertEquals(true, result);
	}

}
