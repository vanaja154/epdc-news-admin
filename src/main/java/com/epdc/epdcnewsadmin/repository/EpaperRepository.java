package com.epdc.epdcnewsadmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.Epaper;

public interface EpaperRepository  extends MongoRepository<Epaper, String> {
    Epaper findFirstByOrderByIdAsc();

    List<Epaper> findByDate(Date date);
}
