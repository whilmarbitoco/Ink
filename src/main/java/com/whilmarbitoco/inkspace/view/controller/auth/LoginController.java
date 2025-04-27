package com.whilmarbitoco.inkspace.view.controller.auth;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.auth.LoginViewModel;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController extends BaseController {
    
    public TextField emailField;
    public PasswordField passwordField;
    public TextField passwordVisibleField;

    private final LoginViewModel viewModel = new LoginViewModel();

    private boolean showPass = true;

    public void initialize() {
        bindView();
        setViewModel(viewModel);
    }

    protected void bindView() {
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        passwordVisibleField.textProperty().bindBidirectional(viewModel.passwordProperty());
    }

    public void login(MouseEvent mouseEvent) {
        viewModel.login();
    }

    public void switchTo(MouseEvent mouseEvent) {
        viewModel.switchTo("auth/SignupView");
    }

    public void togglePassword(MouseEvent mouseEvent) {
        showPass = !showPass;
        if (showPass) {
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
            return;
        }
        passwordField.setVisible(false);
        passwordField.setManaged(false);
        passwordVisibleField.setVisible(true);
        passwordVisibleField.setManaged(true);
    }
}
