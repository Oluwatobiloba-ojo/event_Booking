package org.semicolon.event_Booking.services;

import org.semicolon.event_Booking.dtos.request.SendMailRequest;
import org.semicolon.event_Booking.dtos.response.SendMailResponse;

public interface EmailService {


    SendMailResponse sendMail(SendMailRequest sendMailRequest);

}
