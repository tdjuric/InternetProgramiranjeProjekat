package com.example.demo.model;





import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "subscriber")
public class Subscriber extends User {
	
	 public Subscriber() {
	        this.setType("subscriber");
	    }
	    public Subscriber(String name, String username, String email, String password){
	        super(name, username, email, password);
	        this.setType("subscriber");
	    }
		
}
