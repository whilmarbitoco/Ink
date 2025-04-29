package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

import java.math.BigDecimal;

@Table(name = "storeanalytic")
public class StoreAnalytics {

    @Primary
    @Column(name = "AnalyticsID")
    private int analyticID;

    @Column(name = "Month")
    private String month;

    @Column(name = "Sales")
    private BigDecimal sales;

    @Column(name = "StoreID")
    private int storeID;


    public StoreAnalytics() {}

    public StoreAnalytics(String month, BigDecimal sales, int storeID) {
        this.month = month;
        this.sales = sales;
        this.storeID = storeID;
    }

    public int getAnalyticID() {
        return analyticID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
