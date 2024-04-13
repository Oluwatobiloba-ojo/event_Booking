package org.semicolon.event_Booking.services;

import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.response.BookEventResponse;
import org.semicolon.event_Booking.dtos.response.BookedTicketResponse;
import org.semicolon.event_Booking.dtos.response.CancelTicketResponse;
import org.semicolon.event_Booking.exception.InvalidEventException;
import org.semicolon.event_Booking.exception.TicketExistException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testThatGetAllBookedTicket() throws InvalidEventException, UserDoesNotExistException {
        eventService.bookEvent(new BookEventRequest(201L, 201L));
        eventService.bookEvent(new BookEventRequest(202L, 201L));
        List<BookedTicketResponse> responses = ticketService.getAllTicketFor(201L);
        assertThat(responses).isNotNull();
        assertThat(responses.size()).isEqualTo(2);
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testCancelTicket() throws InvalidEventException, UserDoesNotExistException, TicketExistException {
        BookEventResponse response = eventService.bookEvent(new BookEventRequest(201L, 201L));
        eventService.bookEvent(new BookEventRequest(202L, 201L));
        CancelTicketResponse response1 = ticketService.cancelTicket(response.getTickedId());
        assertThat(response1).isNotNull();
        assertThat(ticketService.getAllTicketFor(201L).size()).isEqualTo(1);
    }

}