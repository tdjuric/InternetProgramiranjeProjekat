package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.PasswordNotMatchException;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.model.Author;
import com.example.demo.model.Subscriber;
import com.example.demo.service.AuthorService;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
 
    }
	
	 @GetMapping(path = "{id}")
	 public Author getAuthorById(@PathVariable("id") String id)
	            throws UserNotExistException {
	        return authorService.getUser(id)
	                .orElseThrow(() -> new UserNotExistException("User doesn't exist"));
	    }  
	 
	 @GetMapping(path = "/all")
	 public List<Author> getAllAuthors(){
	        return authorService.getUsers();
	    }

	 @PostMapping(path = "/login")
	    public Author loginAuthor(@RequestBody String jsonUser)
	    		  throws UserNotExistException, PasswordNotMatchException {

	        JSONObject user = new JSONObject(jsonUser);
	        String username = user.getString("username");
	        String password = user.getString("password");
	        Optional<Author> author = authorService.getUserByUsername(username);
	        if (author.isEmpty()) {
	            throw new UserNotExistException("User doesn't exist");
	        }
	        if (!authorService.passwordMatch(author.get().getId(), password)) {
	            throw new PasswordNotMatchException("Password doesn't match");
	        }
	        return author.get();
	    }
	 
	 @PostMapping(path = "/register")
	    public Author registerAuthor(@RequestBody String jsonUser)
	    		 throws UserAlreadyExistException {

	        JSONObject user = new JSONObject(jsonUser);
	        String username = user.getString("username");
	        String email = user.getString("email");
	        String password = user.getString("password");
	        String name = user.getString("name");
	      
	        Author author = authorService
	                .addUser(name, username, email, password);
	        if (author == null) {
	            throw new UserAlreadyExistException("User already exists, please login");
	        }
	        return author;
	    }

	    @PostMapping(path = "/logout")
	    public int logoutAuthor() {
	        System.out.println("User is logged out!");
	        return 1;
	    }
	    
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    @ExceptionHandler({UserNotExistException.class, PasswordNotMatchException.class,
	            UserAlreadyExistException.class})
	    public String handleException(Exception e) {
	        return e.getMessage();
	    }

}