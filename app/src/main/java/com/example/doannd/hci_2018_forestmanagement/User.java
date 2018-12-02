package com.example.doannd.hci_2018_forestmanagement;

import java.io.Serializable;

public class User implements Serializable {

    String userName;
    String passWord;
    String userType;
    String hoTen;
    String birthDay;
    String flagName;
    boolean active;

    public User(String userName, String passWord, String userType, String hoTen, String birthDay) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
        this.hoTen = hoTen;
        this.birthDay = birthDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return this.userName + " (" + this.userType + ")";
    }
}
