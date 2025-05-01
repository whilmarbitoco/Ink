package com.whilmarbitoco.inkspace.view.controller.user;


import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AuthorItemController {

    public ImageView profileImage;
    public Label authorLabel;
    public Label biographyLabel;

    private Author author;

    public void setAuthor(Author author) {
        this.author = author;

        profileImage.setImage(ImageHelper.load(author.getImage()));
        authorLabel.setText(author.getName());
        biographyLabel.setText(author.getBiography());
    }

}
