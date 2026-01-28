package com.sundered.reminder.domain;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<Reminder> reminders = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addReminders(Reminder reminder) {
        reminders.add(reminder);
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
}