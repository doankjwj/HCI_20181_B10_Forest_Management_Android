package com.example.doannd.hci_2018_forestmanagement.model;

public class District {
    private String ID;
    private String NhietDo;
    private String DoAm;
    private String DoBaoPhu;
    private String NguyCoChayRung;
    private String KhaNangXamHai;
    private int IsXuLy;

    public District(String id,String nhietDo, String doAm, String doBaoPhu, String nguyCoChayRung, String khaNangXamHai) {
        ID = id;
        NhietDo = nhietDo;
        DoAm = doAm;
        DoBaoPhu = doBaoPhu;
        NguyCoChayRung = nguyCoChayRung;
        KhaNangXamHai = khaNangXamHai;
        IsXuLy = 0;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        ID = id;
    }

    public String getNhietDo() {
        return NhietDo;
    }

    public void setNhietDo(String nhietDo) {
        NhietDo = nhietDo;
    }

    public String getDoAm() {
        return DoAm;
    }

    public void setDoAm(String doAm) {
        DoAm = doAm;
    }

    public String getDoBaoPhu() {
        return DoBaoPhu;
    }

    public void setDoBaoPhu(String doBaoPhu) {
        DoBaoPhu = doBaoPhu;
    }

    public String getNguyCoChayRung() {
        return NguyCoChayRung;
    }

    public void setNguyCoChayRung(String nguyCoChayRung) {
        NguyCoChayRung = nguyCoChayRung;
    }

    public String getKhaNangXamHai() {
        return KhaNangXamHai;
    }

    public void setKhaNangXamHai(String khaNangXamHai) {
        KhaNangXamHai = khaNangXamHai;
    }

    public int getXuLy() {
        return IsXuLy;
    }

    public void setXuLy(int xuLy) {
        IsXuLy = xuLy;
    }
}
