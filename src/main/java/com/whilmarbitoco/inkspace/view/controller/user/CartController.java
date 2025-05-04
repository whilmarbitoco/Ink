package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.store.CartStore;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.CartViewModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class CartController extends BaseController {


    public TextField searchField;
    public Label usernameLabel;
    public GridPane grid;

    private final CartViewModel viewModel = new CartViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        usernameLabel.setText(viewModel.getCurrentUser().getFirstName() + " " + viewModel.getCurrentUser().getLastName());

//        debouncing
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            pause.setOnFinished(event -> {
                System.out.println(newText);
            });
            pause.playFromStart();
        });


        CartStore.cart().clear();
        CartStore.cart().addAll(viewModel.getCartList());
        initData();
    }

    private void initData() {
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        try {
            int row = 0;
            int col = 0;

            for (int i = 0; i < CartStore.cart().size(); i++) {
                if (col == 4) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("user/CartItem");
                VBox pane = loader.load();
                CartItemController controller = loader.getController();
                controller.setCart(CartStore.cart().get(i));
                grid.add(pane, col++, row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void bindView() {

    }

    public void delAction(ActionEvent actionEvent) {
        boolean res = showConfirmation("Confirm", "Are you sure you want to delete all selected books");
        if (!res) return;
        viewModel.delete();
        initData();
    }

    public void orderAction(ActionEvent actionEvent) {
        if (CartStore.selected().isEmpty()) {
            showError("Invalid", "Please select an item to order");
            return;
        }
        boolean res = showConfirmation("Confirm", "Are you sure you want to order all selected books");
        if (!res) return;
        viewModel.order();
        initData();
    }
}
