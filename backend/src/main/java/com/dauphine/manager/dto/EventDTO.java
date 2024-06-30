package com.dauphine.manager.dto;

import com.dauphine.manager.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private String location;
    private String  time;
    private LocalDate date;
    private String category;
    private boolean isRegistered;
    private Long  organizerId;


}