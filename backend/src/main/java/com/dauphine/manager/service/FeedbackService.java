package com.dauphine.manager.service;

import com.dauphine.manager.entity.Feedback;

import java.util.List;
import java.util.Optional;


public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Optional<Feedback> getFeedbackById(Long id);
    Feedback createFeedback(Feedback feedback);
    Feedback updateFeedback(Long id, Feedback feedbackDetails);
    void deleteFeedback(Long id);
}