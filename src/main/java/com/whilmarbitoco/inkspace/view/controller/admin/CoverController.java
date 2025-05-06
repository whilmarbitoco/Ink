package com.whilmarbitoco.inkspace.view.controller.admin;

import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.admin.CoverViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CoverController extends BaseController {

    public TableView<Cover> tableView;

    private final CoverViewModel viewModel = new CoverViewModel();
    public TableColumn<Cover, Integer> coverID;
    public TableColumn<Cover, String> cover;
    public TextField coverField;

    public void initialize() {
        bindView();
        setViewModel(viewModel);
        coverID.setCellValueFactory(new PropertyValueFactory<>("coverID"));
        cover.setCellValueFactory(new PropertyValueFactory<>("cover"));
    }

    protected void bindView() {
        tableView.setItems(viewModel.getCovers());
    }

    public void openGenre(ActionEvent actionEvent) {
        viewModel.switchTo("admin/GenreView");
    }

    public void openAuthor(ActionEvent actionEvent) {
        viewModel.switchTo("admin/AuthorView");
    }

    public void addCover(ActionEvent actionEvent) {
        if (coverField.getText().isEmpty()) {
            showError("Error", "Please fill the cover field");
            return;
        }

        viewModel.create(coverField.getText());
        viewModel.fetch();
        coverField.setText("");
    }
}
