package org.semicolon.event_Booking.data.repository;

import org.semicolon.event_Booking.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
