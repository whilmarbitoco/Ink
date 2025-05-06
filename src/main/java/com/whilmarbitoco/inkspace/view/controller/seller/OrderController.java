package com.whilmarbitoco.inkspace.view.controller.seller;

import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.OrderViewModel;
import com.whilmarbitoco.inkspace.utils.Tmp;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrderController extends BaseController {


    private final OrderViewModel viewModel = new OrderViewModel();

    public TableView<Tmp> tableView;
    public TableColumn<Tmp, String> idCol;
    public TableColumn<Tmp, String> titleCol;
    public TableColumn<Tmp, Integer> priceCol;
    public TableColumn<Tmp, Integer> qtyCol;
    public TableColumn<Tmp, String> statusCol;


    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("tmpID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        bindView();
        setViewModel(viewModel);
    }

    protected void bindView() {
        tableView.setItems(viewModel.getOrders());
    }

    public void tableMouseAction(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.getClickCount() == 2) {
                Tmp selected = tableView.getSelectionModel().getSelectedItem();
                if (selected == null) return;
                System.out.println(selected.price);

                Stage child = ViewHandler.childStage();

                FXMLLoader loader = ViewHandler.getLoader("seller/OrderDetailView");
                Parent parent = loader.load();
                OrderDetailController controller = loader.getController();
                controller.setOrderID(selected.tmpID);
                Scene scene = new Scene(parent);
                child.setScene(scene);
                child.setResizable(false);
                child.setOnHidden(ee -> {
                    viewModel.fetch();
                    tableView.getItems().clear();
                    tableView.setItems(viewModel.getOrders());
                });
                child.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exitStore(ActionEvent actionEvent) {
        viewModel.switchTo("user/BookView");
    }

    public void gotoSeller(ActionEvent actionEvent) {
        viewModel.switchTo("seller/SellerView");
    }

    public void gotoBooks(ActionEvent mouseEvent) {
        viewModel.switchTo("seller/BookView");
    }

    public void gotoSold(ActionEvent mouseEvent) {
        viewModel.switchTo("seller/SoldView");
    }
}
