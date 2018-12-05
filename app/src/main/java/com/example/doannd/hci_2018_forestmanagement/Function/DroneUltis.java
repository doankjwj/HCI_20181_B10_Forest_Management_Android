package com.example.doannd.hci_2018_forestmanagement.Function;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Data.DroneConfig;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.activities.MainActivity;

import java.util.Random;

public class DroneUltis {
    public static Drone createDroneByCursor(Cursor cursor)
    {
        int id = cursor.getInt(0);
        String model = cursor.getString(1);
        String mfg = cursor.getString(2);
        int status = cursor.getInt(3);
        int energy = cursor.getInt(4);
        int userControl = cursor.getInt(5);
        float latitude = cursor.getFloat(6);
        float longitude = cursor.getFloat(7);
        int height = cursor.getInt(8);
        int speed = cursor.getInt(9);
        int time = cursor.getInt(10);
        Drone drone = new Drone(id, model, mfg, status, energy, userControl, latitude, longitude, height, speed, time);

        return drone;
    }

    public static boolean registerDrone(Context context, int droneId, DroneConfig droneConfig, String userId)
    {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        try {
            String sql = "UPDATE Drone SET UserControlling = '" + userId + "', Status = '" + Const.DRONE_STATUS_BUSY + "'," +
                    "Latitude = '" + droneConfig.getLatitude() + "', " +
                    "Longitude = '" + droneConfig.getLongitude() + "', " +
                    "Height = '" + droneConfig.getHeight() + "', " +
                    "Speed = '" + droneConfig.getSpeed() + "', " +
                    "Time = '" + droneConfig.getTime() + "' WHERE Id = '" + droneId + "'";
            appDataBase.queryData(sql);
            Log.i("Update Drone: ", sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        };

        return true;
    }
    public static void resetDataBase(Context context) {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Drone(Id INTEGER PRIMARY KEY AUTOINCREMENT, Model STRING, Mfg DATE, Status INTEGER, Energy INTEGER, UserControlling STRING, Latitude FLOAT, Longitude FLOAT, Height INTEGER, Speed INTEGER, Time INTEGER)");
        dropDatabase(context,"Drone");
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Drone(Id INTEGER PRIMARY KEY AUTOINCREMENT, Model STRING, Mfg DATE, Status INTEGER, Energy INTEGER, UserControlling STRING, Latitude FLOAT, Longitude FLOAT, Height INTEGER, Speed INTEGER, Time INTEGER)");
    }
    private static void dropDatabase(Context context, String table)
    {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        appDataBase.queryData("DROP TABLE " + table);
    }
    public static void initDataBase(Context context, int num) {
        AppDataBase mAppDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        resetDataBase(context);
        fillTableDrone(mAppDataBase, num);
    }
    public static void fillTableDrone(AppDataBase appDataBase, int numRecord) {
        Random random = new Random();
        for (int i=0; i<numRecord; i++)
        {
            String model = "DR" + String.valueOf(random.nextInt(2018));
            String year = "2018";
            String month = String.valueOf(random.nextInt(12) + 1);
            String day = String.valueOf(random.nextInt(30) + 1);
            String date = year + "/" + month + "/" + day;
//            int status = random.nextInt(3);
            int status = random.nextInt(2) + 1;
            int energy = random.nextInt(51) + 50;
            int userController = random.nextInt(1000);
            float latitude = random.nextFloat() * 90 * (random.nextFloat() > 0.5 ? 1 : -1);
            float longitude = random.nextFloat() * 180 * (random.nextFloat() > 0.5 ? 1 : -1);
            int height = 0;
            int speed = 0;
            String time = "none";
            String sql = "INSERT INTO Drone VALUES(null, '" + model + "', '" + date + "', '" + status + "', '"
                    + energy + "', '" + userController + "', '" + latitude + "', '" + longitude + "', '" +
                    + height + "', '" + speed + "', '" + time + "')";

            appDataBase.queryData(sql);
        }
    }
}
