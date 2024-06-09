package com.dauphine.manager.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FeedbackRequest {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotEmpty(message = "Comment is required")
    private String comment;

    @Min(1)
    @Max(5)
    private int rating;
}