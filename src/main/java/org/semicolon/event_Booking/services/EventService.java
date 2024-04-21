package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.Ticket;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.*;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.InvalidEventException;
import org.semicolon.event_Booking.exception.TicketExistException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;

import java.util.List;

public interface EventService {
    CreateEventResponse createEvent(CreateEventRequest request) throws UserDoesNotExistException, InvalidDateFormatException;

    BookEventResponse bookEvent(BookEventRequest request) throws InvalidEventException, UserDoesNotExistException;

    EventResponse findEventBy(Long id) throws InvalidEventException;

    CancelEventResponse cancelBookedEvent(Long eventId, String tickedId) throws InvalidEventException, TicketExistException;

    List<EventResponse> findAllEventAvailable();
    List<Event> findAllEventOccuringNextDay();
    List<Ticket> findAllTicketFor(Event event);
}
