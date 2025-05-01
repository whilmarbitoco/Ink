package com.whilmarbitoco.inkspace.viewmodel;

import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.SessionRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

abstract public class BaseViewModel {

    private final SessionRepository sessionRepo = new SessionRepository();

    protected final StringProperty error = new SimpleStringProperty(null);
    protected final StringProperty message = new SimpleStringProperty(null);

    protected final User currentUser = UserStore.getInstance().getUser();

    public void switchTo(String view) {
        ViewHandler.openView(view);
    }

    public void logout() {
        sessionRepo.deleteAll();
        System.exit(0);
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty messageProperty() {
        return message;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
