package com.keepitfresh.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.core.env.Environment;
 
import com.keepitfresh.config.properties.AppConfig;
import com.keepitfresh.config.properties.ApplicationProperties;

public class EmailService {
	
	public void sendEmail(String emailRecipient, String username,  String itemName, Integer quantity){
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 
        ApplicationProperties appProperties = context.getBean(ApplicationProperties.class);
		
		try{
	        Properties props = new Properties();
	        props.put("mail.smtp.host", appProperties.getSmtpServer()); // for gmail use smtp.gmail.com
	
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
	
	        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
	
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("email", "password");
	            }
	        });
	
	        mailSession.setDebug(true); // Enable the debug mode
	        
	        Message msg = new MimeMessage( mailSession );
	
	        //--[ Set the FROM, TO, DATE and SUBJECT fields
	        msg.setFrom( new InternetAddress( "polinabochk@gmail.com" ) );
	        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(emailRecipient) );
	        msg.setSentDate( new Date());
	        msg.setSubject( "Notification from the KeepItFresh app" );
	
	        //--[ Create the body of the mail
	        
	        StringBuffer emailMessage = new StringBuffer("Hello " + username + "!");
	        emailMessage.append("<p></p>");
	        emailMessage.append("Your " + itemName + "(" + quantity + ") is expiring today.");
	        emailMessage.append("<p></p> ");
	        emailMessage.append("Sincerely, <br/>KeepItFreshTeam<br>");
	        
	        msg.setContent(emailMessage.toString() , "text/html; charset=utf-8");
	        //--[ Ask the Transport class to send our mail message
	        Transport.send( msg );
	
	    }catch(Exception E){
	        System.out.println( "Oops something has gone pearshaped!");
	        System.out.println( E );
	    }
	
    }
}