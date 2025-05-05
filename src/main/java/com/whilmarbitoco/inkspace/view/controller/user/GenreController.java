package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.GenreViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GenreController extends BaseController {

    public GridPane grid;
    public TextField searchField;
    public Label usernameLabel;

    private final GenreViewModel viewModel = new GenreViewModel();

    public void initialize() {

        Platform.runLater(() -> grid.requestFocus());
        setViewModel(viewModel);
        bindView();
        initData();
        usernameLabel.setText(viewModel.getCurrentUser().getFullName());

        PauseTransition pause = new PauseTransition(Duration.millis(300));
//        debouncing
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            pause.setOnFinished(event -> {
                viewModel.search(newText);
                initData();
            });
            pause.playFromStart();
        });
    }

    protected void bindView() {

    }

    private void initData() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        try {
            int row = 0;
            int col = 0;

            for (int i = 0; i < viewModel.getGenreList().size(); i++) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/GenreItem");
                VBox pane = loader.load();

                GenreItemController controller = loader.getController();
                controller.setGenre(viewModel.getGenreList().get(i));
                grid.add(pane, col++, row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
