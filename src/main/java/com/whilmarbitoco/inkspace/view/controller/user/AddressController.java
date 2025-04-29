package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.AddressViewModel;
import com.whilmarbitoco.inkspace.viewmodel.user.ProfileViewModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddressController extends BaseController {


    public ImageView profileImage;
    public Label labelOne;
    public Label addressOne;
    public Label labelTwo;
    public Label addressTwo;

    private final AddressViewModel viewModel = new AddressViewModel();


    public void initialize() {
        bindView();
        setViewModel(viewModel);
    }

    protected void bindView() {
        profileImage.setImage(new Image(new File(viewModel.imageProperty().get()).toURI().toString()));
        labelOne.textProperty().bindBidirectional(viewModel.labelOneProperty());
        addressOne.textProperty().bindBidirectional(viewModel.addressOneProperty());

        labelTwo.textProperty().bindBidirectional(viewModel.labelTwoProperty());
        addressTwo.textProperty().bindBidirectional(viewModel.addressTwoProperty());
    }

    public void switchToProfile(ActionEvent actionEvent) {
        ViewHandler.openChildView("user/ProfileView");
    }


    public void addressOneAction(MouseEvent mouseEvent) {
        viewModel.openAddressOne();
    }

    public void addressTwoAction(MouseEvent mouseEvent) {
        viewModel.openAddressTwo();
    }
}
