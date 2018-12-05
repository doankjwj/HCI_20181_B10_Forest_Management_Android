package com.example.doannd.hci_2018_forestmanagement.Function;

public class Distance {
    public static float distance(float p0x, float p0y, float p1x, float p1y)
    {
        return (float) Math.sqrt(Math.pow(p0x - p1x, 2) + Math.pow(p0y - p1y, 2));
    }
}
