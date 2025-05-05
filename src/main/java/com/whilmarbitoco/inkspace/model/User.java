package com.whilmarbitoco.inkspace.model;

import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "User")
public class User {

    @Primary
    @Column(name = "UserID")
    private int userID;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Image")
    private String image;

    @Column(name = "RoleID")
    private int roleID;

    public User() {};

    public User(String firstName, String lastName, String email, String password, String image, int roleID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.image = image;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
