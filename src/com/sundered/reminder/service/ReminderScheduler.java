package com.sundered.reminder.service;

import com.sundered.reminder.domain.Reminder;
import com.sundered.reminder.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReminderScheduler {

    private final List<User> users;

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public ReminderScheduler(List<User> users) {
        this.users = users;
    }

    public void start() {

        scheduler.scheduleAtFixedRate(() -> {

            LocalDateTime now = LocalDateTime.now();

            for (User user : users) {

                for (Reminder reminder : user.getReminders()) {

                    if (reminder.getReminderTime().isBefore(now)) {

                        System.out.println();
                        System.out.println("🔔 REMINDER for " + user.getName());
                        System.out.println("Task: " + reminder.getTitle());
                        System.out.println("Time: " + reminder.getReminderTime());
                        System.out.println();

                    }
                }
            }

        }, 0, 30, TimeUnit.SECONDS);
    }
}