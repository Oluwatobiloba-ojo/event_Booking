package org.semicolon.event_Booking.services;

import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.dtos.request.BrevoSendMailRequest;
import org.semicolon.event_Booking.dtos.request.BrevoSender;
import org.semicolon.event_Booking.dtos.request.Receipient;
import org.semicolon.event_Booking.dtos.request.SendMailRequest;
import org.semicolon.event_Booking.dtos.response.BrevoSendMailResponse;
import org.semicolon.event_Booking.dtos.response.SendMailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.semicolon.event_Booking.exception.GlobalException.MESSAGE_REMAINDER_CONTENT;

@Service
public class AppEmailService implements EmailService{
    @Autowired
    private ModelMapper mapper;
    @Value("${BREVO_API_KEY}")
    private String apiKey;
    @Override
    public SendMailResponse sendMail(SendMailRequest sendMailRequest) {
        String url = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> httpEntity = createEntity(sendMailRequest);

        ResponseEntity<BrevoSendMailResponse> response1 =
                restTemplate.postForEntity(url, httpEntity, BrevoSendMailResponse.class);

        SendMailResponse response = new SendMailResponse();
        response.setMessage(response1.getBody().getEvent());

        return response;
    }

    private HttpEntity<?> createEntity(SendMailRequest sendMailRequest) {
        BrevoSendMailRequest request = createMailRequest(sendMailRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT, "application/json");
        headers.set("api-key", apiKey);

        return new HttpEntity<>(request, headers);
    }

    private BrevoSendMailRequest createMailRequest(SendMailRequest sendMailRequest) {
        BrevoSender sender = new BrevoSender();
        sender.setEmail(sendMailRequest.getEmail());
        sender.setName(sendMailRequest.getUsername());
        List<Receipient> recipient = sendMailRequest.getTo().stream()
                .map(x->mapper.map(x,Receipient.class)).toList();
        BrevoSendMailRequest request = new BrevoSendMailRequest();
        request.setTextContent(String.format(MESSAGE_REMAINDER_CONTENT, sendMailRequest.getDate(),
                sendMailRequest.getEventName()));
        request.setSender(sender);
        request.setTo(recipient);
        request.setSubject("Event Remainder");
        return request;
    }


}
