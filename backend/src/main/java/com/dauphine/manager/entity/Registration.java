package com.dauphine.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "registrations")
@SequenceGenerator(name = "registration_seq_gen", sequenceName = "registration_seq", initialValue = 10, allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registration_seq_gen")
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_at", nullable = false, updatable = false)
    private Date registeredAt;

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", event=" + event.getName() +
                ", registeredAt=" + registeredAt +
                '}';
    }
}