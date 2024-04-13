package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEventRequest {
    private String userFirstName;
    private String userLastName;
    private Long eventId;
    private String userEmail;
}
