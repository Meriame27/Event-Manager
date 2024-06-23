package com.dauphine.manager.service.impl;

import com.dauphine.manager.dto.EventDTO;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.entity.User;
import com.dauphine.manager.repository.EventRepository;
import com.dauphine.manager.repository.FeedbackRepository;
import com.dauphine.manager.repository.RegistrationRepository;
import com.dauphine.manager.repository.UserRepository;
import com.dauphine.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            eventDTO.setLocation(event.getLocation());
            eventDTO.setDate(event.getDate());
            eventDTO.setCategory(event.getCategory());
            eventDTO.setTime(event.getTime());
            eventDTO.setOrganizer(event.getOrganizer());
            eventDTO.setRegistered(registrations.stream().anyMatch(reg -> reg.getEvent().getId().equals(event.getId())));
            return eventDTO;
        }).collect(Collectors.toList());
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
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
            event.setLocation(eventDetails.getLocation());
            event.setOrganizer(eventDetails.getOrganizer());
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Event not found with id " + id);
        }
    }

    public void deleteEvent(Long id) {
        Event e =eventRepository.findById(id).get();
        System.out.println(e);
        feedbackRepository.deleteByEvent(e);
        registrationRepository.deleteByEvent(e);
        eventRepository.deleteById(id);
    }
}