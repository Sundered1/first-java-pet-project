package com.sundered.reminder.domain;

import java.time.LocalDateTime;

public class Reminder {

    private String title;
    private LocalDateTime reminderTime;
    private ReminderStatus status;

    public Reminder(String title, LocalDateTime reminderTime) {
        this.title = title;
        this.reminderTime = reminderTime;
        this.status = ReminderStatus.PENDING;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
