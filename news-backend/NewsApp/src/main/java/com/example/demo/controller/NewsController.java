package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.Author;
import com.example.demo.model.News;
import com.example.demo.service.NewsService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/news")
public class NewsController {
	

	@Autowired
	NewsService newsService;

	@RequestMapping("/all")
	public List<News> getAllNews() {
		return newsService.getNews();
	}
	
	@RequestMapping("/news")
	public List<News> getNews() {
		return newsService.getNews();
	}
	
	@RequestMapping("news/{id}")
	public Optional<News> getNewsById(@PathVariable String id) {
		return newsService.getNewsById(id);
	}
	
	 @PostMapping(path = "/addNews")
	    public News registerAuthor(@RequestBody String jsonUser)
	    		 throws UserAlreadyExistException {

	        JSONObject news = new JSONObject(jsonUser); 
	        int category_id = news.getInt("category_id");
	        String date_posted = news.getString("date_posted");
	        String source = news.getString("source");
	        String author_name = news.getString("author_name");
	        String imgUrl = news.getString("imgUrl");
	        String news_title = news.getString("news_title");
	        String news_content = news.getString("news_content");
	        
	        News n = newsService
	                .addNews(category_id, date_posted, source, author_name, imgUrl, news_title, news_content);
	        if (n == null) {
	            throw new UserAlreadyExistException("News already exists!");
	        }
	        return n;
	    }
	 
	
	 
	 @DeleteMapping(path = "/deleteNews")
	  public void removeNews(@RequestBody String jsonObject) {
	        JSONObject object = new JSONObject(jsonObject);
	        String newsId = object.getString("id");
	        this.newsService.removeNewsIteam(newsId);
	        System.out.println("News with id: " + newsId + " has been successfully deleted!");
	  }

	@RequestMapping(method = RequestMethod.DELETE, value = "/news")
	public void deleteNews(@RequestBody News news) {
		newsService.deleteNews(news);
	}
}
	
	
	
	


