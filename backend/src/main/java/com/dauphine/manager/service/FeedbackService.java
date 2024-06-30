package com.dauphine.manager.service;

import com.dauphine.manager.dto.CommentDTO;
import com.dauphine.manager.dto.FeedbackDTO;
import com.dauphine.manager.entity.Feedback;

import java.util.List;
import java.util.Optional;


public interface FeedbackService {
    Double getEventAverageRating(Long eventId);

    FeedbackDTO getUserFeedbackOnEvent(Long eventId, Long userId);
    Feedback createOrUpdateUserFeedback(Long eventId, Long userId, FeedbackDTO feedbackDetails);

    public Feedback updateOrCreateFeedbackWithComment(Long eventId, Long userId, String comment);

    List<CommentDTO> getEventFeedbacks(Long eventId);
}