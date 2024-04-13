package org.semicolon.event_Booking.controller;

import org.apache.coyote.Response;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.ApiResponse;
import org.semicolon.event_Booking.exception.InvalidDateFormatException;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.semicolon.event_Booking.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest request) {
        try {
            return ResponseEntity.accepted().body(new ApiResponse(eventService.createEvent(request), true));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody BookEventRequest request){
        try {
            return ResponseEntity.accepted().body(new ApiResponse(eventService.bookEvent(request), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }


}
