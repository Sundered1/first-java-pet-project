package com.sundered.reminder.app;
import com.sundered.reminder.console.ConsoleUI;
import com.sundered.reminder.domain.User;
import com.sundered.reminder.storage.FileReminderRepository;
import com.sundered.reminder.service.ReminderScheduler;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        FileReminderRepository repository = new FileReminderRepository();
        List<User> users = repository.loadUsers();

        ReminderScheduler scheduler = new ReminderScheduler(users);

        scheduler.start();
        ConsoleUI.run(users, repository);
    }
}