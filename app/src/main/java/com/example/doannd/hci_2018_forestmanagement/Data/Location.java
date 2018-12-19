package com.example.doannd.hci_2018_forestmanagement.Data;

import java.io.Serializable;

public class Location implements Serializable {
    float latitude, longitude;
    int index;

    public Location(float latitude, float longitude, int index) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addLatitude(float lat)
    {
        setLatitude(getLatitude() + lat);
    }

    public void addLongitude(float lon)
    {
        setLongitude(getLongitude() + lon);
    }
}
