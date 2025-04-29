package com.whilmarbitoco.inkspace.viewmodel.user;

import com.whilmarbitoco.inkspace.model.Address;
import com.whilmarbitoco.inkspace.repository.AddressRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UpdateAddressViewModel extends BaseViewModel {

    private final StringProperty label = new SimpleStringProperty("");
    private final StringProperty street = new SimpleStringProperty("");
    private final StringProperty barangay = new SimpleStringProperty("");
    private final StringProperty city = new SimpleStringProperty("");
    private final StringProperty region = new SimpleStringProperty("");
    private final StringProperty postal = new SimpleStringProperty("");

    private Address address;

    private final AddressRepository addressRepository = new AddressRepository();


    public void setAddress(Address address) {
        if (address == null) return;

        this.address = address;
        label.setValue(address.getLabel());
        street.setValue(address.getStreet());
        barangay.setValue(address.getBarangay());
        city.setValue(address.getCity());
        region.setValue(address.getRegion());
        postal.setValue(address.getPostal());
    }

    public void update() {
        if (this.address == null) {
            Address address = new Address();
            address.setUserID(currentUser.getUserID());
            address.setLabel(label.get());
            address.setStreet(street.get());
            address.setBarangay(barangay.get());
            address.setCity(city.get());
            address.setRegion(region.get());
            address.setPostal(postal.get());

            addressRepository.create(address);
            message.setValue("Address Updated.");
            return;
        }
        address.setLabel(label.get());
        address.setStreet(street.get());
        address.setBarangay(barangay.get());
        address.setCity(city.get());
        address.setRegion(region.get());
        address.setPostal(postal.get());

        addressRepository.update(address);
        message.setValue("Address Updated.");
    }

    public StringProperty labelProperty() {
        return label;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty barangayProperty() {
        return barangay;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty regionProperty() {
        return region;
    }

    public StringProperty postalProperty() {
        return postal;
    }
}
