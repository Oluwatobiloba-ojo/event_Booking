package org.semicolon.event_Booking.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BrevoSendMailRequest {
    private BrevoSender sender;
    private List<Receipient> to = new ArrayList<>();
    private String subject;
    private String textContent;
}
