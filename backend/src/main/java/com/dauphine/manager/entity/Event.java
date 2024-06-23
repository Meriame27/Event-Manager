package com.dauphine.manager.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "events")
@SequenceGenerator(name = "event_seq_gen", sequenceName = "event_seq", initialValue = 10, allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_gen")
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Please enter the event name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Please enter the event date")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull(message = "Please enter the event time")
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @NotEmpty(message = "Please enter the event location")
    @Column(name = "location", nullable = false)
    private String location;

    @NotEmpty(message = "Please enter the event category")
    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}