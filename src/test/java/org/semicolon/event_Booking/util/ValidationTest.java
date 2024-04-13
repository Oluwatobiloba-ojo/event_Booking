package org.semicolon.event_Booking.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ValidationTest {

    @Test
    public void testValidDate(){
       boolean isValid = Validation.isValid("2004-03-12");
       assertTrue(isValid);
    }

    @Test
    public void testInvalidDate(){
        boolean isValid = Validation.isValid("2004/03/12");
        assertFalse(isValid);
    }


}