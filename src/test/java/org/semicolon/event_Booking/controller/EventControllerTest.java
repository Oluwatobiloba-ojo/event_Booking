package org.semicolon.event_Booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.dtos.request.BookEventRequest;
import org.semicolon.event_Booking.dtos.request.CreateEventRequest;
import org.semicolon.event_Booking.dtos.response.ApiResponse;
import org.semicolon.event_Booking.dtos.response.BookEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ModelMapper modelMapper = new ModelMapper();

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testCreateEvent() throws Exception {
        CreateEventRequest request = new CreateEventRequest();
        request.setOwnerId(201L);
        request.setName("Event");
        request.setDate("2004-03-21");
        request.setEventDescription("It is an event");
        request.setCategory("Game");
        request.setAvailableAttendeesCount(20L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/event/create")
                .content(mapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testBookEvent() throws Exception {
        BookEventRequest request = new BookEventRequest(202L, 201L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/event/book")
                .content(mapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(result -> {
                    System.out.println(modelMapper.map(result.getResponse().getContentAsString(), BookEventResponse.class));
                });
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testCancelEvent() throws Exception {
        BookEventRequest request = new BookEventRequest(202L, 201L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/event/book")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(result -> {
                    ApiResponse eventResponse = mapper.readValue(result.getResponse().getContentAsString(), ApiResponse.class);
                    BookEventResponse response = modelMapper.map(eventResponse.getData(), BookEventResponse.class);
                    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/event/remove/" + response.getTickedId() + "/?eventId=" + 202)
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().is2xxSuccessful())
                            .andDo(print());
                });
    }



}