package com.epdc.epdcnewsadmin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.UserFeedBack;

public interface UserFeedBackRepository extends MongoRepository<UserFeedBack, String> {
    List<UserFeedBack> findAll();

    List<UserFeedBack> findByApprovedTrue();
}