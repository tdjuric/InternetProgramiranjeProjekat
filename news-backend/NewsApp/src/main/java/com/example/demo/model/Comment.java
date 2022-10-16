package com.example.demo.model;

import java.sql.Date;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "comment")
public class Comment {
	
	@Id
	private String id;
	private String comment_content;
	@ManyToOne
	private Subscriber subscriber;
	private String subscriber_id;
	@ManyToOne
	private News news;
	private String news_id;
	private Date comment_date;
	private boolean comment_status;
	private String user_id;
	
	/*public Comment(String comment_content, int subscriber_id, int news_id, Date comment_date,
			boolean comment_status, int user_id) {
		super();
		this.comment_content = comment_content;
		this.subscriber_id = subscriber_id;
		this.news_id = news_id;
		this.comment_date = comment_date;
		this.comment_status = comment_status;
		this.user_id = user_id;
	}*/
	
	public Comment(String comment_content, String news_id, String subscriber_id) {
		super();
		this.comment_content = comment_content;
		this.news_id = news_id;
		this.subscriber_id = subscriber_id;
	}
	
	

	public String getComment_id() {
		return id;
	}

	public void setComment_id(int comment_id) {
		this.id = id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getSubscriber_id() {
		return subscriber_id;
	}

	public void setSubscriber_id(String subscriber_id) {
		this.subscriber_id = subscriber_id;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	public boolean isComment_status() {
		return comment_status;
	}

	public void setComment_status(boolean comment_status) {
		this.comment_status = comment_status;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Comment [comment_id=" + id + ", comment_content=" + comment_content + ", subscriber_id="
				+ subscriber_id + ", news_id=" + news_id + ", comment_date=" + comment_date + ", comment_status="
				+ comment_status + ", user_id=" + user_id + "]";
	}
	

}