package com.example.doannd.hci_2018_forestmanagement.Data;

import java.io.Serializable;

public class Area implements Serializable {
    float latitude, longitude, width, height, area;

    public Area()
    {

    };
    public Area(float latitude, float longitude, float width, float height, float area) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.width = width;
        this.height = height;
        this.area = area;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
}
