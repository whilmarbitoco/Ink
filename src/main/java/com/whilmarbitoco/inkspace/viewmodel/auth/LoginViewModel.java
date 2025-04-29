package com.whilmarbitoco.inkspace.viewmodel.auth;

import com.whilmarbitoco.inkspace.model.Session;
import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.SessionRepository;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.utils.Validator;
import com.whilmarbitoco.inkspace.utils.Hasher;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class LoginViewModel extends BaseViewModel {

//    models
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");

//    Repos
    private final UserRepository userRepo = new UserRepository();
    private final SessionRepository sessionRepo = new SessionRepository();


    public void login() {
        if (email.get().isEmpty() || password.get().isEmpty()) {
            error.setValue("Fields Cannot Be Empty.");
            return;
        }

        if (!Validator.isValidEmail(email.get())) {
            error.setValue("Invalid Email Format.");
            return;
        }

        User user = userRepo.getByEmail(email.get());

        if (user == null) {
            error.setValue("Email Not Registered.");
            return;
        }

        if (!Hasher.compare(password.get(), user.getPassword()))  {
            error.setValue("Invalid Email or Password.");
            return;
        }

        sessionRepo.create(new Session(user.getUserID(), UUID.randomUUID().toString()));
        UserStore.getInstance().setUser(user);
        ViewHandler.handleRole(user.getRoleID());

    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
