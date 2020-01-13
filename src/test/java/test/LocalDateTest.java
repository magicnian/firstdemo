package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTest {

    public static void main(String[] args) {

//        LocalDateTime now = LocalDateTime.now();
//        int hour = now.getHour();
//
//        int minute = now.getMinute();
//
//        if (minute >= 5 && minute < 35) {
//            minute = 30;
//            --hour;
//        } else {
//            minute = 0;
//        }
//
//        LocalDateTime resultDateTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), hour, minute);
//
//        Date date = Date.from(resultDateTime.toInstant(ZoneOffset.of("+8")));
//        String hourAndMinute = resultDateTime.format(DateTimeFormatter.ofPattern("HHmm"));
//
//        System.out.println(hourAndMinute);

        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = 35;

        int startHour, startMinute, endHour, endMinute;
        if (minute >= 5 && minute < 35) {
            startHour = hour-1;
            startMinute = 30;
            endHour = hour;
            endMinute = 0;
        } else {
            startHour = hour;
            startMinute = 0;
            endHour = hour;
            endMinute = 30;
        }

        //LocalDateTime.now().minusMinutes(minutes).toEpochSecond(ZoneOffset.of("+8"))
        Long startTimeStamp = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), startHour, startMinute).toEpochSecond(ZoneOffset.of("+8"));
        Long endTimeStamp = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), endHour, endMinute).toEpochSecond(ZoneOffset.of("+8"));

        System.out.println(startTimeStamp);
        System.out.println(endTimeStamp);
    }
}
