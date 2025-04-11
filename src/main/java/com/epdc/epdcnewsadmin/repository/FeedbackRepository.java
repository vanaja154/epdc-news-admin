package com.epdc.epdcnewsadmin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    // Fetch all feedback (for admin panel)
     List<Feedback> findAll();
 
     // Fetch only approved feedback (for user website)
     List<Feedback> findByApprovedTrue();
 }
 