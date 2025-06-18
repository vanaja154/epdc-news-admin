package com.epdc.epdcnewsadmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.Magazine;

public interface MagazineRepository  extends MongoRepository<Magazine, String> {
    Magazine findFirstByOrderByIdAsc();

    List<Magazine> findByDate(Date date);
}
