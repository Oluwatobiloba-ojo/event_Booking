package org.semicolon.event_Booking.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.Category;
import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.User;
import org.semicolon.event_Booking.data.repository.EventRepository;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.*;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.InvalidEventException;
import org.semicolon.event_Booking.exception.TicketExistException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.semicolon.event_Booking.util.Validation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.semicolon.event_Booking.exception.GlobalException.*;

@Service
@AllArgsConstructor
public class AppEventService implements EventService{
    private UserService userService;
    private ModelMapper modelMapper;
    private EventRepository repository;
    private TicketService ticketService;
    private final Long TICKET_NUMBER = 1L;

    @Override
    public CreateEventResponse createEvent(CreateEventRequest request) throws UserDoesNotExistException, InvalidDateFormatException {
       User user = userService.findBy(request.getOwnerId());
       if(!Validation.isValid(request.getDate())) throw new InvalidDateFormatException(INVALID_DATE);
       Event event = modelMapper.map(request, Event.class);
       event.setCategory(Category.valueOf(request.getCategory().toUpperCase()));
       event.setUser(user);
       repository.save(event);
       CreateEventResponse response = new CreateEventResponse();
       response.setMessage("Event created successfully");
       return response;
    }

    @Override
    public BookEventResponse bookEvent(BookEventRequest request) throws InvalidEventException, UserDoesNotExistException {
        User user = userService.findBy(request.getUserId());
        Event event = findEvent(request.getEventId());
        if (event.getAvailableAttendeesCount().equals(0L)) throw new InvalidEventException(EVENT_OUT_OF_BOUND);
        TicketResponse ticketResponse = ticketService.createTicket(event, user.getId());
        event.setAvailableAttendeesCount(event.getAvailableAttendeesCount() - TICKET_NUMBER);
        repository.save(event);
        return generateResponse(request, ticketResponse);
    }

    private static BookEventResponse generateResponse(BookEventRequest request, TicketResponse ticketResponse) {
        BookEventResponse response = new BookEventResponse();
        response.setUserId(request.getUserId());
        response.setTickedId(ticketResponse.getTickedId());
        response.setMessage(SUCCESSFUL_BOOKING_EVENT);
        return response;
    }

    @Override
    public EventResponse findEventBy(Long id) throws InvalidEventException {
        return repository.findById(id)
                .map(event -> modelMapper.map(event, EventResponse.class))
                .orElseThrow(() -> new InvalidEventException(INVALID_EVENT));
    }

    @Override
    public CancelEventResponse cancelBookedEvent(Long eventId, String tickedId) throws InvalidEventException, TicketExistException {
        Event event = findEvent(eventId);
        ticketService.cancelTicket(tickedId);
        event.setAvailableAttendeesCount(event.getAvailableAttendeesCount()+TICKET_NUMBER);
        repository.save(event);
        CancelEventResponse response = new CancelEventResponse();
        response.setMessage("Ticket booked for "+ event.getName() + " has been deleted");
        return response;
    }

    @Override
    public List<EventResponse> findAllEventAvailable() {
        return repository.findAll()
                         .stream()
                         .filter(event -> event.getAvailableAttendeesCount() > 0)
                         .map(event -> modelMapper.map(event, EventResponse.class))
                         .toList();
    }

    private Event findEvent(Long eventId) throws InvalidEventException {
        return repository.findById(eventId)
                .orElseThrow(() -> new InvalidEventException(INVALID_EVENT));
    }
}
