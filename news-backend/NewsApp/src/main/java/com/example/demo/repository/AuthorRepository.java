package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Author;


@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
	
	@Query("{ 'author_email' : ?0}")
	List<Author> findByAuthor_email(String email);
	
	@Query("{ 'usrename' : ?0}")
	List<Author> findByUsername(String usrename);

}
