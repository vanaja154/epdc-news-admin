package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}
