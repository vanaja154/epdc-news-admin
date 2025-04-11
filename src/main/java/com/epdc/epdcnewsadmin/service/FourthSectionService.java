package com.epdc.epdcnewsadmin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.FourthSection;
import com.epdc.epdcnewsadmin.entity.HomePageContent;
import com.epdc.epdcnewsadmin.repository.FourthSectionRepository;


@Service
public class FourthSectionService {
    @Autowired
    private FourthSectionRepository contentRepository;

     public List<FourthSection> getAllContent() {
        return contentRepository.findAll();
    }
    public FourthSection getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }

    public void saveContent(FourthSection content) {
        contentRepository.save(content);
    }

}
