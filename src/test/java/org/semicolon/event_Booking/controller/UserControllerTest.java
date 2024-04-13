package org.semicolon.event_Booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCreateAccount() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("name");
        request.setMail("test@gmail.com");
        request.setPassword("password");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/account")
                .content(mapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}