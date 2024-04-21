package org.semicolon.event_Booking.services;

import org.modelmapper.ModelMapper;
import org.semicolon.event_Booking.data.model.Event;
import org.semicolon.event_Booking.dtos.request.Attendance;
import org.semicolon.event_Booking.dtos.request.SendMailRequest;
import org.semicolon.event_Booking.dtos.response.SendMailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class Schedule {

    @Autowired
    private EventService eventService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ModelMapper mapper;

    @Scheduled(cron = "0 55 10 ? * *")
    public void sendRemainder() {
        System.out.println("entered oooooo");
        eventService.findAllEventOccuringNextDay()
                .forEach(event -> {
                    SendMailRequest request = new SendMailRequest();
                    request.setEventName(event.getName());
                    request.setEmail(event.getUser().getEmail());
                    request.setUsername(event.getUser().getName());
                    request.setDate(event.getDate());
                    List<Attendance> attendances = getAttendances(event);
                    request.setTo(attendances);
                    SendMailResponse response = emailService.sendMail(request);
                    System.out.println(response);
                });
    }

    private List<Attendance> getAttendances(Event event) {
        return
                eventService.findAllTicketFor(event)
                        .stream().map(ticket -> mapper.map(ticket, Attendance.class)).toList();
    }

}
