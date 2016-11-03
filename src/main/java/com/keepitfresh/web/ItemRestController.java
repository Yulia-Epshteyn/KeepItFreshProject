package com.keepitfresh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keepitfresh.model.Item;
import com.keepitfresh.model.ItemService;

@RestController
public class ItemRestController {
    @Autowired
    private ItemService service;

    @RequestMapping(path = "/items")
    public Iterable<Item> listAllItems() {
        Iterable<Item> users = service.retrieveItems("polina");
        return users;
    }
    
    @RequestMapping(path = "/items/{id}")
    public Item listItem(@PathVariable int id) {
        return service.retrieveItem(id);
    }
}