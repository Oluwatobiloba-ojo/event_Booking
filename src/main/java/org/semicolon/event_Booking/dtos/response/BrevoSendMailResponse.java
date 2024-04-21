package org.semicolon.event_Booking.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BrevoSendMailResponse {
    private String event;
    private Long id;
    private String message_id;
}
