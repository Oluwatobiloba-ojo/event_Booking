package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String name;
    private String email;
    private String password;
}
