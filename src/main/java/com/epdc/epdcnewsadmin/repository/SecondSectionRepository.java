package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.SecondSection;

public interface SecondSectionRepository extends MongoRepository<SecondSection, String> {
    SecondSection findFirstByOrderByIdAsc();
    
}