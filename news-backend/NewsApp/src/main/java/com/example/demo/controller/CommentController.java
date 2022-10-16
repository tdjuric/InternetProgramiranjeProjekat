package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PasswordNotMatchException;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.model.Author;
import com.example.demo.model.Comment;
import com.example.demo.model.News;
import com.example.demo.service.CommentService;
import com.example.demo.service.NewsService;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;

	@RequestMapping("/all")
	public List<Comment> getAllComments() {
		return commentService.getComments();
	}
	
	
	 @GetMapping(path = "{news_id}")
	 public List<Comment> getCommentByNewsId(@PathVariable("news_id") String news_id){
	        return commentService.getCommentByNewsId(news_id);
	 
	    }  
	
	
	
	 @PostMapping(path = "/addComment")
	    public Comment registerComment(@RequestBody String jsonUser)
	    		 throws UserAlreadyExistException {

	        JSONObject news = new JSONObject(jsonUser); 
	        String comment_content = news.getString("comment_content");
	        String news_id = news.getString("news_id");
	        String subscriber_id = news.getString("subscriber_id");
	        
	        Comment c = commentService
	                .addComment(comment_content, news_id, subscriber_id);
	        if (c == null) {
	            throw new UserAlreadyExistException("Comment already exists!");
	        }
	        return c;
	    }
	 
	
	
	 
	 @DeleteMapping(path = "/deleteComment")
	  public void removeNews(@RequestBody String jsonObject) {
	        JSONObject object = new JSONObject(jsonObject);
	        String id = object.getString("id");
	        this.commentService.removeComment(id);
	        System.out.println("Comment with id: " + id + " has been successfully deleted!");
	  }

	@RequestMapping(method = RequestMethod.DELETE, value = "/news")
	public void deleteNews(@RequestBody Comment comment) {
		commentService.deleteComment(comment);
	}
	
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotExistException.class, PasswordNotMatchException.class,
            UserAlreadyExistException.class})
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
