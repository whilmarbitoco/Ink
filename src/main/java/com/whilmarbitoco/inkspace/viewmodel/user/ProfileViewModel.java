package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
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
    private User current = UserStore.getInstance().getUser();

    private final UserRepository userRepository = new UserRepository();


    public ProfileViewModel() {
        firstname.setValue(current.getFirstName());
        lastname.setValue(current.getLastName());
        email.setValue(current.getEmail());
        image.setValue(current.getImage());
    }

    public void update() {
        current.setFirstName(firstname.get());
        current.setLastName(lastname.get());
        current.setEmail(email.get());


        File sourceFile = new File(image.get());
        File destinationFile = new File("src/main/resources/" + generateRandomFileName("png"));

        try {
            Files.copy(
                    sourceFile.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
            current.setImage(destinationFile.getAbsolutePath());
            userRepository.update(current);
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
