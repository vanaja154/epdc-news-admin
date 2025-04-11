package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.News;
import com.epdc.epdcnewsadmin.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    // Get the latest content for a specific category
    public News getLatestContentByCategory(String category) {
        return newsRepository.findFirstByCategoryOrderByIdAsc(category);
    }

    // Get all content for a specific category
    public List<News> getContentByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

    // Save or update content
    public void saveContent(News news) {
        newsRepository.save(news);
    }
}
