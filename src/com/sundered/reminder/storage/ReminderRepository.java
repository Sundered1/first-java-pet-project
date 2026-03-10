package com.sundered.reminder.storage;
import com.sundered.reminder.domain.User;
import java.util.List;

public interface ReminderRepository {
    List<User> loadUsers();
    void saveUsers(List<User> users);
    User findByEmail(String email, List<User> users);
}
