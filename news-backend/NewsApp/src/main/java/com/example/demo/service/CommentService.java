package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.News;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	public CommentService() {
	}
	
	public Comment addComment(String comment_content, String news_id, String subscriber_id) {
		 if(!comment_content.equals("")) {
	            Comment comment = new Comment(comment_content, news_id, subscriber_id);
	            commentRepository.save(comment);
	            System.out.println("Comment added to the database");
	            return comment;
		 }
	        System.out.println("Comment can't be added to the database");
	        return null;
	}
	
	public int removeComment(String id){
		commentRepository.deleteById(id);
        return 1;
    }

	
	 
	
	public List<Comment> getComments() {
	    return commentRepository.findAll();
	}

	public Optional<Comment> getCommentById(String comment_id) {
		return this.commentRepository.findById(comment_id);
	}
	
	public List<Comment> getCommentByNewsId(String news_id) {
		return this.commentRepository.findByNews_id(news_id);
	}

	
	public void deleteCommentById(String id) {
		/*Optional<News> n = this.getNewsById(id);*/
		this.commentRepository.deleteById(id);	
	}
	
	public void deleteComment(Comment comment) {
		this.commentRepository.delete(comment);	
	}

}
