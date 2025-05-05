package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.model.Genre;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.SpecificAuthorViewModel;
import com.whilmarbitoco.inkspace.viewmodel.user.SpecificGenreViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SpecificGenreController extends BaseController {


    public GridPane grid;
    public Label usernameLabel;
    public Label genreField;
    public Label description;

    private final SpecificGenreViewModel viewModel = new SpecificGenreViewModel();

    public void initialize() {
        setViewModel(viewModel);
        usernameLabel.setText(viewModel.getCurrentUser().getFullName());
    }
    
    protected void bindView() {
        genreField.setText(viewModel.getGenre().getGenre());
        description.setText(viewModel.getGenre().getDescription());
    }

    public void setGenre(Genre genre) {
        viewModel.setGenre(genre);
        bindView();
        initData();
    }

    private void initData() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        try {
            int row = 0;
            int col = 0;

            for (int i = 0; i < viewModel.getBooks().size(); i++) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/BookItem");
                VBox pane = loader.load();
                BookItemController controller = loader.getController();
                controller.setBook(viewModel.getBooks().get(i));
                grid.add(pane, col++, row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
