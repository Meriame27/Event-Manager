package com.dauphine.manager.repository;

import com.dauphine.manager.entity.Event;
import com.dauphine.manager.entity.Feedback;
import com.dauphine.manager.entity.Registration;
import com.dauphine.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(@Param("user") User user);
    List<Registration> findByEvent(Event event);

    Registration findByEventAndUser(Event event, User user);


}