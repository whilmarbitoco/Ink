package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ProfileViewModel extends BaseViewModel {

    private final StringProperty firstname = new SimpleStringProperty("");
    private final StringProperty lastname = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty image = new SimpleStringProperty("");

    
    private final UserRepository userRepository = new UserRepository();


    public ProfileViewModel() {
        firstname.setValue(currentUser.getFirstName());
        lastname.setValue(currentUser.getLastName());
        email.setValue(currentUser.getEmail());
        image.setValue(currentUser.getImage());
        
    }

    public void update() {
        currentUser.setFirstName(firstname.get());
        currentUser.setLastName(lastname.get());
        currentUser.setEmail(email.get());

        try {
            String destination = ImageHelper.save(image.get());
            currentUser.setImage(destination);
            userRepository.update(currentUser);
            message.setValue("Successfully Updated.");
        } catch (IOException e) {
            error.setValue("Error Uploading file");
        }
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

    public StringProperty imageProperty() {
        return image;
    }
}
