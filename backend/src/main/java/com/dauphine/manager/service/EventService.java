package com.dauphine.manager.service;


import com.dauphine.manager.dto.EventDTO;
import com.dauphine.manager.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDTO> getAllEvents(String username);
    Optional<Event> getEventById(Long id);
    Event createEvent(Event event);
    Event updateEvent(Long id, Event eventDetails);
    void deleteEvent(Long id);
}