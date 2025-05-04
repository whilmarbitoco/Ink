package com.whilmarbitoco.inkspace.viewmodel.seller;

import com.whilmarbitoco.inkspace.model.Store;
import com.whilmarbitoco.inkspace.model.StoreAnalytics;
import com.whilmarbitoco.inkspace.repository.StoreAnalyticsRepository;
import com.whilmarbitoco.inkspace.repository.StoreRepository;
import com.whilmarbitoco.inkspace.store.StoreStore;
import com.whilmarbitoco.inkspace.store.UserStore;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.XYChart;

import java.util.List;

public class SellerViewModel extends BaseViewModel {


    private final StringProperty storeName = new SimpleStringProperty("");
    private final XYChart.Series<String, Number> series = new XYChart.Series<>();

    private Store userStore;
    private final StoreAnalyticsRepository storeAnalyticsRepository = new StoreAnalyticsRepository();
    private final StoreRepository storeRepository = new StoreRepository();

    public SellerViewModel() {
        fetch();
        Store current = storeRepository.getByUser(UserStore.getInstance().getUser().getUserID());
        StoreStore.getInstance().setStore(current);
    }

    private void fetch() {
        List<StoreAnalytics> analytics = storeAnalyticsRepository.summary();
        if (analytics != null) {
            series.setName("Monthly Sales");
            for (StoreAnalytics data : analytics) {
                series.getData().add(new XYChart.Data<>(data.getMonth(), data.getSales().intValue()));
            }
        }

        Store store = storeRepository.getByUser(currentUser.getUserID());
        if (store != null) {
            userStore = store;
            storeName.setValue(store.getStoreName());
        }
    }

    public void update() {
        if (storeName.get().isEmpty()) {
            error.setValue("Store name cannot be empty");
            return;
        }
        userStore.setStoreName(storeName.get());
        storeRepository.update(userStore);
        message.setValue("Store name updated.");
    }

    public XYChart.Series<String, Number> getSeries() {
        return series;
    }

    public StringProperty storeNameProperty() {
        return storeName;
    }
}
