package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epdc.epdcnewsadmin.entity.Telangana;

public interface TelanganaRepository extends MongoRepository<Telangana,String>{

    Telangana findFirstByOrderByIdAsc();
   

}

