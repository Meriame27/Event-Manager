package com.dauphine.manager.service;


import com.dauphine.manager.entity.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    List<Registration> getAllRegistrations();
    Optional<Registration> getRegistrationById(Long id);
     Registration registerUserToEvent(Long eventId, Long userId);
    void unregisterUserFromEvent(Long eventId, Long userId);
}