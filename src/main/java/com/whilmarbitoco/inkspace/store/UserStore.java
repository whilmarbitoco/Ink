package com.whilmarbitoco.inkspace.store;

import com.whilmarbitoco.inkspace.model.User;


public class UserStore {

    private static UserStore state;
    private User currentUser;

    private UserStore() {}

    public static UserStore getInstance() {
        if (state == null) {
            state = new UserStore();
        }
        return state;
    }

    public void setUser(User user) {
        currentUser = user;
    }

    public User getUser() {
        return currentUser;
    }
}
