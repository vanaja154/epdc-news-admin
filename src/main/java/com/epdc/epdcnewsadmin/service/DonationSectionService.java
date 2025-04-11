package com.epdc.epdcnewsadmin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epdc.epdcnewsadmin.entity.DonationSection;
import com.epdc.epdcnewsadmin.repository.DonationSectionRepository;

@Service
public class DonationSectionService {

    @Autowired
    private DonationSectionRepository donationSectionRepository;

    public DonationSection getLatestContent() {
        return donationSectionRepository.findFirstByOrderByIdAsc(); 
    }
    public void saveContent(DonationSection content) {
        if (content.getId() == null) {
            content.setId("1");
        }
        donationSectionRepository.save(content);
    }
}