package org.semicolon.event_Booking.dtos.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse{
    private Object data;
    private boolean isSuccessful;
}
