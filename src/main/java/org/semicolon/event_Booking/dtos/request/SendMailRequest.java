package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SendMailRequest {
    private String email;
    private List<Attendance> to;
    private String date;
    private String eventName;
    private String username;


}
