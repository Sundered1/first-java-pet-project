package com.sundered.reminder.app;

import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;

import java.time.LocalDateTime;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        User user = new User(name, email);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Add reminder");
            System.out.println("2) Show reminders");
            System.out.println("3) Delete reminder");
            System.out.println("4) Edit reminder");
            System.out.println("0) Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.println("Enter your event:");
                String event = scanner.nextLine();

                LocalDateTime reminderTime = readDateTime(scanner, "Enter reminder date/time:");

                Reminder reminder = new Reminder(event, reminderTime);
                user.addReminder(reminder);

            } else if (choice.equals("2")) {
                printReminders(user);
            } else if (choice.equals("3")) {
                deleteReminder(scanner, user);
            }

            //EDIT REMINDERS
            else if (choice.equals("4")) {
                var reminders = user.getReminders();
                if (reminders.isEmpty()) {
                    System.out.println("There is no reminders to edit.");
                    continue;
                }
                printReminders(user);
                System.out.print("Enter reminder number to edit (0 to cancel): ");
                String input = scanner.nextLine().trim();
                int number;
                try {
                    number = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                    continue;
                }
                if (number == 0) {
                    System.out.println("Edit cancelled.");
                    continue;
                }
                if (number < 1 || number > reminders.size()) {
                    System.out.println("invalid reminder number");
                    continue;
                }
                Reminder reminderToEdit = reminders.get(number - 1);
                System.out.println("Selected reminder:");
                System.out.println("Event: " + reminderToEdit.getTitle());
                System.out.println("Date/Time: " + reminderToEdit.getReminderTime());

                while (true) {
                    System.out.println("\nWhat do you want to edit?");
                    System.out.println("1) Title");
                    System.out.println("2) Date/Time");
                    System.out.println("0) Cancel");
                    System.out.print("Choose option: ");

                    String editChoice = scanner.nextLine().trim();
                    if (editChoice.equals("1")) {
                        System.out.println("Enter new reminder title: ");
                        String newTitle = scanner.nextLine();

                        reminderToEdit.setTitle(newTitle);
                        System.out.println("Title updated successfully.");
                        break;
                    } else if (editChoice.equals("2")) {

                        LocalDateTime newDate = readDateTime(scanner, "Enter new reminder date/time:");
                        reminderToEdit.setReminderTime(newDate);

                        System.out.println("Date/time updated successfully.");
                        break;

                    } else if (editChoice.equals("0")) {
                        System.out.println("Edit cancelled.");
                        break;
                    } else {
                        System.out.println("Unknown option.");
                    }
                }
            }

             else if (choice.equals("0")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Unknown option! Try again.");
            }
        }
    }

    private static void printReminders(User user) {

        var reminders = user.getReminders();

        if (reminders.isEmpty()) {
            System.out.println("No reminders found!");
            return;
        }

        System.out.println("\nReminders:");

        for (int i = 0; i < reminders.size(); i++) {
            Reminder r = reminders.get(i);
            int number = i + 1;

            System.out.println(number + ") Event: " + r.getTitle());
            System.out.println("   Date/Time: " + r.getReminderTime().format(DATE_TIME_FORMAT));
            System.out.println("----");
        }
    }

    private static void deleteReminder(Scanner scanner, User user) {
        var reminders = user.getReminders();
        if (reminders.isEmpty()) {
            System.out.println("No reminders to delete!");
            return;
        }
        printReminders(user);
        System.out.println("Enter reminder number to delete (0 to cancel): ");
        String input = scanner.nextLine().trim();

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid reminder number!");
            return;
        }

        if (number == 0) {
            System.out.println("Delete cancelled.");
            return;
        }

        if (number < 1 || number > reminders.size()) {
            System.out.println("Invalid reminder number!");
            return;
        }

        reminders.remove(number - 1);
        System.out.println("Reminder deleted.");
    }

    //PARSING localDateTime
 private static LocalDateTime readDateTime(Scanner scanner, String prompt) {
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

     while (true) {
         System.out.println(prompt);
         System.out.println("Format: yyyy-MM-dd HH:mm (Example: 2026-01-29 16:30)");

         String input = scanner.nextLine().trim();

         try {
             return LocalDateTime.parse(input, formatter);
         } catch (DateTimeParseException e) {
             System.out.println("Invalid date/time format. Please try again.");
         }
     }
 }
}
