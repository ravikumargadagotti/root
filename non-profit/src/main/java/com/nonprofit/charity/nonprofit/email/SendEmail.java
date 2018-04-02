package com.nonprofit.charity.nonprofit.email;

import java.util.Properties;
import javax.annotation.ManagedBean;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

@ManagedBean
public class SendEmail {
	
	Logger logg = Logger.getLogger(SendEmail.class);
	
	public boolean emailSend(String[] toAddress, String subject, String messageBody, String messageType) {
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "mail.codeandsleep.com");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply@codeandsleep.com", "Joseph@99056");
			}
		});
		
		try {
			for(String toEmail : toAddress) {
			logg.info("Sending Email To: "+toEmail);	
			MimeMessage message = new MimeMessage(session);
			message.setFrom("noreply@codeandsleep.com");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			
			if(messageType.equalsIgnoreCase("text")) {
			message.setText(messageBody);
			}else {
				message.setContent(messageBody, "text/html;charset=utf-8");
			}
			Transport.send(message);
			}
			return true;
		}catch(MessagingException me) {
			me.printStackTrace();
		}
		
		return false;
	}

}
