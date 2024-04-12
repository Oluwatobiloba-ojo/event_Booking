package org.semicolon.event_Booking.services;

import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.CreateEventResponse;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;
    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testThatEventCanBeCreated() throws UserDoesNotExistException {
        CreateEventRequest request = new CreateEventRequest();
        request.setName("Event");
        request.setDate("2004-03-31");
        request.setAvailableAttendeesCount(100L);
        request.setCategory("Concert");
        request.setEventDescription("This is an event");
        request.setOwnerId(200L);
        CreateEventResponse response = eventService.createEvent(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testThatEventWithoutValidDateFormatWillThrowException() {
        CreateEventRequest request = new CreateEventRequest();
        request.setName("Event");
        request.setDate("Monday 2004 12");
        request.setAvailableAttendeesCount(100L);
        request.setCategory("Concert");
        request.setEventDescription("This is an event");
        request.setOwnerId(200L);
        assertThrows(InvalidDateFormatException.class, () -> eventService.createEvent(request));
    }



}