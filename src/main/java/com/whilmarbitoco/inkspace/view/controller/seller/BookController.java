package com.whilmarbitoco.inkspace.view.controller.seller;

import com.whilmarbitoco.inkspace.model.Book;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.BookViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class BookController extends BaseController {


    private final BookViewModel viewModel = new BookViewModel();

    public TableView<Book> tableView;
    public TableColumn<Book, Integer> tableID;
    public TableColumn<Book, String> tableTitle;
    public TableColumn<Book, BigDecimal> tablePrice;
    public TableColumn<Book, Integer> tableQty;


    public void initialize() {
        tableID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bindView();
        setViewModel(viewModel);
    }

    protected void bindView() {
        tableView.setItems(viewModel.getBooks());
    }

    public void addBookAction(ActionEvent actionEvent) {
        viewModel.switchTo("seller/create/BookInfoView");
    }

    public void tableMouseAction(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.getClickCount() == 2) {
                Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                if (selectedBook == null) return;

                Stage child = new Stage();

                FXMLLoader loader = ViewHandler.getLoader("seller/BookInformationView");
                Parent parent = loader.load();

                BookInformationController controller = loader.getController();
                controller.setBook(selectedBook);

                Scene scene = new Scene(parent);
                child.setScene(scene);
                child.setResizable(false);
                child.setOnCloseRequest(ee -> viewModel.fetch());
                child.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exitStore(ActionEvent actionEvent) {
        viewModel.switchTo("user/BookView");
    }

    public void gotoSeller(ActionEvent actionEvent) {
        viewModel.switchTo("seller/SellerView");
    }

    public void gotoOrder(MouseEvent mouseEvent) {
        viewModel.switchTo("seller/OrderView");
    }
}
