package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequest {
    private String name;
    private Long availableAttendeesCount;
    private String eventDescription;
    private String date;
    private String category;
    private Long ownerId;
}
