package com.sundered.reminder.console;
import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;
import com.sundered.reminder.service.ReminderService;
import com.sundered.reminder.storage.FileReminderRepository;
import com.sundered.reminder.util.DateTimeUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    public static void run (List<User> users, FileReminderRepository repository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        User user  = repository.findByEmail(email, users);
        if (user == null) {
            user = new User(name, email);
            users.add(user);
            repository.saveUsers(users);
            System.out.println("New user created");} else {
            System.out.println("Welcome back, " + user.getName() + "!");
        }

        ReminderService reminderService = new ReminderService();

        while(true){
            System.out.println("\n=== MENU ===");
            System.out.println("1) Add reminder");
            System.out.println("2) Show reminders");
            System.out.println("3) Delete reminder");
            System.out.println("4) Edit reminder");
            System.out.println("0) Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                addReminderFlow(scanner, user, reminderService, users, repository);
            } else if (choice.equals("2")) {
                ConsolePrinter.printReminders(user);
            } else if (choice.equals("3")) {
                deleteReminderFlow(scanner, user, reminderService, users, repository);
            } else if (choice.equals("4")) {
                editReminderFlow(scanner, user, reminderService, users, repository);
            } else if (choice.equals("0")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Unknown option! Try again.");
            }
        }
    }


    public static void deleteReminderFlow(Scanner scanner, User user, ReminderService reminderService, List<User> users, FileReminderRepository repository) {
        if (user.getReminders().isEmpty()) {
            System.out.println("There are no reminders to delete");
            return;
        }
        ConsolePrinter.printReminders(user);

        System.out.print("Enter reminder number to delete (0 to cancel): ");
        String input = scanner.nextLine().trim();

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }

        if (number == 0) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean deleted = reminderService.deleteReminder(user, number);

        if (deleted) {
            System.out.println("Reminder deleted successfully.");
            repository.saveUsers(users);
        } else {
            System.out.println("Invalid reminder number!");
        }
    }

    public static void addReminderFlow(Scanner scanner, User user, ReminderService reminderService, List<User> users, FileReminderRepository repository) {

        System.out.print("Enter your event: ");
        String event = scanner.nextLine();

        LocalDateTime reminderTime =
                DateTimeUtil.readDateTime(scanner, "Enter reminder date/time:");

        reminderService.addReminder(user, event, reminderTime);
        repository.saveUsers(users);

        System.out.println("Reminder added.");
    }

    public static void editReminderFlow(Scanner scanner, User user, ReminderService reminderService, List<User> users, FileReminderRepository repository) {
        if (user.getReminders().isEmpty()) {
            System.out.println("There are no reminders to edit.");
            return;
        }
        ConsolePrinter.printReminders(user);
        System.out.println("Enter reminder number to edit (0 to cancel): ");
        String input = scanner.nextLine().trim();

        int number;
        try  {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }
        if (number == 0) {
            System.out.println("Edit cancelled.");
            return;
        }
        Reminder reminderToEdit = reminderService.getReminderByNumber(user, number);
        if (reminderToEdit == null) {
            System.out.println("Invalid reminder number!");
            return;
        }
        System.out.println("Selected reminder:");
        System.out.println("Event: " + reminderToEdit.getTitle());
        System.out.println("Date/Time: " + reminderToEdit.getReminderTime().format(DateTimeUtil.DATE_TIME_FORMAT));

        while(true) {
            System.out.println("\nWhat do you want to edit?");
            System.out.println("1) Title");
            System.out.println("2) Date/Time");
            System.out.println("0) Cancel");
            System.out.print("Choose option: ");

            String editChoice = scanner.nextLine().trim();
            if (editChoice.equals("1")) {
                System.out.println("Enter new reminder title: ");
                String newTitle = scanner.nextLine();
                reminderService.updateTitle(reminderToEdit, newTitle);
                System.out.println("Title updated successfully.");
                repository.saveUsers(users);
                return;
            } else if (editChoice.equals("2")) {
                LocalDateTime newDate = DateTimeUtil.readDateTime(scanner, "Enter new reminder date/time: ");
                reminderService.updateTime(reminderToEdit, newDate);
                System.out.println("Date/time updated successfully.");
                repository.saveUsers(users);
                return;
            } else if (editChoice.equals("0")) {
                System.out.println("Edit cancelled.");
                return;
            } else  {
                System.out.println("Unknown option. Please try again.");
            }
        }
    }
}
