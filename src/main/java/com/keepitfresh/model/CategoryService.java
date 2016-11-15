package com.keepitfresh.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao dao;

    public Iterable<Category> retrieveCategories() {
    	return dao.findAll();
    }

    public Category retrieveCategory(int id) {
    	return dao.findOne(id);
    }

    public void updateCategory(Category category) {
    	dao.save(category);
    }

    public void addCategory(String name) {
    	Category category = new Category(name);
    	dao.save(category);
    }

    public void deleteCategory(int id) {
        dao.delete(id);
    }
}
