package com.whilmarbitoco.inkspace.view.controller.seller;

import com.whilmarbitoco.inkspace.model.Address;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.OrderDetailViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class OrderDetailController extends BaseController {

    public ImageView userImg;
    public Label username;
    public Label addressLabelOne;
    public Label addressOne;
    public Label addressLabelTwo;
    public Label addressTwo;
    public Label emailField;
    public ImageView bookImg;
    public Label titleField;
    public Label editionField;
    public Label priceField;
    public Label qtyField;
    public Label coverField;
    public Label desField;

    private final OrderDetailViewModel viewModel = new OrderDetailViewModel();

    public void initialize() {
        setViewModel(viewModel);
    }

    protected void bindView() {
        userImg.setImage(ImageHelper.load(viewModel.getUser().getImage()));
        username.setText(viewModel.getUser().getFirstName() + " " + viewModel.getUser().getLastName());

        List<Address> addresses = viewModel.getAddresses();
        if (!addresses.isEmpty()) {
            Address a = addresses.getFirst();
            addressLabelOne.setText(a.getLabel());
            addressOne.setText(a.formattedAddress());
        }

        if (addresses.size() > 1) {
            Address a = addresses.getLast();
            addressLabelTwo.setText(a.getLabel());
            addressTwo.setText(a.formattedAddress());
        }

        emailField.setText(viewModel.getUser().getEmail());

        bookImg.setImage(ImageHelper.load(viewModel.getBook().getImage()));
        titleField.setText(viewModel.getBook().getTitle());
        priceField.setText(Float.toString(viewModel.getBook().getPrice().floatValue() * viewModel.getOrder().getQuantity()));
        qtyField.setText(Integer.toString(viewModel.getOrder().getQuantity()));
        editionField.setText(viewModel.getEdition().getEdition());
        coverField.setText(viewModel.getCover().getCover());
        desField.setText(viewModel.getDetail().getDescription());

    }

    public void setOrderID(int orderID) {
        viewModel.orderID(orderID);
        bindView();
    }

    public void sellAction(ActionEvent actionEvent) {
        boolean res = showConfirmation("Confirm", "Do you really want to sell this book?");
        if (!res) return;;
        viewModel.sell();
        close(userImg);
    }
}
