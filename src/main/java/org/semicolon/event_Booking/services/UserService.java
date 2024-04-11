package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.data.model.User;
import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;

public interface UserService {

    CreateAccountResponse createAccount(CreateAccountRequest request);

    User findBy(Long ownerId) throws UserDoesNotExistException;
}
