package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.HomePageContent;

public interface HomePageContentRepository extends MongoRepository<HomePageContent, String> {
    HomePageContent findFirstByOrderByIdAsc();
    
}
