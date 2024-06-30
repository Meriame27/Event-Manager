package com.dauphine.manager.service.impl;

import com.dauphine.manager.dto.EventDTO;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.entity.User;
import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.repository.EventRepository;
import com.dauphine.manager.repository.FeedbackRepository;
import com.dauphine.manager.repository.RegistrationRepository;
import com.dauphine.manager.repository.UserRepository;
import com.dauphine.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;


    public List<EventDTO> getAllEvents(String username) {
        User currentUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Registration> registrations = registrationRepository.findByUser(currentUser);

        return eventRepository.findAll().stream().map(event -> {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setName(event.getName());
            eventDTO.setDate(event.getDate());
            eventDTO.setCategory(event.getCategory());
            eventDTO.setTime(event.getTime());
            eventDTO.setOrganizerId(event.getOrganizer().getId());
            eventDTO.setRegistered(registrations.stream().anyMatch(reg -> reg.getEvent().getId().equals(event.getId())));
            return eventDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event;
        } else {
            throw new RuntimeException("Event not found with id " + id);
        }
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setName(eventDetails.getName());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            event.setOrganizer(eventDetails.getOrganizer());
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Event not found with id " + id);
        }
    }

    @Transactional
    public void deleteEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            List<Feedback> feedbacks = feedbackRepository.findByEvent(event);
            feedbackRepository.deleteAll(feedbacks);
            List<Registration> registrations = registrationRepository.findByEvent(event);
            registrationRepository.deleteAll(registrations);
            eventRepository.delete(event);
        } else {
            throw new RuntimeException("Event not found with id " + id);
        }
    }

}