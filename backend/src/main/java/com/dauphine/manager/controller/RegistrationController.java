package com.dauphine.manager.controller;

import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController{

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Registration>> getRegistrationById(@PathVariable Long id) {
        Optional<Registration> registration = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/register")
    public ResponseEntity<Registration> registerUserToEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        Registration registration = registrationService.registerUserToEvent(eventId, userId);
        return ResponseEntity.ok(registration);
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<Void> unregisterUserFromEvent(@RequestParam Long eventId, @RequestParam Long userId) {
        registrationService.unregisterUserFromEvent(eventId, userId);
        return ResponseEntity.noContent().build();
    }
}