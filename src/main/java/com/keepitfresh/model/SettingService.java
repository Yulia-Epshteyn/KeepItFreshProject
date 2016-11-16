package com.keepitfresh.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingDao dao;

    public Iterable<Setting> retrieveSettings(String user) {
    	return dao.findAll();
    }

    public Setting retrieveSetting(int id) {
    	return dao.findOne(id);
    }

    public void updateSetting(Setting setting) {
    	dao.save(setting);
    }

    public void addSetting(String user, String emailAddress, int daysBeforeExp) {
    	Setting setting = new Setting(user, emailAddress, daysBeforeExp);
    	dao.save(setting);
    }

    public void deleteSetting(int id) {
        dao.delete(id);
    }
}
