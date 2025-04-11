package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.AndhraPradesh;

public interface AndhraPradeshRepository extends MongoRepository<AndhraPradesh,String>{

    AndhraPradesh findFirstByOrderByIdAsc();

    // AndhraPradesh getContentByType(String detailName);
    AndhraPradesh findByDetailName(String detailName);

   

}
