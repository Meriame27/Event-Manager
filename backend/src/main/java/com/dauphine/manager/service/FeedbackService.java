package com.dauphine.manager.service;

import com.dauphine.manager.entity.Feedback;

import java.util.List;
import java.util.Optional;


public interface FeedbackService {
    Double getEventAverageRating(Long eventId);

    Optional<Feedback> getFeedbackById(Long id);
     Feedback createOrUpdateFeedback( Feedback feedbackDetails);
}