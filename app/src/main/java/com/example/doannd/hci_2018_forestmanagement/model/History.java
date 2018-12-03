package com.example.doannd.hci_2018_forestmanagement.model;

import java.util.Date;

public class History {

    private int id;
    private String action;
    private String date;
    private String username;

    public History(String action, String date, String username) {
        //this.id = id;
        this.action = action;
        this.date = date;
        this.username=username;

    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
