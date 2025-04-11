package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.DonationSection;

public interface DonationSectionRepository  extends MongoRepository<DonationSection, String> {
    DonationSection findFirstByOrderByIdAsc();
}