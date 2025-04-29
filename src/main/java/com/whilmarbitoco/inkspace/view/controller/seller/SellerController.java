package com.whilmarbitoco.inkspace.view.controller.seller;

import com.whilmarbitoco.inkspace.view.controller.BaseController;
import com.whilmarbitoco.inkspace.viewmodel.seller.SellerViewModel;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SellerController extends BaseController {

    public PieChart pie2Chart;
    public TextField storeName;
    public Button editBtn;
    public BarChart<String, Number> barChart;


    private final SellerViewModel viewModel = new SellerViewModel();

    public void initialize() {
        bindView();
        setViewModel(viewModel);

        viewModel.messageProperty().addListener((o, p, n) -> {
            storeName.setEditable(false);
            storeName.setDisable(true);
            editBtn.setVisible(false);
            editBtn.setManaged(false);
        });
    }

    protected void bindView() {
        storeName.textProperty().bindBidirectional(viewModel.storeNameProperty());
        barChart.getData().add(viewModel.getSeries());
    }

    public void editAction(ActionEvent actionEvent) {
        storeName.setEditable(true);
        storeName.setDisable(false);
        editBtn.setVisible(true);
        editBtn.setManaged(true);
    }

    public void editStoreAction(ActionEvent actionEvent) {
        viewModel.update();
    }
}
