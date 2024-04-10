package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;

public interface UserService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
}
