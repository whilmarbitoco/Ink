package com.whilmarbitoco.inkspace.view.controller.seller;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.model.BookDetail;
import com.whilmarbitoco.inkspace.repository.BookDetailRepository;
import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Date;

public class BookInformationController extends BaseController {
    
    public ImageView bookImg;
    public TextField titleField;
    public TextArea descriptionArea;
    public TextField priceField;
    public TextField quantityField;
    public Button updateBtn;
    public TextField publisherField;
    public DatePicker publishedField;
    private String path;

    private Book book;
    private BookDetail detail;
    private final BookRepository bookRepository = new BookRepository();
    private final BookDetailRepository bookDetailRepository = new BookDetailRepository();

    public void initialize() {
        bindView();
    }

    protected void bindView() {
        if (book == null) return;
        detail = bookRepository.getDetail(book.getBookID());
        bookImg.setImage(ImageHelper.load(book.getImage()));
        titleField.setText(book.getTitle());
        descriptionArea.setText(detail.getDescription());
        priceField.setText(book.getPrice().toString());
        quantityField.setText(Integer.toString(book.getQuantity()));
        publisherField.setText(detail.getPublisher());
        publishedField.setValue(detail.getPublishedDate().toLocalDate());

    }

    public void setBook(Book book) {
        this.book = book;
       bindView();
    }

    public void enableEdit(ActionEvent actionEvent) {
        titleField.setEditable(true);
        descriptionArea.setEditable(true);
        priceField.setEditable(true);
        quantityField.setEditable(true);
        publishedField.setEditable(true);
        publisherField.setEditable(true);
        priceField.setEditable(true);
        updateBtn.setManaged(true);
        updateBtn.setVisible(true);
    }

    public void deleteAction(ActionEvent actionEvent) {
        boolean res = showConfirmation("Confirm Action", "Are you sure you want to delete this book?");
        if (!res) return;

        bookRepository.delete(book.getBookID());
        showInformation("Success", "Book Deleted Successfully");
        close(bookImg);
    }

    public void editAction(ActionEvent actionEvent) {
        book.setTitle(titleField.getText());
        book.setImage(path);
        book.setPrice(Float.parseFloat(priceField.getText()));
        book.setQuantity(Integer.parseInt(quantityField.getText()));
        bookRepository.update(book);

        detail.setDescription(descriptionArea.getText());
        detail.setPublisher(publisherField.getText());
        detail.setPublishedDate(Date.valueOf(publishedField.getValue()));
        bookDetailRepository.update(detail);
        try {
            ImageHelper.save(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showInformation("Success", "Book Details updated");
    }

    public void changeImage(MouseEvent mouseEvent) {
        if (!titleField.isEditable()) return;

        String file = fileChooser(titleField);
        if (file == null) return;
        bookImg.setImage(ImageHelper.load(file));
        path = file;
    }
}
