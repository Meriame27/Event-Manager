package com.dauphine.manager.service.impl;

import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.repository.EventRepository;
import com.dauphine.manager.repository.FeedbackRepository;
import com.dauphine.manager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EventRepository eventRepository;

    public Double getEventAverageRating(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return feedbackRepository.findAverageRatingByEvent(event);
    }
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback createOrUpdateFeedback( Feedback feedbackDetails) {
        if(feedbackDetails.getId() != null){
            Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackDetails.getId());
            if (optionalFeedback.isPresent()) {
                Feedback feedback = optionalFeedback.get();
                feedback.setUser(feedbackDetails.getUser());
                feedback.setEvent(feedbackDetails.getEvent());
                feedback.setRating(feedbackDetails.getRating());
                return feedbackRepository.save(feedback);}
            else{
                return feedbackRepository.save(feedbackDetails);
                }
        } else {
            return feedbackRepository.save(feedbackDetails);
        }
    }
}
