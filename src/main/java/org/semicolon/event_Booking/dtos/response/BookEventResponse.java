package org.semicolon.event_Booking.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEventResponse {
    private String message;
    private String tickedId;
    private String email;
}
