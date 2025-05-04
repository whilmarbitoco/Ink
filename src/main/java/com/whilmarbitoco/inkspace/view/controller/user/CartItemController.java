package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Cart;
import com.whilmarbitoco.inkspace.store.CartStore;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.CartItemViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CartItemController extends BaseController {

    public ImageView image;
    public Label titleField;
    public Label priceField;
    public Label qtyField;
    public VBox cartContainer;

    private final CartItemViewModel viewModel = new CartItemViewModel();

    public void initialize() {
        setViewModel(viewModel);

        viewModel.quantityProperty().addListener((o, p, n) -> {
            qtyField.setText(Integer.toString(n.intValue()));
            viewModel.getCart().setQuantity(n.intValue());
            viewModel.update();
        });

        if (CartStore.selected().contains(viewModel.getCart())) {
            cartContainer.getStyleClass().add("card__container__active");
        }
    }

    protected void bindView() {
        image.setImage(ImageHelper.load(viewModel.getBook().getImage()));
        titleField.setText(viewModel.getBook().getTitle());
        priceField.setText(viewModel.getBook().getPrice().toString());
    }

    public void setCart(Cart cart) {
        viewModel.setCart(cart);
        bindView();
    }

    public void minusAction(ActionEvent actionEvent) {
        int val = viewModel.quantityProperty().get() - 1;
        if (val < 1) return;
        viewModel.quantityProperty().setValue(val);
    }

    public void addAction(ActionEvent actionEvent) {
        viewModel.quantityProperty().setValue(viewModel.quantityProperty().get() + 1);
    }

    public void clickedAction() {
        if (CartStore.selected().contains(viewModel.getCart())) {
            CartStore.selected().remove(viewModel.getCart());
            cartContainer.getStyleClass().removeLast();
            cartContainer.getStyleClass().add("card__container");
            return;
        }
        CartStore.selected().add(viewModel.getCart());
        cartContainer.getStyleClass().add("card__container__active");

    }
}
