package com.epdc.epdcnewsadmin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.Epaper;
import com.epdc.epdcnewsadmin.repository.EpaperRepository;

@Service
public class EpaperService {

    @Autowired
    private EpaperRepository repository;

    public Epaper getLatestEditions() {
        return repository.findFirstByOrderByIdAsc();
    }

    public List<Epaper> getEditionsByDate(Date date) {
        return repository.findByDate(date);
    }

    public void saveEditions(Epaper mainEditions) {
        repository.save(mainEditions);
    }

}
