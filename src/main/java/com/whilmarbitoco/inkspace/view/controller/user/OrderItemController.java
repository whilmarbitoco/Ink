package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.OrderItemViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class OrderItemController extends BaseController {
    
    public ImageView imgField;
    public Label titleField;
    public Label descField;
    public Label qtyField;
    public Label priceField;

    private final OrderItemViewModel viewModel = new OrderItemViewModel();

    public void initialize() {
        setViewModel(viewModel);
    }

    protected void bindView() {
        imgField.setImage(ImageHelper.load(viewModel.getBook().getImage()));
        titleField.setText(viewModel.getBook().getTitle());
        descField.setText(viewModel.getDetail().getDescription());
        qtyField.setText(Integer.toString(viewModel.getOrder().getQuantity()));
        priceField.setText(Float.toString(viewModel.getBook().getPrice().floatValue() * viewModel.getOrder().getQuantity()));
    }

    public void setOrder(Order order) {
        viewModel.setOrder(order);
        bindView();
    }

    public void rateAction(ActionEvent actionEvent) {
    }
}
