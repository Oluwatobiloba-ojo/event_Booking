package org.semicolon.event_Booking.data.repository;

import org.semicolon.event_Booking.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
