package com.whilmarbitoco.inkspace.view.controller.user;

import com.whilmarbitoco.inkspace.repository.OrderRepository;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.user.OrderViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class OrderController extends BaseController {

    public GridPane grid;

    private final OrderViewModel viewModel = new OrderViewModel();

    public void initialize() {
        setViewModel(viewModel);
        bindView();
        initData();
    }

    protected void bindView() {

    }

    public void initData() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        try {
            for (int i = 0; i < viewModel.getOrders().size(); i++) {

                FXMLLoader loader = ViewHandler.getLoader("user/OrderItemView");
                HBox pane = loader.load();
                OrderItemController controller = loader.getController();
                controller.setOrder(viewModel.getOrders().get(i));
                grid.add(pane, 0, i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
