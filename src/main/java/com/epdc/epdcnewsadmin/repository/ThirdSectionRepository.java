package com.epdc.epdcnewsadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.epdc.epdcnewsadmin.entity.ThirdSection;

public interface ThirdSectionRepository extends MongoRepository<ThirdSection,String>{

     ThirdSection findFirstByOrderByIdAsc();
    

}
