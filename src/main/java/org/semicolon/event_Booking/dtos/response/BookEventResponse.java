package org.semicolon.event_Booking.dtos.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEventResponse{
    private String message;
    private String tickedId;
    private Long userId;
}
