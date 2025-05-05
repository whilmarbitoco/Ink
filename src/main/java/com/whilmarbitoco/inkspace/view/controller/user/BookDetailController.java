package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.BookDetail;
import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.model.Edition;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.BookDetailViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookDetailController extends BaseController {


    public ImageView img;
    public Label store;
    public Label title;
    public Label price;
    public Label avg;
    public Label qty;
    public Label total;
    public Label desc;
    public ChoiceBox<Cover> coverBox;
    public ChoiceBox<Edition> editionBox;
    public GridPane girdReviews;

    private final BookDetailViewModel viewModel = new BookDetailViewModel();

    public void initialize() {
        setViewModel(viewModel);
        initData();
    }

    protected void bindView() {
        Book b = viewModel.getBook();
        BookDetail d = viewModel.getDetail();

        img.setImage(ImageHelper.load(b.getImage()));
        title.setText(b.getTitle());
        price.setText(b.getPrice().toString());
        desc.setText(d.getDescription());

        coverBox.setItems(viewModel.getCovers());
        editionBox.setItems(viewModel.getEditions());

        viewModel.quantityProperty().addListener((o, p, n) -> {
            qty.setText(Integer.toString(n.intValue()));

            float total = viewModel.getBook().getPrice().floatValue() * n.intValue();
            viewModel.totalProperty().setValue(total);
            this.total.setText(Float.toString(total));
        });
    }

    public void initData() {
        girdReviews.getColumnConstraints().clear();
        girdReviews.getRowConstraints().clear();
        girdReviews.getChildren().clear();

        try {
            for (int i = 0; i < 5; i++) {

                FXMLLoader loader = ViewHandler.getLoader("user/ReviewItem");
                HBox pane = loader.load();
                girdReviews.add(pane, 0, i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setBook(Book book) {
        viewModel.setBook(book);
        bindView();
    }

    public void minusAction(ActionEvent actionEvent) {
        int val = viewModel.getQuantity() - 1;
        if (val < 1) return;
        viewModel.quantityProperty().setValue(val);
    }

    public void addAction(ActionEvent actionEvent) {
        viewModel.quantityProperty().setValue(viewModel.getQuantity() + 1);
    }

    public void addCartAction(ActionEvent actionEvent) {
        viewModel.addCart();
    }

    public void coverAction(ActionEvent actionEvent) {
        Cover selected = coverBox.getValue();
        if (selected == null) return;

        viewModel.setSelectedCover(selected);
    }

    public void editionAction(ActionEvent actionEvent) {
        Edition selected = editionBox.getValue();
        if (selected == null) return;

        viewModel.setSelectedEdition(selected);
    }

    public void buyAction(ActionEvent actionEvent) {
        viewModel.buy();
    }
}
