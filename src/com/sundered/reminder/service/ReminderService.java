package  com.sundered.reminder.service;
import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class ReminderService {
    public void addReminder(User user, String title, LocalDateTime time){
        Reminder reminder = new Reminder(title, time);
        user.addReminder(reminder);
    }
    public List<Reminder> getReminders(User user){
        return user.getReminders();
    }
    public boolean deleteReminder(User user, int number) {
        List<Reminder> reminders = user.getReminders();

        if (number < 1 || number > reminders.size()) {
            return false;
        }
        reminders.remove(number - 1);
        return true;
    }

    public Reminder getReminderByNumber(User user, int number) {
        var  reminders = user.getReminders();
        if (number < 1 || number > reminders.size()) {
            return null;
        }
        return reminders.get(number - 1);
    }

    public void updateTitle (Reminder reminder, String newTitle){
        reminder.setTitle(newTitle);
    }
    public void updateTime (Reminder reminder, LocalDateTime newTime){
        reminder.setReminderTime(newTime);
    }
}
