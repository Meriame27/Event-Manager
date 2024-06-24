package com.dauphine.manager.dto;

import lombok.Data;
@Data
public class FeedbackDTO {

    private Long id;

    private Long userId;

    private Long eventId;

    private int rating;


}