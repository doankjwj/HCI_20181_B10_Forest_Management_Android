package com.example.doannd.hci_2018_forestmanagement;

import java.util.Date;

public class History {

    private String stt;
    private String action;
    private String date;

    public History(String stt, String action,String date) {
        this.stt = stt;
        this.action = action;
        this.date = date;
    }

    public String getStt() {
        return stt;
    }

    public String getAction() {
        return action;
    }

    public void setStt(String stt) {
        this.stt = stt;
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
}
