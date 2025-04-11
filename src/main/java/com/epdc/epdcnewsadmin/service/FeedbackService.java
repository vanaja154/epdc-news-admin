package com.epdc.epdcnewsadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epdc.epdcnewsadmin.entity.Feedback;
import com.epdc.epdcnewsadmin.repository.FeedbackRepository;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save feedback submitted by users
    public void saveFeedback(Feedback feedback) {
        feedback.setApproved(false); // Feedback starts as unapproved
        feedbackRepository.save(feedback);
    }

    // Fetch all feedback (for admin panel)
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Fetch only approved feedback (for user website)
    public List<Feedback> getApprovedFeedback() {
        return feedbackRepository.findByApprovedTrue();
    }

    // Approve feedback by ID
    public void approveFeedback(String id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setApproved(true);
        feedbackRepository.save(feedback);
    }

    // Reject feedback by ID
    public void rejectFeedback(String id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedbackRepository.delete(feedback);
    }

}
