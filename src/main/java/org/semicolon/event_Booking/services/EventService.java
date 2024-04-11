package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.CreateEventResponse;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;

public interface EventService {
    CreateEventResponse createEvent(CreateEventRequest request) throws UserDoesNotExistException;
}
