package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "CoverType")
public class Cover {

    @Primary
    @Column(name = "CoverTypeID")
    private int coverID;

    @Column(name = "TypeName")
    private String cover;

    public Cover() {}

    public Cover(String cover) {
        this.cover = cover;
    }

    public int getCoverID() {
        return coverID;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return this.cover;
    }
}
