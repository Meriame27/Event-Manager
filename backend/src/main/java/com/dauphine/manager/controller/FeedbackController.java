package com.dauphine.manager.controller;

import com.dauphine.manager.dto.CommentDTO;
import com.dauphine.manager.dto.FeedbackDTO;
import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/average-rating")
    public Double getEventAverageRating(@RequestParam Long eventId) {
        return feedbackService.getEventAverageRating(eventId);
    }

    @GetMapping("/event/user-feedback")
    public FeedbackDTO getUserFeedbackOnEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        return feedbackService.getUserFeedbackOnEvent(eventId, userId);
    }

    @PostMapping("/event/user-feedback")
    public Feedback createOrUpdateUserFeedback(@RequestParam Long eventId, @RequestParam Long userId, @RequestBody FeedbackDTO feedbackDetails) {
        return feedbackService.createOrUpdateUserFeedback(eventId, userId, feedbackDetails);
    }

    @PostMapping("/event/user-comment")
    public ResponseEntity<Feedback> updateOrCreateFeedbackWithComment(@RequestParam Long eventId, @RequestParam Long userId, @RequestParam String comment) {
        Feedback feedback = feedbackService.updateOrCreateFeedbackWithComment(eventId, userId, comment);
        return ResponseEntity.ok(feedback);
    }


    @GetMapping("/event/{eventId}")
    public List<CommentDTO> getEventFeedbacks(@PathVariable Long eventId) {
        return feedbackService.getEventFeedbacks(eventId);
    }

}