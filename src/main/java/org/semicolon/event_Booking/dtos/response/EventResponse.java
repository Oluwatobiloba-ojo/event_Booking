package org.semicolon.event_Booking.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.semicolon.event_Booking.data.model.Category;

@Getter
@Setter
public class EventResponse {
    private String name;
    private Long availableAttendeesCount;
    private String eventDescription;
    private Long id;
    private String date;
    private Category category;
}
