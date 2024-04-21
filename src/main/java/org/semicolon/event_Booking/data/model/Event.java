package org.semicolon.event_Booking.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Long availableAttendeesCount;
    private String eventDescription;
    private String date;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private User user;
}
