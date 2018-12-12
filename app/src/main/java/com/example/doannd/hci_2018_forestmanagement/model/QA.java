package com.example.doannd.hci_2018_forestmanagement.model;

public class QA {
    private int ID;
    private String CauHoi;
    private String TraLoi;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getTraLoi() {
        return TraLoi;
    }

    public void setTraLoi(String traLoi) {
        TraLoi = traLoi;
    }

    public QA(int ID, String cauHoi, String traLoi) {

        this.ID = ID;
        CauHoi = cauHoi;
        TraLoi = traLoi;
    }
}
