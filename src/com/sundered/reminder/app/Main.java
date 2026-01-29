package com.sundered.reminder.app;

import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        User user = new User(name, email);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Add reminder");
            System.out.println("2) Show reminders");
            System.out.println("3) Delete reminder");
            System.out.println("0) Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.println("Enter your event:");
                String event = scanner.nextLine();

                System.out.println("Enter your date/time:");
                String reminderTime = scanner.nextLine();

                Reminder reminder = new Reminder(event, reminderTime);
                user.addReminder(reminder);
            } else if (choice.equals("2")) {
                printReminders(user);
            } else if (choice.equals("3")) {
                deleteReminder(scanner, user);
            } else if (choice.equals("0")) {
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
            System.out.println("   Date/Time: " + r.getReminderTime());
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
            System.out.println("Please  enter a valid reminder number!");
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
}