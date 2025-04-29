package com.whilmarbitoco.inkspace.model;


import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "Address")
public class Address {

    @Primary
    @Column(name = "AddressID")
    private int addressID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "Label")
    private String label;

    @Column(name = "Street")
    private String street;

    @Column(name = "Barangay")
    private String barangay;

    @Column(name = "City")
    private String city;

    @Column(name = "Region")
    private String region;

    @Column(name = "PostalCode")
    private String postal;


    public Address() {}

    public Address(int userID, String label, String street, String barangay, String city, String region, String postal) {
        this.userID = userID;
        this.label = label;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.region = region;
        this.postal = postal;
    }

    public int getAddressID() {
        return addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}
