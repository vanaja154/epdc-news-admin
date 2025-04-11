package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.UserFeedBack;
import com.epdc.epdcnewsadmin.repository.UserFeedBackRepository;

@Service
public class UserFeedBackService {

    @Autowired
    private UserFeedBackRepository feedbackRepository;

    // Save feedback submitted by users
    public void saveFeedback(UserFeedBack feedback) {
        feedback.setApproved(false); // Feedback starts as unapproved
        feedbackRepository.save(feedback);
    }

    // Fetch all feedback (for admin panel)
    public List<UserFeedBack> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Fetch only approved feedback (for user website)
    public List<UserFeedBack> getApprovedFeedback() {
        return feedbackRepository.findByApprovedTrue();
    }

    // Approve feedback by ID
    public void approveFeedback(String id) {
        UserFeedBack feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setApproved(true);
        feedbackRepository.save(feedback);
    }

    // Reject feedback by ID
    public void rejectFeedback(String id) {
        UserFeedBack feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedbackRepository.delete(feedback);
    }

}

