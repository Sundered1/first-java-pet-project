package com.sundered.reminder.console;
import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;
import com.sundered.reminder.util.DateTimeUtil;
import java.util.List;

public class ConsolePrinter {
    public static void printReminders(User user) {
        List<Reminder> reminders = user.getReminders();
        if (reminders.isEmpty()) {
            System.out.println("No reminders found.");
            return;
        }
        System.out.println("\nReminders:");
        for (int i  = 0; i < reminders.size(); i++) {
            Reminder r  = reminders.get(i);
            int number = i + 1;

            System.out.println(number + ") Event: " + r.getTitle());
            System.out.println("   Date/Time: " + r.getReminderTime().format(DateTimeUtil.DATE_TIME_FORMAT));
            System.out.println("----");
        }
    }
}
