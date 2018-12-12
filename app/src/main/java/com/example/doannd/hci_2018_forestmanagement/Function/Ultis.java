package com.example.doannd.hci_2018_forestmanagement.Function;

import android.content.Context;
import android.os.Vibrator;

public class Ultis {
    public static void onVibrate(Context context)
    {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    public static String ChucVu(String userType){
        if (userType.equals("kiemlam")){
            return "Kiểm lâm";
        }
        else if (userType.equals("quantrivien")){
            return "Quản trị viên";
        }
        else if (userType.equals("phantichvien")){
            return "Phân tích viên";
        }
        return "Kiểm lâm";
    }
}
