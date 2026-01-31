package com.sundered.reminder.domain;

public class Reminder {

    private String title;
    private String reminderTime;
    private ReminderStatus status;

    public Reminder(String title, String reminderTime) {
        this.title = title;
        this.reminderTime = reminderTime;
        this.status = ReminderStatus.PENDING;
    }

    public String getTitle() {
        return title;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
}
