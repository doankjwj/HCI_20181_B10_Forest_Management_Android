package com.example.doannd.hci_2018_forestmanagement.Function;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Data.DroneConfig;
import com.example.doannd.hci_2018_forestmanagement.Data.Location;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.activities.MainActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class DroneUltis {
    public static Drone createDroneById(Context context, int droneId)
    {
        AppDataBase appDataBase = new AppDataBase(context,context.getResources().getString(R.string.data_base_name), null, 1);
        String sql = "SELECT * FROM Drone WHERE Id = '" + droneId + "'";
        Cursor cursor = appDataBase.getData(sql);
        cursor.moveToNext();
        return createDroneByCursor(cursor);
    };
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
        float zoom = cursor.getFloat(8);
        int height = cursor.getInt(9);
        int speed = cursor.getInt(10);
        int time = cursor.getInt(11);
        Drone drone = new Drone(id, model, mfg, status, energy, userControl, latitude, longitude, zoom, height, speed, time);

        return drone;
    }

    public static boolean registerDrone(Context context, int droneId, DroneConfig droneConfig, String userId)
    {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        try {
            String sql = "UPDATE Drone SET UserControlling = '" + userId + "', Status = '" + Const.DRONE_STATUS_BUSY + "'," +
                    "Latitude = '" + droneConfig.getLatitude() + "', " +
                    "Longitude = '" + droneConfig.getLongitude() + "', " +
                    "Zoom = '" + droneConfig.getZoom() + "', " +
                    "Height = '" + droneConfig.getHeight() + "', " +
                    "Speed = '" + droneConfig.getSpeed() + "', " +
                    "Time = '" + droneConfig.getTime() + "' WHERE Id = '" + droneId + "'";
            appDataBase.queryData(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        };

        return true;
    };

    public static boolean registerDrone(Context context, int droneId, DroneConfig droneConfig, String userId, ArrayList<Location> listLocation)
    {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        if (appDataBase.getData("SELECT * FROM Route WHERE DroneId ='" + droneId +"'").getCount() > 0)
        {
            for (int i=0; i<listLocation.size(); i++)
            {
                Location location = listLocation.get(i);
                String sql = "UPDATE ROUTE SET Latitude = '" + location.getLatitude() + "', Longitude = '" + location.getLongitude() + "'" +
                        " WHERE DroneId = '" + droneId + "' AND locationIndex = '" + i +"'";
                appDataBase.queryData(sql);
            }
        }
        else
        {
            for (int i=0; i<listLocation.size(); i++)
            {
                Location location = listLocation.get(i);
                String sql = "INSERT INTO Route Values(null, '" + droneId + "', '" + location.getLatitude() + "', '" + location.getLongitude() + "', '" + i +"')";
                appDataBase.queryData(sql);
            }
        }
        try {
            String sql = "UPDATE Drone SET UserControlling = '" + userId + "', Status = '" + Const.DRONE_STATUS_BUSY + "'," +
                    "Latitude = '" + droneConfig.getLatitude() + "', " +
                    "Longitude = '" + droneConfig.getLongitude() + "', " +
                    "Zoom = '" + droneConfig.getZoom() + "', " +
                    "Height = '" + droneConfig.getHeight() + "', " +
                    "Speed = '" + droneConfig.getSpeed() + "', " +
                    "Time = '" + droneConfig.getTime() + "' WHERE Id = '" + droneId + "'";
            appDataBase.queryData(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        };

        return true;
    }
    public static boolean freeDone(Context context, int droneId)
    {
        AppDataBase appDataBase = new AppDataBase(context, context.getResources().getString(R.string.data_base_name), null, 1);
        try {
            String sql = "UPDATE Drone SET  Status = '" + Const.DRONE_STATUS_FREE + "' WHERE Id = '" + droneId + "'";
            appDataBase.queryData(sql);
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
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Drone(Id INTEGER PRIMARY KEY AUTOINCREMENT, Model STRING, Mfg DATE, Status INTEGER, Energy INTEGER, UserControlling STRING, Latitude FLOAT, Longitude FLOAT, Zoom FLOAT, Height INTEGER, Speed INTEGER, Time INTEGER)");
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Route(Id INTEGER PRIMARY KEY AUTOINCREMENT, DroneId INTEGER, Latitude FLOAT, Longitude FLOAT, location INTEGER)");
        dropDatabase(context,"Drone");
        dropDatabase(context, "Route");
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Drone(Id INTEGER PRIMARY KEY AUTOINCREMENT, Model STRING, Mfg DATE, Status INTEGER, Energy INTEGER, UserControlling STRING, Latitude FLOAT, Longitude FLOAT, Zoom FLOAT, Height INTEGER, Speed INTEGER, Time INTEGER)");
        appDataBase.queryData("CREATE TABLE IF NOT EXISTS Route(Id INTEGER PRIMARY KEY AUTOINCREMENT, DroneId INTEGER, Latitude FLOAT, Longitude FLOAT, locationIndex INTEGER)");
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
            float zoom = 0;
            int height = 0;
            int speed = 0;
            String time = "none";
            String sql = "INSERT INTO Drone VALUES(null, '" + model + "', '" + date + "', '" + status + "', '"
                    + energy + "', '" + userController + "', '" + latitude + "', '" + longitude + "', '" +  zoom + "', '" +
                    + height + "', '" + speed + "', '" + time + "')";

            appDataBase.queryData(sql);
        }
    }
}
