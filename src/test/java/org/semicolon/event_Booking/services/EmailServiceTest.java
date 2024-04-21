package org.semicolon.event_Booking.services;

import org.junit.jupiter.api.Test;
import org.semicolon.event_Booking.dtos.request.Attendance;
import org.semicolon.event_Booking.dtos.request.SendMailRequest;
import org.semicolon.event_Booking.dtos.response.SendMailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendMail(){
        SendMailRequest sendMailRequest = new SendMailRequest();
        List<Attendance> attendances = List.of(
                new Attendance("ope", "opeoluwaagnes@gmail.com"),
                new Attendance("tobi", "ojot630@gmail.com")
        );
        sendMailRequest.setEmail("ojot630@gmail.com");
        sendMailRequest.setTo(attendances);
        sendMailRequest.setDate("2024-12-20");
        sendMailRequest.setUsername("Tobi");
        sendMailRequest.setEventName("Tech event");
        SendMailResponse response = emailService.sendMail(sendMailRequest);
        assertNotNull(response);
    }


}