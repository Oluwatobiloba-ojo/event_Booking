package org.semicolon.event_Booking.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    private String userEmail;
    private String userLastName;
    private String userFirstName;
    @ManyToOne
    private Event event;
}
