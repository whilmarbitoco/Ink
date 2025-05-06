package com.whilmarbitoco.inkspace.viewmodel.admin;

import com.whilmarbitoco.inkspace.model.Cover;
import com.whilmarbitoco.inkspace.repository.CoverRepository;
import com.whilmarbitoco.inkspace.viewmodel.BaseViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CoverViewModel extends BaseViewModel {

    private final ObservableList<Cover> covers = FXCollections.observableArrayList();

    private final CoverRepository coverRepository = new CoverRepository();

    public CoverViewModel() {
        fetch();
    }

    public void fetch() {
        covers.clear();
        covers.addAll(coverRepository.findAll());
    }

    public void create(String str) {
        if(!coverRepository.findWhere("TypeName", "=", str).isEmpty()) {
            error.setValue(str + " already exist.");
            return;
        }
        coverRepository.create(new Cover(str));
        message.setValue("Cover Created");
    }

    public ObservableList<Cover> getCovers() {
        return covers;
    }
}
