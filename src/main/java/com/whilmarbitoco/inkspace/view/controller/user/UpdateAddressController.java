package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Address;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.UpdateAddressViewModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UpdateAddressController extends BaseController {

    public TextField labelField;
    public TextField streetField;
    public TextField barangayField;
    public TextField cityField;
    public TextField regionField;
    public TextField postalField;
    public Button updateBtn;
    public Label headerLabel;

    private final UpdateAddressViewModel viewModel = new UpdateAddressViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        viewModel.messageProperty().addListener((obs, prev, now) -> {
            close(headerLabel);
        });
    }

    protected void bindView() {
        labelField.textProperty().bindBidirectional(viewModel.labelProperty());
        streetField.textProperty().bindBidirectional(viewModel.streetProperty());
        barangayField.textProperty().bindBidirectional(viewModel.barangayProperty());
        cityField.textProperty().bindBidirectional(viewModel.cityProperty());
        regionField.textProperty().bindBidirectional(viewModel.regionProperty());
        postalField.textProperty().bindBidirectional(viewModel.postalProperty());

    }

    public void setAddress(Address address) {
        viewModel.setAddress(address);
    }

    public void updateAction(ActionEvent actionEvent) {
        viewModel.update();
    }

}
