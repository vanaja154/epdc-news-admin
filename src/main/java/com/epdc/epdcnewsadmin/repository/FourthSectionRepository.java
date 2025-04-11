package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.FourthSection;

public interface FourthSectionRepository extends MongoRepository<FourthSection,String>{

    FourthSection findFirstByOrderByIdAsc();
   

}
