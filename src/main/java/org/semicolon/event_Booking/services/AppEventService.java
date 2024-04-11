package org.semicolon.event_Booking.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.Category;
import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.data.model.User;
import org.semicolon.event_Booking.data.repository.EventRepository;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.CreateEventResponse;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppEventService implements EventService{
    private UserService userService;
    private ModelMapper modelMapper;
    private EventRepository repository;
    @Override
    public CreateEventResponse createEvent(CreateEventRequest request) throws UserDoesNotExistException {
       User user = userService.findBy(request.getOwnerId());
       Event event = modelMapper.map(request, Event.class);
       event.setCategory(Category.valueOf(request.getCategory().toUpperCase()));
       event.setUser(user);
       repository.save(event);
       CreateEventResponse response = new CreateEventResponse();
       response.setMessage("Event created successfully");
       return response;
    }
}
