package com.keepitfresh.web;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keepitfresh.model.CategoryService;

@Component
public class CategoryEditor extends PropertyEditorSupport {

	@Autowired
	private CategoryService categoryService;
	
    @Override
    public void setAsText(String id) 
    {
    	if (!id.isEmpty()) {
    		this.setValue(categoryService.retrieveCategory(Integer.parseInt(id)));
    	}
    }
}
