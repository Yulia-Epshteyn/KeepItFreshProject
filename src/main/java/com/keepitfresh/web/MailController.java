package com.keepitfresh.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.keepitfresh.model.EmailService;
import com.keepitfresh.model.Item;
import com.keepitfresh.model.ItemService;
import com.keepitfresh.model.Setting;
import com.keepitfresh.model.SettingService;

@Controller
public class MailController {
	
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private SettingService settingService;
    
    @Autowired
    private EmailService emailService;
    
    
    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public String sendEmail(ModelMap model) {
    	
    	String user = "polina";

    	List<Item> listExpiredItem = new ArrayList<Item>();
    	List<Item> listPreExpiredItem = new ArrayList<Item>();
    	List<Setting> listOfSettings = new ArrayList<Setting>();
    	Date todayDate = new Date();
    	String today = extractDayFromDate(todayDate);
    	Iterable<Item> items = itemService.retrieveItems(user);
    	Iterable<Setting> settings = settingService.retrieveSettings(user);
    	for(Item item: items) {
    		
    		for(Setting setting: settings ) {
	    		Date expDate = item.getExpDate();
	    		String expDateString = extractDayFromDate(expDate);
	    		Date preNotificationDate = getPreNotificationDate(expDate, setting.getDaysBeforeExp() );
	    		String preNotificationDateString = extractDayFromDate(preNotificationDate);
	    		
	    		if(expDateString.equals(today)){
	    			listExpiredItem.add(item);
	    		}
	    		if(preNotificationDateString.equals(today)){
	    			listPreExpiredItem.add(item);
	    		}
    		}
    	}
    	for(Setting setting: settings)
    	{
    		if(setting.getUser().equals(user)){
    			listOfSettings.add(setting);
    		}
    	}
    	
    	for(Setting setting : listOfSettings)
    	{
    		String email = setting.getEmailAddress();
        	for(Item expiredItem : listExpiredItem)
        	{
            	emailService.sendEmailExpired(email, user, expiredItem.getName(), expiredItem.getQuantity() );
        	}
        	for(Item preExpiredItem : listPreExpiredItem)
        	{
            	emailService.sendEmailPreExpired(email, user, preExpiredItem.getName(), preExpiredItem.getQuantity(),
            			                                                  preExpiredItem.getExpDate(), setting.getDaysBeforeExp()   );
        	}
    	}
    	
        return "redirect:/";
    }
    
    
    private String extractDayFromDate(Date date)
    {
    	DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	return outputFormatter.format(date);
    }
    
    private Date getPreNotificationDate(Date date, int daysBeforeExp)
    {
    	return new DateTime(date).minusDays(daysBeforeExp).toDate();
    }
}