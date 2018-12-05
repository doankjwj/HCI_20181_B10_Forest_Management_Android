package com.example.doannd.hci_2018_forestmanagement.Function;

import android.content.Context;
import android.os.Vibrator;

public class Ultis {
    public static void onVibrate(Context context)
    {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
}
