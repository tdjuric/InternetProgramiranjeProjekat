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
import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscribeService;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/subscriber")
public class SubscriberController {
	
	@Autowired
	SubscribeService subscriberService;
	
	@Autowired
    public SubscriberController(SubscribeService subscriberService) {
        this.subscriberService = subscriberService;
 
    }
	
	 @GetMapping(path = "{id}")
	 public Subscriber getAuthorById(@PathVariable("id") String id)
	            throws UserNotExistException {
	        return subscriberService.getUser(id)
	                .orElseThrow(() -> new UserNotExistException("User doesn't exist"));
	    }
	 
	 @GetMapping(path = "/all")
	 public List<Subscriber> getAllSubs(){
	        return subscriberService.getUsers();
	    }

	 @PostMapping(path = "/login")
	    public Subscriber loginSubscriber(@RequestBody String jsonUser)
	            throws UserNotExistException, PasswordNotMatchException {

	        JSONObject user = new JSONObject(jsonUser);
	        String username = user.getString("username");
	        String password = user.getString("password");
	        Optional<Subscriber> subscriber = subscriberService.getUserByUsername(username);
	        if (subscriber.isEmpty()) {
	            throw new UserNotExistException("User doesn't exist");
	        }
	        if (!subscriberService.passwordMatch(subscriber.get().getId(), password)) {
	            throw new PasswordNotMatchException("Password doesn't match");
	        }
	        return subscriber.get();
	    }
	 
	 @PostMapping(path = "/register")
	    public Subscriber registerSubscriber(@RequestBody String jsonUser)
	            throws UserAlreadyExistException {

	        JSONObject user = new JSONObject(jsonUser);
	        String username = user.getString("username");
	        String email = user.getString("email");
	        String password = user.getString("password");
	        String name = user.getString("name");
	      
	        Subscriber subscriber = subscriberService
	                .addUser(name, username, email, password);
	        if (subscriber == null) {
	            throw new UserAlreadyExistException("User already exists, please login");
	        }
	        return subscriber;
	    }

	    @PostMapping(path = "/logout")
	    public int logoutSubscriber() {
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
	
	/*@RequestMapping(method=RequestMethod.POST, value="/logInAuthor")
	public void logInAuthor(@RequestBody Author author) {
		authorService.logInAuthor(author);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/signUpAuthor")
	public void signUpAuthor(@RequestBody Author author) {
		authorService.signUpAuthor(author);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/authors")
	public void updateAuthor(@RequestBody Author author) {
		authorService.updateAuthor(author);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/authors/{id}")
	public void deleteAuthorById(@PathVariable String id) {
		authorService.deleteAuthorById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/authors")
	public void deleteAuthors(@RequestBody Author author) {
		authorService.deleteAuthors(author);
	}
	
	

}


































package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscribeService;



@RestController
@RequestMapping("/api/subscriber")
public class SubscriberController {
	
	@Autowired
	SubscribeService subscriberService;

	@RequestMapping("/subscribers")
	public List<Subscriber> getAllSubscribers() {
		return subscriberService.getAllSubscribers();
	}
	
	@RequestMapping("subscribers/{id}")
	public Optional<Subscriber> getSubscriberById(@PathVariable String id) {
		return subscriberService.getSubscriberById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/subscribers")
	public void addSubscriber(@RequestBody Subscriber subscriber) {
		subscriberService.addSubscriber(subscriber);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public void logInSubscriber(@RequestBody Subscriber subscriber) {
		subscriberService.logInSubscriber(subscriber);
	}

	@RequestMapping(method=RequestMethod.POST, value="/register")
	public void registerSubscriber(@RequestBody String jsonUser) 
				throws UserAlreadyExistException{
	
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/subscribers")
	public void updateSubscriber(@RequestBody Subscriber subscriber) {
		subscriberService.updateSubscriber(subscriber);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/subscribers/{id}")
	public void deleteSubscriberById(@PathVariable String id) {
		subscriberService.deleteSubscriberById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/subscribers")
	public void deleteSubscribers(@RequestBody Subscriber subscriber) {
		subscriberService.deleteSubscribers(subscriber);
	}
}*/
