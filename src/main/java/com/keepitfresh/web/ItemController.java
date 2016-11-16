package com.keepitfresh.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepitfresh.model.Category;
import com.keepitfresh.model.CategoryService;
import com.keepitfresh.model.Item;
import com.keepitfresh.model.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryEditor categoryEditor;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        binder.registerCustomEditor(Category.class, categoryEditor);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showItemsList(ModelMap model) {
        String user = getLoggedInUserName();
        model.addAttribute("items", itemService.retrieveItems(user));
        return "list-items";
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
	    return "login";
	}
    
    @RequestMapping(value = "/add-item", method = RequestMethod.GET)
    public String showAddItemPage(ModelMap model) {
        model.addAttribute("item", new Item());
        return "item";
    }

    @RequestMapping(value = "/add-item", method = RequestMethod.POST)
    public String addItem(ModelMap model, @Valid Item item, BindingResult result) {
        if (result.hasErrors())
            return "item";
        itemService.addItem(getLoggedInUserName(), item.getName(),
                item.getCategory(), item.getQuantity(), item.getExpDate());
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value = "/update-item", method = RequestMethod.GET)
    public String showUpdateItemPage(ModelMap model, @RequestParam int id) {
        model.addAttribute("item", itemService.retrieveItem(id));
        return "item";
    }

    @RequestMapping(value = "/update-item", method = RequestMethod.POST)
    public String updateItem(ModelMap model, @Valid Item item,
            BindingResult result) {
        if (result.hasErrors())
            return "item";

        item.setUser(getLoggedInUserName());
        itemService.updateItem(item);

        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-item", method = RequestMethod.GET)
    public String deleteItem(@RequestParam int id) {
    	itemService.deleteItem(id);
        return "redirect:/";
    }
    
    @ModelAttribute("categories")
	public Iterable<Category> getCategories()
	{
		return categoryService.retrieveCategories();
	}
}