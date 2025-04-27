package com.whilmarbitoco.inkspace.model;

import com.whilmarbitoco.inkspace.database.anotation.Column;
import com.whilmarbitoco.inkspace.database.anotation.Primary;
import com.whilmarbitoco.inkspace.database.anotation.Table;

@Table(name = "session")
public class Session {

    @Primary
    @Column(name = "SessionID")
    private int sessionID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "Token")
    private String token;

    public Session() {}

    public Session(int userID, String token) {
        this.userID = userID;
        this.token = token;
    }

    public int getSessionID() {
        return sessionID;
    }

    public int getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
