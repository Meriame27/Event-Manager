package com.dauphine.manager.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "feedbacks")
@SequenceGenerator(name = "feedback_seq_gen", sequenceName = "feedback_seq", initialValue = 10, allocationSize = 1)
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submitted_at", nullable = false, updatable = false)
    private Date submittedAt;

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", event=" + event.getName() +
                ", rating=" + rating +
                ", submittedAt=" + submittedAt +
                '}';
    }
}