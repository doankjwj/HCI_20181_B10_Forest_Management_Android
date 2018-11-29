package com.example.doannd.hci_2018_forestmanagement.Function;

public class StringUtls {
    public static String strCat(String s, int length)
    {
        length = Math.min(length, s.length());
        return s.substring(0, length-1);
    }
}
