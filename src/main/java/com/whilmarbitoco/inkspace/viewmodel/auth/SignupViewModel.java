package com.whilmarbitoco.inkspace.viewmodel.auth;

import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.utils.Validator;
import com.whilmarbitoco.inkspace.utils.HashHandler;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignupViewModel extends BaseViewModel  {

//    models/state
    private final StringProperty firstname = new SimpleStringProperty("");
    private final StringProperty lastname = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");

// repos/db
    private final UserRepository userRepo = new UserRepository();

    public void signup() {
        if (firstname.get().isEmpty() || lastname.get().isEmpty()
                || email.get().isEmpty() || password.get().isEmpty()) {
            error.setValue("Fields Cannot Be Empty.");
            return;
        }

        if (password.get().length() < 6) {
            error.setValue("Password should be 6 characters long.");
            return;
        }

        if (!Validator.isValidEmail(email.get())) {
            error.setValue("Invalid Email Format");
            return;
        }

        User user = userRepo.getByEmail(email.get());

        if (user != null) {
            error.setValue("Email Already Registered.");
            return;
        }

        userRepo.create(new User(firstname.get(), lastname.get(), email.get(), HashHandler.hash(password.get()), "", 2));
        message.setValue("Login Successfully");
        switchTo("auth/LoginView");
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

}
