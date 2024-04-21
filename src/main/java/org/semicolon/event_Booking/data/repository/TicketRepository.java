package org.semicolon.event_Booking.data.repository;

import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findAllByUserId(Long userId);
    List<Ticket> findAllByEvent(Event event);

}
