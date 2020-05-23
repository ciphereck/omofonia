package com.ciphereck.omofonia.managers;

import com.ciphereck.omofonia.model.User;

import io.reactivex.rxjava3.core.Observable;

public class UserManager {
    private static volatile User user;

    private UserManager() {
        if(user != null) {
            throw new RuntimeException("Use setInstance() method to get the single instance of this class.");
        }
    }

    public static Observable<Integer> setInstance(User userObj) {
        if(user == null) {
            synchronized (UserManager.class) {
                if(user == null) {
                    user = userObj;
                    return Observable.just(1);
                }
            }
        }
        return Observable.just(0);
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

    public static boolean instanceExist() {
        synchronized (UserManager.class) {
            return user != null;
        }
    }
}
