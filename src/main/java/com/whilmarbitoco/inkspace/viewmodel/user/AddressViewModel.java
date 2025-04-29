package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Address;
import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.AddressRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.user.UpdateAddressController;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AddressViewModel extends BaseViewModel {

    private final StringProperty labelOne = new SimpleStringProperty("Unknown Address");
    private final StringProperty addressOne = new SimpleStringProperty("Click to add an address");
    private final StringProperty labelTwo = new SimpleStringProperty("Unknown Address");
    private final StringProperty addressTwo = new SimpleStringProperty("Click to add an address");
    private final StringProperty image = new SimpleStringProperty("");

    private final AddressRepository addressRepository = new AddressRepository();
    private User current = UserStore.getInstance().getUser();

    private Address addOne;
    private Address addTwo;

    public AddressViewModel() {
        image.setValue(current.getImage());

        List<Address> addresses = addressRepository.getByUser(current.getUserID());

        if (!addresses.isEmpty()) {
            Address address = addresses.getFirst();
            addOne = address;
            labelOne.setValue(address.getLabel());
            String tmp = address.getStreet() + ", "
                    + address.getBarangay() + ", "
                    + address.getCity() + ", "
                    + address.getRegion() + ", "
                    + address.getPostal();
            addressOne.setValue(tmp);
        }

        if (addresses.size() > 1) {
            Address address = addresses.getLast();
            addTwo = address;
            labelTwo.setValue(address.getLabel());
            String tmp = address.getStreet() + ", "
                    + address.getBarangay() + ", "
                    + address.getCity() + ", "
                    + address.getRegion() + ", "
                    + address.getPostal();
            addressTwo.setValue(tmp);
        }

    }

    public void openAddressOne() {
        if (addOne != null) {
            openChild("user/UpdateAddressView", addOne);
            return;
        }
        openChild("user/UpdateAddressView", null);
    }

    public void openAddressTwo() {
        if (addTwo != null) {
            openChild("user/UpdateAddressView", addTwo);
            return;
        }
        openChild("user/UpdateAddressView", null);
    }

    public StringProperty labelOneProperty() {
        return labelOne;
    }

    public StringProperty addressOneProperty() {
        return addressOne;
    }

    public StringProperty labelTwoProperty() {
        return labelTwo;
    }

    public StringProperty addressTwoProperty() {
        return addressTwo;
    }

    public StringProperty imageProperty() {
        return image;
    }

    private void openChild(String view, Address address) {
        try {
            Stage child = new Stage();
            FXMLLoader loader = ViewHandler.getLoader(view);
            Parent parent = loader.load();
            UpdateAddressController controller = loader.getController();
            controller.setAddress(address);

            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
