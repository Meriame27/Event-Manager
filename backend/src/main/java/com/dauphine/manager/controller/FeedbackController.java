package com.dauphine.manager.controller;

import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/average-rating")
    public Double getEventAverageRating(@RequestParam Long eventId) {
        return feedbackService.getEventAverageRating(eventId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id).orElseThrow(() -> new RuntimeException("Feedback not found with id " + id));
        return ResponseEntity.ok(feedback);
    }
}