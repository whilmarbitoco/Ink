package com.whilmarbitoco.inkspace.store;

import com.whilmarbitoco.inkspace.model.Store;

public class StoreStore {

    private static StoreStore state;
    private Store store;

    public static StoreStore getInstance() {
        if (state == null) {
            state = new StoreStore();
        }

        return state;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
