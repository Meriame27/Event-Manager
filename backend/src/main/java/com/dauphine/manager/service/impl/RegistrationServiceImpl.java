package com.dauphine.manager.service.impl;

import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.User;
import com.dauphine.manager.repository.EventRepository;
import com.dauphine.manager.repository.RegistrationRepository;
import com.dauphine.manager.repository.UserRepository;
import com.dauphine.manager.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Transactional
    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    @Transactional
    public Registration registerUserToEvent(Long eventId, Long userId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isPresent() && user.isPresent()) {
            Registration registration = new Registration();
            registration.setEvent(event.get());
            registration.setUser(user.get());
            return registrationRepository.save(registration);
        } else {
            throw new RuntimeException("Event or User not found");
        }
    }

    @Transactional
    public void unregisterUserFromEvent(Long eventId, Long userId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isPresent() && user.isPresent()) {
            Registration registration = registrationRepository.findByEventAndUser(event.get(), user.get());
            if (registration != null) {
                registrationRepository.delete(registration);
            } else {
                throw new RuntimeException("Registration not found");
            }
        } else {
            throw new RuntimeException("Event or User not found");
        }
    }


    }
