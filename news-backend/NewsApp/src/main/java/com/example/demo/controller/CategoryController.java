package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;


@RestController
//@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
     }
	
	@RequestMapping("categories/{id}")
	public Optional<Category> getAuthorById(@PathVariable String id) {
		return categoryService.getCategoryById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/categories")
	public void addAuthor(@RequestBody Category category) {
		categoryService.addCategory(category);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/categories")
	public void updateAuthor(@RequestBody Category category) {
		categoryService.updateCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/categories/{id}")
	public void deleteAuthorById(@PathVariable String id) {
		categoryService.deleteCategoryById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/categories")
	public void deleteAuthors(@RequestBody Category category) {
		categoryService.deleteCategories(category);
	}
	
}
