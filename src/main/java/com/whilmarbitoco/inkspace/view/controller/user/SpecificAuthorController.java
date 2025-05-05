package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Author;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.SpecificAuthorViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SpecificAuthorController extends BaseController {

    public ImageView authorImg;
    public Label totalBooks;
    public Label usernameLabel;
    public GridPane grid;
    public Label authorName;
    public Label authorPenName;
    public Label description;

    private final SpecificAuthorViewModel viewModel = new SpecificAuthorViewModel();

    public void initialize() {
        setViewModel(viewModel);
    }
    
    protected void bindView() {
        usernameLabel.setText(viewModel.getCurrentUser().getFullName());
        authorImg.setImage(ImageHelper.load(viewModel.getAuthor().getImage()));
        authorName.setText(viewModel.getAuthor().getName());
        authorPenName.setText(viewModel.getAuthor().getPenName());
        description.setText(viewModel.getAuthor().getBiography());
        totalBooks.setText(Integer.toString(viewModel.getBooks().size()));
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


    public void setAuthor(Author author) {
        viewModel.setAuthor(author);
        bindView();
        initData();
    }
}
