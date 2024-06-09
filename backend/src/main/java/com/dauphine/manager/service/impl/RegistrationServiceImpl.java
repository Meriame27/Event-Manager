package com.dauphine.manager.service.impl;

import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.repository.RegistrationRepository;
import com.dauphine.manager.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    public Registration createRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public Registration updateRegistration(Long id, Registration registrationDetails) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(id);
        if (optionalRegistration.isPresent()) {
            Registration registration = optionalRegistration.get();
            registration.setUser(registrationDetails.getUser());
            registration.setEvent(registrationDetails.getEvent());
            return registrationRepository.save(registration);
        } else {
            throw new RuntimeException("Registration not found with id " + id);
        }
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}