package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.BookEventResponse;
import org.semicolon.event_Booking.dtos.response.CreateEventResponse;
import org.semicolon.event_Booking.dtos.response.EventResponse;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.InvalidEventException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;

public interface EventService {
    CreateEventResponse createEvent(CreateEventRequest request) throws UserDoesNotExistException, InvalidDateFormatException;

    BookEventResponse bookEvent(BookEventRequest request) throws InvalidEventException;

    EventResponse findEventBy(Long id) throws InvalidEventException;
}
