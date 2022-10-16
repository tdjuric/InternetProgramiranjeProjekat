package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.model.News;


@Repository
public interface NewsRepository extends MongoRepository<News, String> {
	
	@Query("{ 'news_title' : ?0}")
	List<News> findByNews_title(String news_title);
	

}