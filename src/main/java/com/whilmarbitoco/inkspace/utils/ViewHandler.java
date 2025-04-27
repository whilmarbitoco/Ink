package com.whilmarbitoco.inkspace.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ViewHandler {
    private static final String BASE_PATH = System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\whilmarbitoco\\inkspace\\view\\fxml\\";
    private static final Stage stage = new Stage();

    public static void openView(String view) {
        try {
            File fxmlFile = new File(BASE_PATH + view + ".fxml");
            if (!fxmlFile.exists()) {
                throw new IOException("FXML file not found: " + fxmlFile.getAbsolutePath());
            }

            System.out.println("[!] View: " + fxmlFile.toString());

            URL fxmlURL = fxmlFile.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
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
                System.out.println("Seller");
                break;
        }
    }
}
