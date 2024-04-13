package org.semicolon.event_Booking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {


    public static boolean isValid(String date){
         boolean isValid = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
           Date date1 = dateFormat.parse(date);
            System.out.println(date1);
            dateFormat.setLenient(false);
            isValid = true;
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        return isValid;
    }
}
