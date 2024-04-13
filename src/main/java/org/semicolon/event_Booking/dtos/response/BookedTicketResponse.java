package org.semicolon.event_Booking.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.semicolon.event_Booking.data.model.Ticket;

@Getter
@Setter
public class BookedTicketResponse {
    private String ticketId;
    private String eventName;
    private String description;
    private String date;


    public BookedTicketResponse(Ticket ticket){
        this.ticketId = ticket.getId();
        this.eventName = ticket.getEvent().getName();
        this.description = ticket.getEvent().getEventDescription();
        this.date = ticket.getEvent().getDate();
    }

}
