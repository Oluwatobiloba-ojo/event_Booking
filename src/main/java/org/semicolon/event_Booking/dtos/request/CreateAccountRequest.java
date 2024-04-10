package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String name;
    private String mail;
    private String password;
}
