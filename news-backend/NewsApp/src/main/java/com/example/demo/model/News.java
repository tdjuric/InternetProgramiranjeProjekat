package com.example.demo.model;



import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "news")
public class News {
	@Id
	private String id;
	@ManyToOne
	private Category category;
	private int category_id;
    private String date_posted; 
    private String source;
    @ManyToOne
    private Author author;
    private String author_name;
    private String imgUrl;
	private String news_title;
	private String news_content;
	private int news_status;
	private boolean comment_status;
	
	/*public News(Category category, int category_id, String date_posted, String source, Author author,
			String author_name, String imgUrl, String news_title, String news_content, int news_status,
			boolean comment_status) {
		super();
		this.category = category;
		this.category_id = category_id;
		this.date_posted = date_posted;
		this.source = source;
		this.author = author;
		this.author_name = author_name;
		this.imgUrl = imgUrl;
		this.news_title = news_title;
		this.news_content = news_content;
		this.news_status = news_status;
		this.comment_status = comment_status;
	}*/
	
	 public News( int category_id, String date_posted, String source,String author_name, String imgUrl,
			 String news_title, String news_content){
		 	super();
			this.category_id = category_id;
			this.date_posted = date_posted;
			this.source = source;
			this.author_name = author_name;
			this.imgUrl = imgUrl;
			this.news_title = news_title;
			this.news_content = news_content;  
	    }

	public String getNews_id() {
		return id;
	}

	public void setNews_id(String id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(String date_posted) {
		this.date_posted = date_posted;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public int getNews_status() {
		return news_status;
	}

	public void setNews_status(int news_status) {
		this.news_status = news_status;
	}

	public boolean isComment_status() {
		return comment_status;
	}

	public void setComment_status(boolean comment_status) {
		this.comment_status = comment_status;
	}

	@Override
	public String toString() {
		return "News [news_id=" + id + ", category=" + category + ", category_id=" + category_id + ", date_posted="
				+ date_posted + ", source=" + source + ", author=" + author + ", author_name=" + author_name
				+ ", imgUrl=" + imgUrl + ", news_title=" + news_title + ", news_content=" + news_content
				+ ", news_status=" + news_status + ", comment_status=" + comment_status + "]";
	}
	
	
	
	
	
	
}