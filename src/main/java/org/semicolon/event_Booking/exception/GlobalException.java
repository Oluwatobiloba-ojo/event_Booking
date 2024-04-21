package org.semicolon.event_Booking.exception;

public class GlobalException {

    public static final String INVALID_DATE = "%s Invalid date format yyyy-mm-dd";
    public static final String INVALID_EVENT = "Event does not exist";
    public static final String EVENT_OUT_OF_BOUND = "Event already filled up";
    public static final String SUCCESSFUL_BOOKING_EVENT = "Event already booked";
    public static final String INVALID_TICKET = "Ticket does not exist";

    public static final String MESSAGE_REMAINDER_CONTENT = """
            <html><head></head><body>
            Hello
            <p>This is a quick reminder of the event booked for tomorrow.</p>
            Date: %s %n
            Event name: %s
            </body></html>
            """;
}
