package com.ciphereck.omofonia.managers;

import com.ciphereck.omofonia.model.User;

public class UserManager {
    private static volatile User user;

    private UserManager() {
        if(user != null) {
            throw new RuntimeException("Use setInstance() method to get the single instance of this class.");
        }
    }

    public static void setInstance(User userObj) {
        if(user == null) {
            synchronized (UserManager.class) {
                if(user == null) {
                    user = userObj;
                }
            }
        }
    }

    public static User getInstance() {
        if(user == null) {
            synchronized (UserManager.class) {
                if(user == null) {
                    throw new RuntimeException("Use setInstance() method to get the single instance of this class.");
                }
            }
        }

        return user;
    }
}
