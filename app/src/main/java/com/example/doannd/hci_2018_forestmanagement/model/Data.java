package com.example.doannd.hci_2018_forestmanagement.model;

import java.util.List;

public class Data {
    private int DataID;
    private String KhuVucID;
    private String VideoID;
    private String DroneID;
    private String Date;
    private List<Warning> ListWarning;

    public Data(int dataID, String khuVucID, String videoID, String droneID, String date, List<Warning> listWarning) {
        DataID = dataID;
        KhuVucID = khuVucID;
        VideoID = videoID;
        DroneID = droneID;
        Date = date;
        ListWarning = listWarning;
    }

    public int getDataID() {
        return DataID;
    }

    public void setDataID(int dataID) {
        DataID = dataID;
    }

    public String getKhuVucID() {
        return KhuVucID;
    }

    public void setKhuVucID(String khuVucID) {
        KhuVucID = khuVucID;
    }

    public String getVideoID() {
        return VideoID;
    }

    public void setVideoID(String videoID) {
        VideoID = videoID;
    }

    public String getDroneID() {
        return DroneID;
    }

    public void setDroneID(String droneID) {
        DroneID = droneID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<Warning> getListWarning() {
        return ListWarning;
    }

    public void setListWarning(List<Warning> listWarning) {
        ListWarning = listWarning;
    }
}
