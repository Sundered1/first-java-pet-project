package com.sundered.reminder.storage;
import com.sundered.reminder.domain.User;
import java.util.ArrayList;
import java.util.List;

public class FileReminderRepository implements ReminderRepository {

    private static final String FILE_PATH = "users.json";

    @Override
    public List<User> loadUsers() {
        return JsonUtil.readUsers(FILE_PATH);
    }

    @Override
    public void saveUsers(List<User> users) {
        JsonUtil.writeUsers(FILE_PATH, users);
    }

    @Override
    public User findByEmail(String email, List<User> users){
        for (User user : users){
            if(user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }
}
