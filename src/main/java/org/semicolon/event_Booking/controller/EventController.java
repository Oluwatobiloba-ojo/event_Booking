package org.semicolon.event_Booking.controller;

import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.ApiResponse;
import org.semicolon.event_Booking.services.EventService;
import org.semicolon.event_Booking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/user/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest request) {
        try {
            return ResponseEntity.accepted().body(new ApiResponse(eventService.createEvent(request), true));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookEvent(@RequestBody BookEventRequest request){
        try {
            return ResponseEntity.accepted().body(new ApiResponse(eventService.bookEvent(request), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }

    @DeleteMapping("/remove/{tickedId}")
    public ResponseEntity<?> removeSeat(@PathVariable String tickedId,
                                        @RequestParam(name = "eventId") Long eventId){
        try{
           return  ResponseEntity.ok().body(new ApiResponse(eventService.cancelBookedEvent(eventId, tickedId), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }

    @GetMapping("/events")
    public ResponseEntity<?> viewAllEvent(){
        try{
            return ResponseEntity.accepted().body(new ApiResponse(eventService.findAllEventAvailable(), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }

    @GetMapping("/tickets/{userId}")
    public ResponseEntity<?> viewAllTicket(@PathVariable Long userId){
        try {
            return ResponseEntity.ok().body(new ApiResponse(ticketService.getAllTicketFor(userId), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }



}
