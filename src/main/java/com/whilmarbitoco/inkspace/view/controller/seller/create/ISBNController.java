package com.whilmarbitoco.inkspace.view.controller.seller.create;

import com.whilmarbitoco.inkspace.model.Edition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ISBNController {
    public Label isbn;
    public Label text;

    private Edition edition;
    private DelEdition deleteEdition;

    public void setEdition(Edition edition) {
        this.edition = edition;
        isbn.setText(edition.getIsbn());
        text.setText(edition.getEdition());
    }

    public void setListener(DelEdition del) {
        deleteEdition = del;
    }

    public void delete(ActionEvent actionEvent) {
        deleteEdition.deleteEdition(edition);
    }
}
