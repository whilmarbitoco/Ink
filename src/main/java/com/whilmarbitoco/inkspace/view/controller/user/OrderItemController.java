package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.model.Order;
import com.whilmarbitoco.inkspace.utils.ImageHelper;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.OrderItemViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderItemController extends BaseController {
    
    public ImageView imgField;
    public Label titleField;
    public Label descField;
    public Label qtyField;
    public Label priceField;
    public VBox rateContainer;

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

        if (viewModel.hasReviewed()) {
            rateContainer.setVisible(false);
            rateContainer.setManaged(false);
        }
    }

    public void setOrder(Order order) {
        viewModel.setOrder(order);
        bindView();
    }

    public void rateAction(ActionEvent actionEvent) {
        try {
            Stage child = ViewHandler.childStage();

            FXMLLoader loader = ViewHandler.getLoader("user/RateView");
            Parent parent = loader.load();
            RateController controller = loader.getController();
            controller.setBook(viewModel.getBook());

            Scene scene = new Scene(parent);
            child.setScene(scene);
            child.setResizable(false);
            child.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
