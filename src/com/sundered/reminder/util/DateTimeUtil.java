package com.sundered.reminder.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime readDateTime(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.println("Format: yyyy-MM-dd HH:mm (Example: 2026-01-29 16:30)");
            String input = scanner.nextLine().trim();

            try {
                return LocalDateTime.parse(input, DATE_TIME_FORMAT);
            }  catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format. Please try again.");
            }
        }
    }
}
