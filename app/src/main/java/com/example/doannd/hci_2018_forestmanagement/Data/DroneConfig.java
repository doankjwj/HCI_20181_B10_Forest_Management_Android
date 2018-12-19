package com.example.doannd.hci_2018_forestmanagement.Data;

public class DroneConfig {
    float latitude, longitude, zoom;
    int height;
    int speed;
    int time;

    public DroneConfig(float latitude, float longitude, float zoom, int height, int speed, int time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom;
        this.height = height;
        this.speed = speed;
        this.time = time;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
