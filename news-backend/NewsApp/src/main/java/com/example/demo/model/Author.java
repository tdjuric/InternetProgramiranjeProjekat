package com.example.demo.model;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "author")
public class Author extends User {
	

	 public Author() {
	        this.setType("author");
	    }
	    public Author(String name, String username, String email, String password){
	        super(name, username, email, password);
	        this.setType("author");
	    }
	
	
	

}
