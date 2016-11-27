package com.keepitfresh.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public String sendEmail(ModelMap model) {
    	EmailService emailService = new EmailService();
    	List<Item> listExpiredItem = new ArrayList<Item>();
/*    	List<Item> listPreExpiredItem = new ArrayList<Item>();*/
    	List<Setting> listOfSettings = new ArrayList<Setting>();
    	Date todayDate = new Date();
    	String today = extractDayFromDate(todayDate);
    	Iterable<Item> items = itemService.retrieveItems(getLoggedInUserName());
    	Iterable<Setting> settings = settingService.retrieveSettings(getLoggedInUserName());
    	for(Item item: items)
    	{
    		Date expDate = item.getExpDate();
    		String expDateString = extractDayFromDate(expDate);
    		
    		if(expDateString.equals(today)){
    			listExpiredItem.add(item);
    		}
    	}
    	
    	
    	for(Setting setting: settings)
    	{
    		if(setting.getUser().equals(getLoggedInUserName())){
    			listOfSettings.add(setting);
    		}
    	}
    	
    	for(Setting setting : listOfSettings)
    	{
    		String email = setting.getEmailAddress();
        	for(Item expiredItem : listExpiredItem)
        	{
            	emailService.sendEmail(email, getLoggedInUserName(), expiredItem.getName(), expiredItem.getQuantity() );
        	}
    	}

        return "redirect:/";
    }
    
    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
    
    private String extractDayFromDate(Date date)
    {
    	DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
    	return outputFormatter.format(date);
    	
    }
}