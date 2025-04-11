package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.SecondSection;
import com.epdc.epdcnewsadmin.repository.SecondSectionRepository;

@Service
public class SecondSectionService {
     @Autowired
    private SecondSectionRepository contentRepository;

    public List<SecondSection> getAllContent() {
        return contentRepository.findAll();
    }
    public SecondSection getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }

    public void saveContent(SecondSection content) {
        contentRepository.save(content);
    }

}
