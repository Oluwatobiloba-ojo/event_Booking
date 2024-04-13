package org.semicolon.event_Booking.data.repository;

import org.semicolon.event_Booking.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
