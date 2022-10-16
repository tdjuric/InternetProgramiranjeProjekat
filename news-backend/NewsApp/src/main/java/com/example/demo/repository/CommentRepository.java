package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;



@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
		
	@Query("{ 'news_id' : ?0}")
	List<Comment> findByNews_id(String news_id);
}

