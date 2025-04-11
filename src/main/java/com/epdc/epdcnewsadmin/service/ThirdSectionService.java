package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epdc.epdcnewsadmin.entity.ThirdSection;
import com.epdc.epdcnewsadmin.repository.ThirdSectionRepository;

@Service
public class ThirdSectionService {
    @Autowired
    private ThirdSectionRepository contentRepository;

    public List<ThirdSection> getAllContent() {
        return contentRepository.findAll();
    }
    public ThirdSection getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }

    public void saveContent(ThirdSection content) {
        contentRepository.save(content);
    }

}
