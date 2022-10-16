package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Author;
import com.example.demo.model.Subscriber;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
	
	
	@Query("{ 'subscriber_email' : ?0}")
	List<Subscriber> findBySubscriber_email(String email);
	
	@Query("{ 'usrename' : ?0}")
	List<Subscriber> findByUsername(String usrename);

}
