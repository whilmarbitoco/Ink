package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.Store;

import java.util.List;

public class StoreRepository extends BaseRepository<Store> {
    public StoreRepository() {
        super(Store.class);
    }

    public Store getByUser(int user) {
        List<Store> stores = findWhere("UserID", "=", user);
        if (stores.isEmpty()) return null;

        return stores.getFirst();
    }
}
