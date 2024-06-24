package com.dauphine.manager.repository;
import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.User;
import com.dauphine.manager.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Optional<Feedback> findByEventAndUser(@Param("event") Event event, @Param("user") User user);

    List<Feedback> findByEvent(Event event);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.event = :event")
    Double findAverageRatingByEvent(@Param("event") Event event);
}