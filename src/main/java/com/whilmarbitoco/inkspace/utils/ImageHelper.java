package com.whilmarbitoco.inkspace.utils;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageHelper {

    public static String save(String image) throws IOException {
        File sourceFile = new File(image);
        File destinationFile = new File("src/main/resources/" + HashHandler.generateFilename("png"));

        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destinationFile.getAbsolutePath();
    }

    public static Image load(String image) {
        return new Image(new File(image).toURI().toString());
    }

}
