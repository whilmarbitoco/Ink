package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.Review;
import com.whilmarbitoco.inkspace.repository.ReviewRepository;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class RateController extends BaseController {
    public Slider rating;
    public TextArea comment;
    public Label title;
    public Label ratingField;

    private Book book;
    private final ReviewRepository reviewRepository = new ReviewRepository();

    public void initialize() {
        ratingField.textProperty().bind(rating.valueProperty().asString("%.0f"));
    }

    protected void bindView() {

    }

    public void setBook(Book book) {
        this.book = book;

        title.setText(book.getTitle());
    }

    public void rateAction(ActionEvent actionEvent) {
        if (rating.getValue() < 1 || comment.getText().isEmpty()) {
            showError("Error", "Please fill all fields");
            return;
        }

        Review review = new Review(UserStore.getInstance().getUser().getUserID(), book.getBookID(), (int) rating.getValue(), comment.getText());
        reviewRepository.create(review);
        showInformation("Created", "Review Created.");
        close(ratingField);
    }
}
