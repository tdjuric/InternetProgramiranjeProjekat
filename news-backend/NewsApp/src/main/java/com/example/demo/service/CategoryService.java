package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;

import com.example.demo.repository.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categorytRepository;
	
	public CategoryService() {
	}

	public List<Category> getAllCategories() {
		return this.categorytRepository.findAll();
	}
	
	public Optional<Category> getCategoryById(String news_id) {
		return this.categorytRepository.findById(news_id);
	}

	public void addCategory(Category category) {
		this.categorytRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		this.categorytRepository.save(category);
	}

	public void deleteCategoryById(String id) {
		this.categorytRepository.deleteById(id);	
	}
	
	public void deleteCategories(Category category) {
		this.categorytRepository.delete(category);	
	}

}
