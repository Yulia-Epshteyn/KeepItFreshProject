package com.keepitfresh.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepitfresh.model.Setting;
import com.keepitfresh.model.SettingService;

@Controller
public class SettingController {

    @Autowired
    private SettingService service;

    @RequestMapping(value = "/list-settings", method = RequestMethod.GET)
    public String showSettingsList(ModelMap model) {
        String user = getLoggedInUserName();
        model.addAttribute("settings", service.retrieveSettings(user));
        return "list-settings";
    }

    @RequestMapping(value = "/add-setting", method = RequestMethod.GET)
    public String showAddSettingPage(ModelMap model) {
        model.addAttribute("setting", new Setting());
        return "setting";
    }

    @RequestMapping(value = "/add-setting", method = RequestMethod.POST)
    public String addSetting(ModelMap model, @Valid Setting setting, BindingResult result) {
        if (result.hasErrors())
            return "setting";
        service.addSetting(getLoggedInUserName(), setting.getEmailAddress(),
        		setting.getDaysBeforeExp());
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-settings";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value = "/update-setting", method = RequestMethod.GET)
    public String showUpdateSettingPage(ModelMap model, @RequestParam int id) {
        model.addAttribute("setting", service.retrieveSetting(id));
        return "setting";
    }

    @RequestMapping(value = "/update-setting", method = RequestMethod.POST)
    public String updateSetting(ModelMap model, @Valid Setting setting,
            BindingResult result) {
        if (result.hasErrors())
            return "setting";

        service.updateSetting(setting);

        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-settings";
    }

    @RequestMapping(value = "/delete-setting", method = RequestMethod.GET)
    public String deleteSetting(@RequestParam int id) {
    	service.deleteSetting(id);
        return "redirect:/list-settings";
    }
}