package com.keepitfresh.model;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemDao dao;

    public Iterable<Item> retrieveItems(String user) {
    	return dao.findAll();
    }

    public Item retrieveItem(int id) {
    	return dao.findOne(id);
    }

    public void updateItem(Item item) {
    	dao.save(item);
    }

    public void addItem(String user, String name, Category category, Integer quantity, Date expDate) {
    	Item i = new Item(user, name, category, quantity, expDate);
    	dao.save(i);
    }

    public void deleteItem(int id) {
        dao.delete(id);
    }
}
