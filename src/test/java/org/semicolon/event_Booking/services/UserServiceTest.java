package org.semicolon.event_Booking.services;

import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateAccount(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("user");
        request.setMail("test@email.com");
        request.setPassword("password");
        CreateAccountResponse response = userService.createAccount(request);
        assertThat(response).isNotNull();
    }
    

}