package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.HomePageContent;
import com.epdc.epdcnewsadmin.repository.HomePageContentRepository;

@Service
public class HomePageContentService {

    @Autowired
    private HomePageContentRepository contentRepository;

    public List<HomePageContent> getAllContent() {
        return contentRepository.findAll();
    }
    public HomePageContent getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }

    public void saveContent(HomePageContent content) {
        contentRepository.save(content);
    }

}
