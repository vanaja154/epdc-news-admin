package com.epdc.epdcnewsadmin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.News;

public interface NewsRepository extends MongoRepository<News, String> {

    // Find the latest content for a specific category
    News findFirstByCategoryOrderByIdAsc(String category);

    // Find all content for a specific category
    List<News> findByCategory(String category);
}