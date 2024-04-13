package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.response.TicketResponse;

public interface TicketService {

    TicketResponse createTicket(Event event, BookEventRequest request);

}
