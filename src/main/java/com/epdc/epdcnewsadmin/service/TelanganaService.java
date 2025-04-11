package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.AndhraPradesh;
import com.epdc.epdcnewsadmin.repository.AndhraPradeshRepository;

@Service
public class TelanganaService {

    @Autowired
    private AndhraPradeshRepository contentRepository;

     public List<AndhraPradesh> getAllContent() {
        return contentRepository.findAll();
    }
    public AndhraPradesh getLatestContent() {
        return contentRepository.findFirstByOrderByIdAsc(); // Fetch latest content
    }

    public void saveContent(AndhraPradesh content) {
        contentRepository.save(content);
    }

}
