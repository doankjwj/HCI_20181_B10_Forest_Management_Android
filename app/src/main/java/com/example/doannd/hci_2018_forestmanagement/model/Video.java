package com.example.doannd.hci_2018_forestmanagement.model;

public class Video {

    private String VideoID;
    private String DroneID;
    private String KhuVucID;
    private String Date;

    public Video(String videoID, String droneID, String khuVucID, String date) {

        VideoID = videoID;
        DroneID = droneID;
        KhuVucID = khuVucID;
        Date = date;
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

    public String getKhuVucID() {
        return KhuVucID;
    }

    public void setKhuVucID(String khuVucID) {
        KhuVucID = khuVucID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
