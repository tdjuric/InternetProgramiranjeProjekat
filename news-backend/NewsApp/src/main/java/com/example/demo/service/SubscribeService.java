package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Subscriber;
import com.example.demo.repository.SubscriberRepository;

@Service
public class SubscribeService implements UserService<Subscriber>{
	
	@Autowired
	SubscriberRepository subscriberRepository;
    PasswordService passwordService = new PasswordService();
	
	
	@Override
	public Subscriber addUser(String name, String username, String email, String password) {
	     if (this.getUserIdByEmail(email) == null) {
	            String newPassword = passwordService.generatePassword(password);
	            Subscriber subscriber = new Subscriber(name, username, email, newPassword);
	            subscriberRepository.save(subscriber);
	            System.out.println("Subscriber added to the database");
	            return subscriber;
	        }
	        System.out.println("Subscriber can't be added to the database");
	        return null;
	}

	@Override
	public int deleteUser(String id) {
	    if (this.getUser(id).isPresent()) {
	    	subscriberRepository.deleteById(id);
            System.out.println("Subscriber deleted from the database");
            return 1;
        }
        System.out.println("Subscriber can't be deleted from the database");
        return -1;
	}

	@Override
	public Optional<Subscriber> getUser(String id) {
		 if (id != null) {
	            return subscriberRepository.findById(id);
	        }
	        return Optional.empty();
	}

	@Override
	public String getUserIdByEmail(String email) {
		   List<Subscriber> subscribers = this.getUsers();
	        for (Subscriber subscriber : subscribers) {
	            if (subscriber.getUsername().equals(email)) {
	                return subscriber.getId();
	            }
	        }
	        System.out.println("User with the given email does not exist in our database");
	        return null;
	}

	@Override
	public Optional<Subscriber> getUserByEmail(String email) {
		   return this.getUser(getUserIdByEmail(email));
	}

	@Override
	public List<Subscriber> getUsers() {
	    return subscriberRepository.findAll();
	}


	@Override
	public String getUserIdByUsername(String username) {
		List<Subscriber> subscribers = this.getUsers();
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getUsername().equals(username)) {
                return subscriber.getId();
            }
        }
        System.out.println("User with the given username does not exist in our database");
        return null;
	}

	@Override
	public Optional<Subscriber> getUserByUsername(String username) {
		return this.getUser(getUserIdByUsername(username));
	}
	
	@Override
	public boolean passwordMatch(String id, String password) {
		Optional<Subscriber> author = this.getUser(id);
        return author.isPresent() && passwordService.passwordMatch(password, author.get().getPassword());
	}
}
	
	/*	public boolean logInSubscriber(Subscriber subscriber) {
		List<Subscriber> subscribers = this.subscriberRepository.findByUsername(subscriber.getUsername());
		if(subscribers.size() == 1) {
			if(subscriber.getPassword().equals(subscribers.get(0).getPassword())){
				return true;
			}
		}
			return false;
	}
	
	public void signUpSubscriber(Subscriber subscriber) {
		List<Subscriber> subscribers = this.subscriberRepository.findByUsername(subscriber.getUsername());
		if(subscribers.isEmpty())
			this.subscriberRepository.save(subscriber);
	}
	 */

