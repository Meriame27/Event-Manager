package com.dauphine.manager.service;

import com.dauphine.manager.dto.FeedbackDTO;
import com.dauphine.manager.entity.Feedback;

import java.util.Optional;


public interface FeedbackService {
    Double getEventAverageRating(Long eventId);

    FeedbackDTO getUserFeedbackOnEvent(Long eventId, Long userId);
    Feedback createOrUpdateUserFeedback(Long eventId, Long userId, FeedbackDTO feedbackDetails);
}