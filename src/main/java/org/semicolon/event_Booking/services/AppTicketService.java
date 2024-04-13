package org.semicolon.event_Booking.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.Ticket;
import org.semicolon.event_Booking.data.repository.TicketRepository;
import org.semicolon.event_Booking.dtos.response.BookedTicketResponse;
import org.semicolon.event_Booking.dtos.response.CancelTicketResponse;
import org.semicolon.event_Booking.dtos.response.TicketResponse;
import org.semicolon.event_Booking.exception.TicketExistException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.semicolon.event_Booking.exception.GlobalException.INVALID_TICKET;

@Service
@AllArgsConstructor
public class AppTicketService implements TicketService{
    private ModelMapper mapper;
    private TicketRepository repository;

    @Override
    public TicketResponse createTicket(Event event, Long userId) {
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setEvent(event);
        repository.save(ticket);
        TicketResponse response = new TicketResponse();
        response.setTickedId(ticket.getId());
        response.setMessage("Ticket created");
        return response;
    }

    @Override
    public List<BookedTicketResponse> getAllTicketFor(Long userId) {
        return repository.findAllByUserId(userId)
                         .stream()
                         .map(BookedTicketResponse::new)
                         .toList();
    }

    @Override
    public CancelTicketResponse cancelTicket(String tickedId) throws TicketExistException {
        Ticket ticket = findTicketBy(tickedId);
        repository.delete(ticket);
        CancelTicketResponse response = new CancelTicketResponse();
        response.setMessage("Ticket with "+ tickedId + " has been deleted");
        return response;
    }

    @Override
    public Ticket findTicketBy(String ticketId) throws TicketExistException {
        return repository.findById(ticketId)
                .orElseThrow(() -> new TicketExistException(INVALID_TICKET));
    }
}
