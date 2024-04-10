package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.data.model.User;
import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        User user = new User();
        return null;
    }
}
