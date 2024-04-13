package org.semicolon.event_Booking.services;

import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.BookEventResponse;
import org.semicolon.event_Booking.dtos.response.CancelEventResponse;
import org.semicolon.event_Booking.dtos.response.CreateEventResponse;
import org.semicolon.event_Booking.dtos.response.EventResponse;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.InvalidEventException;
import org.semicolon.event_Booking.exception.TicketExistException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;
    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testThatEventCanBeCreated() throws UserDoesNotExistException, InvalidDateFormatException {
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

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testBookingEvent() throws InvalidEventException, UserDoesNotExistException {
        Long eventAttendeeCount = eventService.findEventBy(201L).getAvailableAttendeesCount();
        BookEventRequest request = new BookEventRequest();
        request.setEventId(201L);
        request.setUserId(200L);
        BookEventResponse response = eventService.bookEvent(request);
        assertThat(response).isNotNull();
        assertThat(eventService.findEventBy(201L).getAvailableAttendeesCount()).isEqualTo(eventAttendeeCount - 1);
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testBookedEventCanBeCanceled() throws InvalidEventException, UserDoesNotExistException, TicketExistException {
        Long eventAttendeeCount = eventService.findEventBy(201L).getAvailableAttendeesCount();
        BookEventResponse response = eventService.bookEvent(new BookEventRequest(201L, 200L));
        assertThat(ticketService.getAllTicketFor(200L).size()).isEqualTo(1);
        assertThat(eventService.findEventBy(201L).getAvailableAttendeesCount()).isEqualTo(eventAttendeeCount-1);
        CancelEventResponse response1 = eventService.cancelBookedEvent(201L, response.getTickedId());
        assertThat(ticketService.getAllTicketFor(200L).size()).isEqualTo(0);
        assertNotNull(response1);
        assertThat(eventService.findEventBy(201L).getAvailableAttendeesCount()).isEqualTo(eventAttendeeCount);
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testGetAllEventsAvailable(){
        List<EventResponse> response = eventService.findAllEventAvailable();
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
    }

}