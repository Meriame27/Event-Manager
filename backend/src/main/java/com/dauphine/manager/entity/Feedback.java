package com.dauphine.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "feedbacks")
@SequenceGenerator(name = "feedback_seq_gen", sequenceName = "feedback_seq", initialValue = 10, allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq_gen")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @Column(name = "rating")
    private int rating;

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", event=" + event.getName() +
                ", rating=" + rating +
                '}';
    }
}