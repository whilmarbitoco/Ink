package com.whilmarbitoco.inkspace;

import com.whilmarbitoco.inkspace.model.User;
import com.whilmarbitoco.inkspace.repository.SessionRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    private final SessionRepository sessionRepo = new SessionRepository();

    @Override
    public void start(Stage stage) throws Exception {
        Optional<User> currentUser = sessionRepo.getCurrentUser();
        if (currentUser.isEmpty()) {
            ViewHandler.openView("auth/LoginView");
            return;
        }

        UserStore.getInstance().setUser(currentUser.get());
        ViewHandler.handleRole(currentUser.get().getRoleID());
//        ViewHandler.openView("seller/SellerView");

//        ViewHandler.openView("user/BookDetailView");
    }



    public static void main(String[] args) {
        launch(args);
    }
}
