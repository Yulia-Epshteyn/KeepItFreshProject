package com.keepitfresh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keepitfresh.model.Setting;
import com.keepitfresh.model.SettingService;

@RestController
public class SettingRestController {
    @Autowired
    private SettingService service;

    @RequestMapping(path = "/settings")
    public Iterable<Setting> listAllSettings() {
        Iterable<Setting> users = service.retrieveSettings("polina");
        return users;
    }
    
    @RequestMapping(path = "/settings/{id}")
    public Setting listSetting(@PathVariable int id) {
        return service.retrieveSetting(id);
    }
}