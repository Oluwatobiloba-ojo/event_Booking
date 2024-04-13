package org.semicolon.event_Booking.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.Ticket;
import org.semicolon.event_Booking.data.repository.TicketRepository;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.response.TicketResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppTicketService implements TicketService{
    private ModelMapper mapper;
    private TicketRepository repository;

    @Override
    public TicketResponse createTicket(Event event, BookEventRequest request) {
        Ticket ticket = new Ticket();
        ticket.setUserEmail(request.getUserEmail());
        ticket.setUserLastName(request.getUserLastName());
        ticket.setUserFirstName(request.getUserFirstName());
        ticket.setEvent(event);
        repository.save(ticket);
        TicketResponse response = new TicketResponse();
        response.setTickedId(ticket.getId());
        response.setMessage("Ticket created");
        return response;
    }
}
