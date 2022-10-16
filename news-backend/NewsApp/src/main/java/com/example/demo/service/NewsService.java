package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;

@Service
public class NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	public NewsService() {
	}
	
	
	public News addNews(int category_id, String date_posted, String source,String author_name, String imgUrl,
			String news_title, String news_content) {
		 if (this.getNewsTitile(news_title) == null) {
	            News news = new News(category_id, date_posted, source, author_name, imgUrl, news_title, news_content);
	            newsRepository.save(news);
	            System.out.println("News added to the database");
	            return news;
	        }
	        System.out.println("News can't be added to the database");
	        return null;
	}
	
	public int removeNewsIteam(String id){
		newsRepository.deleteById(id);
        return 1;
    }

	public String getNewsTitile(String news_tittle) {
		   List<News> news = this.getNews();
	        for (News n : news) {
	            if (n.getNews_title().equals(news_tittle)) {
	                return ((News) news).getNews_id();
	            }
	        }
	        System.out.println("News with the given title does not exist in our database");
	        return null;
	}
	
	
	
	public List<News> getNews() {
	    return newsRepository.findAll();
	}

	public Optional<News> getNewsById(String news_id) {
		return this.newsRepository.findById(news_id);
	}

	public void addNews(News news) {
		this.newsRepository.save(news);
	}
	
	public void updateNews(News student) {
		this.newsRepository.save(student);
	}

	public void deleteNewsById(String id) {
		/*Optional<News> n = this.getNewsById(id);*/
		this.newsRepository.deleteById(id);	
	}
	
	public void deleteNews(News news) {
		this.newsRepository.delete(news);	
	}

}