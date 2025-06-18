package com.epdc.epdcnewsadmin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.Epaper;
import com.epdc.epdcnewsadmin.entity.Magazine;
import com.epdc.epdcnewsadmin.repository.EpaperRepository;
import com.epdc.epdcnewsadmin.repository.MagazineRepository;

@Service
public class MagazineService {

     @Autowired
    private MagazineRepository repository;

    public Magazine getLatestEditions() {
        return repository.findFirstByOrderByIdAsc();
    }

    public List<Magazine> getEditionsByDate(Date date) {
        return repository.findByDate(date);
    }

    public void saveEditions(Magazine mainEditions) {
        repository.save(mainEditions);
    }

}
