package com.dauphine.manager.service;


import com.dauphine.manager.entity.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    List<Registration> getAllRegistrations();
    Optional<Registration> getRegistrationById(Long id);
    Registration createRegistration(Registration registration);
    Registration updateRegistration(Long id, Registration registrationDetails);
    void deleteRegistration(Long id);
}