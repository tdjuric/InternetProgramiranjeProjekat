package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Author;
import com.example.demo.model.Subscriber;
import com.example.demo.repository.AuthorRepository;


@Service
public class AuthorService implements UserService<Author>{
	
	@Autowired
	AuthorRepository authorRepository;
	PasswordService passwordService = new PasswordService();
	
	@Override
	public Author addUser(String name, String username, String email, String password) {
		 if (this.getUserIdByEmail(email) == null) {
	            String newPassword = passwordService.generatePassword(password);
	            Author author = new Author(name, username, email, newPassword);
	            authorRepository.save(author);
	            System.out.println("Author added to the database");
	            return author;
	        }
	        System.out.println("Author can't be added to the database");
	        return null;
	}
	@Override
	public int deleteUser(String id) {
		if (this.getUser(id).isPresent()) {
	    	authorRepository.deleteById(id);
            System.out.println("Author deleted from the database");
            return 1;
        }
        System.out.println("Author can't be deleted from the database");
        return -1;
	}
	
	@Override
	public Optional<Author> getUser(String id) {
		if (id != null) {
            return authorRepository.findById(id);
        }
        return Optional.empty();
	}
	
	@Override
	public String getUserIdByEmail(String email) {
		   List<Author> authors = this.getUsers();
	        for (Author author : authors) {
	            if (author.getUsername().equals(email)) {
	                return author.getId();
	            }
	        }
	        System.out.println("User with the given email does not exist in our database");
	        return null;
	}

	@Override
	public Optional<Author> getUserByEmail(String email) {
		   return this.getUser(getUserIdByEmail(email));
	}

	@Override
	public List<Author> getUsers() {
	    return authorRepository.findAll();
	}


	@Override
	public String getUserIdByUsername(String username) {
		List<Author> authors = this.getUsers();
        for (Author author : authors) {
            if (author.getUsername().equals(username)) {
                return author.getId();
            }
        }
        System.out.println("User with the given username does not exist in our database");
        return null;
	}

	@Override
	public Optional<Author> getUserByUsername(String username) {
		return this.getUser(getUserIdByUsername(username));
	}
	
	@Override
	public boolean passwordMatch(String id, String password) {
		Optional<Author> author = this.getUser(id);
        return author.isPresent() && passwordService.passwordMatch(password, author.get().getPassword());
	}	
	
}


