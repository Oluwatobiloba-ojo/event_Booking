package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.Ticket;
import org.semicolon.event_Booking.dtos.response.BookedTicketResponse;
import org.semicolon.event_Booking.dtos.response.CancelTicketResponse;
import org.semicolon.event_Booking.dtos.response.TicketResponse;
import org.semicolon.event_Booking.exception.TicketExistException;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(Event event, Long request);

    List<BookedTicketResponse> getAllTicketFor(Long userId);

    CancelTicketResponse cancelTicket(String tickedId) throws TicketExistException;

    Ticket findTicketBy(String ticketId) throws TicketExistException;

}
