package com.example.demo.model;


/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collation = "category")
public class Category {
	
	@Id
	private String id;
	private String category_name;
	private String category_description;
	
	public Category(String category_name, String category_description) {
		super();
		this.category_name = category_name;
		this.category_description = category_description;
	}

	public String getCategory_id() {
		return id;
	}

	public void setCategory_id(String id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + id + ", category_name=" + category_name + ", category_description="
				+ category_description + "]";
	}
	
}
