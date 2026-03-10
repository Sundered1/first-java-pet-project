package com.sundered.reminder.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundered.reminder.domain.User;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public static List<User> readUsers(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        } try {
            User[] users = mapper.readValue(file, User[].class);
            return new ArrayList<>(Arrays.asList(users));
        } catch (IOException e) {
            System.out.println("Warning: users.json is corrupted. Starting with empty data.");
            return new ArrayList<>();
        }
    }

    public static void writeUsers(String filePath, List<User> users) {
        try {

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), users);

        } catch (IOException e) {
            throw new RuntimeException("Error writing users.json", e);
        }
    }
}