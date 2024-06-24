package com.dauphine.manager.service.impl;

import com.dauphine.manager.dto.FeedbackDTO;
import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.User;
import com.dauphine.manager.repository.EventRepository;
import com.dauphine.manager.repository.FeedbackRepository;
import com.dauphine.manager.repository.UserRepository;
import com.dauphine.manager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Double getEventAverageRating(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return feedbackRepository.findAverageRatingByEvent(event);
    }
    public FeedbackDTO getUserFeedbackOnEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).get();
        User user = userRepository.findById(userId).get();
        Optional<Feedback> feedbackOpt = feedbackRepository.findByEventAndUser(event, user);
        FeedbackDTO feedbackDTO =  new FeedbackDTO();
        if( feedbackOpt.isPresent()){
            feedbackDTO.setId(feedbackOpt.get().getId());
            feedbackDTO.setRating(feedbackOpt.get().getRating());
            feedbackDTO.setUserId(user.getId());
            feedbackDTO.setEventId(eventId);
        }
        return feedbackDTO;
    }
    public Feedback createOrUpdateUserFeedback(Long eventId, Long userId, FeedbackDTO feedbackDetails) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Feedback> existingFeedback = feedbackRepository.findByEventAndUser(event, user);
        Feedback feedback;
        if (existingFeedback.isPresent()) {
            feedback = existingFeedback.get();
            feedback.setRating(feedbackDetails.getRating());
        } else {
            feedback = new Feedback();
            feedback.setEvent(event);
            feedback.setUser(user);
            feedback.setRating(feedbackDetails.getRating());
        }
        return feedbackRepository.save(feedback);
    }
}
