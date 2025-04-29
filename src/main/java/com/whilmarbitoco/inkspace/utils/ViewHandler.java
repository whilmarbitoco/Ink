package com.whilmarbitoco.inkspace.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class ViewHandler {
    private static final String BASE_PATH = System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\whilmarbitoco\\inkspace\\view\\fxml\\";

    private static final Stage stage = new Stage();
    private static final Stage child = new Stage();


    public static void openView(String view) {
        try {
            FXMLLoader loader = getLoader(view);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openChildView(String view) {
        try {
            FXMLLoader loader = getLoader(view);
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FXMLLoader getLoader(String view) throws IOException {

        File fxmlFile = new File(BASE_PATH + view + ".fxml");
        if (!fxmlFile.exists()) {
            throw new IOException("FXML file not found: " + fxmlFile.getAbsolutePath());
        }

        URL fxmlURL = fxmlFile.toURI().toURL();
        System.out.println("[!] " + LocalTime.now().toString() + " -  View: " + fxmlFile.toString());
        return new FXMLLoader(fxmlURL);

    }

    public static void handleRole(int roleID) {
        switch (roleID) {
            case 1:
                System.out.println("Admin");
                break;

            case 2:
                openView("user/BookView");
                break;

            case 3:
                openView("user/StoreView");
                break;
        }
    }
}
