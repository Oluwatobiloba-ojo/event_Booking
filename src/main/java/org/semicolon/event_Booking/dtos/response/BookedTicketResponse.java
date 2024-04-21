package org.semicolon.event_Booking.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.semicolon.event_Booking.data.model.Ticket;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookedTicketResponse {
    private String ticketId;
    private String eventName;
    private String description;
    private String date;
    private String email;
    private String name;


    public BookedTicketResponse(Ticket ticket){
        this.email = ticket.getEmail();
        this.name = ticket.getName();
        this.ticketId = ticket.getId();
        this.eventName = ticket.getEvent().getName();
        this.description = ticket.getEvent().getEventDescription();
        this.date = ticket.getEvent().getDate();
    }


}
