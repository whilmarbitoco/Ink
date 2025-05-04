package com.whilmarbitoco.inkspace.view.controller.seller.create;

import com.whilmarbitoco.inkspace.factory.CreateBookFactory;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.create.BookInfoViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;

public class BookInfoController extends BaseController {

    public TextField titleField;
    public TextField priceField;
    public TextField quantityField;
    public Label filePath;
    public DatePicker publishedField;
    public TextField publisherField;
    public TextArea descriptionField;

    private final BookInfoViewModel viewModel = CreateBookFactory.createBookInfoVM();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        publishedField.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                viewModel.dateProperty().set(Date.valueOf(newDate));
            }
        });

        viewModel.dateProperty().addListener((obs, o, n) -> {
            if (n != null) {
                publishedField.setValue(n.toLocalDate());
            }
        });
    }

    protected void bindView() {
        titleField.textProperty().bindBidirectional(viewModel.titleProperty());
        priceField.textProperty().bindBidirectional(viewModel.priceProperty());
        quantityField.textProperty().bindBidirectional(viewModel.quantityProperty());
        filePath.textProperty().bindBidirectional(viewModel.filepathProperty());
        publishedField.valueProperty().bindBidirectional(viewModel.publishedProperty());
        publisherField.textProperty().bindBidirectional(viewModel.publisherProperty());
        descriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());

    }

    public void fileUpload(ActionEvent actionEvent) {
        String path = fileChooser(filePath);

        if (path == null) {
            filePath.setText("No such file selected");
            return;
        }

        filePath.setText(path);
        viewModel.filepathProperty().setValue(path);
    }

    public void cancelAction(ActionEvent actionEvent) {
        viewModel.switchTo("seller/BookView");
    }

    public void nextAction(ActionEvent actionEvent) {
            viewModel.changeView();
    }
}
