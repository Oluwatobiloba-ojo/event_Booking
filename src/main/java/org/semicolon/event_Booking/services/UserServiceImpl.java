package org.semicolon.event_Booking.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.User;
import org.semicolon.event_Booking.data.repository.UserRepository;
import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;
import org.semicolon.event_Booking.exception.UserDoesNotExistException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        User newUser = modelMapper.map(request, User.class);
        User savedUser = repository.save(newUser);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setMessage(String.format("%s account has been created with this identification %d", savedUser.getName(), savedUser.getId()));
        return response;
    }

    @Override
    public User findBy(Long ownerId) throws UserDoesNotExistException {
       return repository.findById(ownerId)
               .orElseThrow(() -> new UserDoesNotExistException("User does not exist"));
    }

}
