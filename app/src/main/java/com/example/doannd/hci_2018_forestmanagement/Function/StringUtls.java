package com.example.doannd.hci_2018_forestmanagement.Function;

public class StringUtls {
    public static String strCat(String s, int length)
    {
        length = Math.min(length, s.length());
        return s.substring(0, length-1);
    }
    public static String longString(String s, int length, char deli, boolean toLeft)
    {
        if (s.length() >= length)
            return s;
        String ss = s;
        for (int i=0; i < length - s.length(); i++)
        {
            if (toLeft)
            {
                ss = deli + ss;
            }
            else
            {
                ss = ss + deli;
            }

        };
        return ss;
    }
    public static String longString(int n, int length, char deli, boolean toLeft)
    {
        String s = String.valueOf(n);
        if (s.length() >= length)
            return s;
        String ss = s;
        for (int i=0; i < length - s.length(); i++)
        {
            if (toLeft)
            {
                ss = deli + ss;
            }
            else
            {
                ss = ss + deli;
            }

        };
        return ss;
    }
}
