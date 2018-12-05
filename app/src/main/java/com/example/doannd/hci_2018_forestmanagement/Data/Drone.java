package com.example.doannd.hci_2018_forestmanagement.Data;

import java.io.Serializable;
import java.util.Date;

public class Drone implements Serializable {
    int id;
    String model;
    String mfg;
    int status;
    int energy;
    int UserControlling;
    float latitude;
    float longitude;
    int height;
    int speed;
    int time; // minutes

    public Drone(int id, String model, String mfg, int status, int energy, int userControlling, float latitude, float longitude, int height, int speed, int time) {
        this.id = id;
        this.model = model;
        this.mfg = mfg;
        this.status = status;
        this.energy = energy;
        UserControlling = userControlling;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.speed = speed;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getUserControlling() {
        return UserControlling;
    }

    public void setUserControlling(int userControlling) {
        UserControlling = userControlling;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return 15;
//        return speed;
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
