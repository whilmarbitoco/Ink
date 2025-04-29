package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Store;
import com.whilmarbitoco.inkspace.repository.StoreRepository;
import com.whilmarbitoco.inkspace.repository.UserRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateStoreViewModel extends BaseViewModel {

    private final StringProperty storeName = new SimpleStringProperty("");

    private final StoreRepository storeRepository = new StoreRepository();
    private final UserRepository userRepository = new UserRepository();

    public void create() {
        if (storeName.get().isEmpty()) {
            error.setValue("Store cannot be empty.");
            return;
        }
        Store store = new Store(currentUser.getUserID(), storeName.get());
        currentUser.setRoleID(3);
        storeRepository.create(store);
        userRepository.update(currentUser);
        message.setValue("Store Created.");
    }

    public StringProperty storeNameProperty() {
        return storeName;
    }
}
