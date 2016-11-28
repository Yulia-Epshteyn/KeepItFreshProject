package com.keepitfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailService {
	
	
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendEmailExpired(String emailRecipient, String username,  String itemName, Integer quantity){
    	SimpleMailMessage msg = new SimpleMailMessage();
        StringBuffer emailMessage = new StringBuffer("Hello " + username + ",\n\n");
        emailMessage.append("Just a friendly reminder, your " + itemName + " (quantity: " + quantity + ") is expiring today.\n\n");
        emailMessage.append("Sincerely,\nKeepItFreshTeam");
        msg.setText(emailMessage.toString());
        msg.setTo(emailRecipient);
        msg.setSubject( "Notification from the KeepItFresh app" );
        msg.setFrom("polinabochk@gmail.com");
        
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void sendEmailPreExpired(String emailRecipient, String username,  String itemName, Integer quantity, Date expDate, int daysBeforeExpired){
    	
    	SimpleMailMessage msg = new SimpleMailMessage();
        StringBuffer emailMessage = new StringBuffer("Hello " + username + ",\n\n");
        emailMessage.append("Just a friendly reminder, your " + itemName + "'s (quantity: " + quantity + ") expiration date comes due on "
                                                              + extractDayFromDate(expDate) + " (in " + daysBeforeExpired + " days).\n\n");
        emailMessage.append("Sincerely,\nKeepItFreshTeam");
        msg.setText(emailMessage.toString());
        msg.setTo(emailRecipient);
        msg.setSubject( "Notification from the KeepItFresh app" );
        msg.setFrom("polinabochk@gmail.com");
        
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private String extractDayFromDate(Date date)
    {
    	DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	return outputFormatter.format(date);
    }
}
