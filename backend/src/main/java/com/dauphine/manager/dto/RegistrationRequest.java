package com.dauphine.manager.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Event ID is required")
    private Long eventId;
}