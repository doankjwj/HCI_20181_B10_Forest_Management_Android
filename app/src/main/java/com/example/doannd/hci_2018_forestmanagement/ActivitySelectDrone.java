package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.Data.Area;
import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.example.doannd.hci_2018_forestmanagement.adapters.DroneSelectAdapter;
import com.example.doannd.hci_2018_forestmanagement.fragments.MyItemRecyclerViewAdapter;

import java.util.ArrayList;

public class ActivitySelectDrone extends AppCompatActivity {

    ListView listViewDrone;
    ArrayList<Drone> listDrone;
    DroneSelectAdapter droneSelectAdapter;
    Button btnOk;

    SharedPreferences sharedPreferences;

    Drone drone;
    Area area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_drone);

        area = (Area) getIntent().getSerializableExtra("areaConfig");
        resetDroneInfo();
        reference();
        addDrone();
    }

    private void resetDroneInfo() {
        sharedPreferences = getSharedPreferences("droneSelect", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
//        editor.putInt("droneSelected",droneId);
        editor.apply();
    }

    private void reference() {
        listViewDrone = (ListView)findViewById(R.id.listViewDrone);
        btnOk = (Button)findViewById(R.id.btnDrone);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int droneId = sharedPreferences.getInt("droneSelected", -1);
                if (droneId == -1)
                    Toast.makeText(getApplicationContext(), "Chưa chọn Drone", Toast.LENGTH_SHORT).show();
                else
                {
                    drone = DroneUltis.createDroneById(getApplicationContext(), droneId);
                    Intent intent = new Intent(ActivitySelectDrone.this, ActivityConfigDrone.class);
                    intent.putExtra("areaConfig", area);
                    intent.putExtra("droneInfo", drone);
                    startActivity(intent);
                }
            }
        });
    }

    private void addDrone()
    {
        AppDataBase appDataBase = new AppDataBase(getApplicationContext(), getResources().getString(R.string.data_base_name), null, 1);

        listDrone = new ArrayList<>();
        droneSelectAdapter = new DroneSelectAdapter(getApplicationContext(), R.layout.layout_drone_item_select, listDrone);

        listViewDrone.setAdapter(droneSelectAdapter);
        String sql = "SELECT * FROM Drone WHERE Status == '" + Const.DRONE_STATUS_FREE + "'";
        Cursor cursor = appDataBase.getData(sql);
        while (cursor.moveToNext())
        {
            Drone drone = DroneUltis.createDroneByCursor(cursor);
            listDrone.add(drone);
        };
        droneSelectAdapter.notifyDataSetChanged();
    }
}
