package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.Data.Area;
import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Data.DroneConfig;
import com.example.doannd.hci_2018_forestmanagement.Data.Location;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.example.doannd.hci_2018_forestmanagement.Function.StringUtls;

import java.util.ArrayList;

public class ActivityConfigDrone extends AppCompatActivity {

    Area area;
    Drone drone;

    TextView txtStatus, txtEnergy, txtIdDrone, txtHeight, txtTime;
    ImageView imgDrone;
    Button btnOk;
    Spinner spinnerHeight, spinnerTime;
    int currentHeight;
    int currentTime;

    ArrayList<Location> listLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_config_drone);

        area = (Area) getIntent().getSerializableExtra("areaConfig");
        drone = (Drone) getIntent().getSerializableExtra("droneInfo");
        listLocation = (ArrayList<Location>) getIntent().getBundleExtra("route").getSerializable("listLocation");
        Log.e("NUM ROUTE", listLocation.size() + "");
        reference();
        matchData();
    }

    private void matchData() {
        txtStatus.setText("Trạng thái: sẵn sàng");
        txtEnergy.setText("Năng Lượng: " + drone.getEnergy() + "%");
        txtIdDrone.setText("Id: " + StringUtls.longString(String.valueOf(drone.getId()), 5, '0', true) );
        txtHeight.setText("Chọn độ cao bay");
        txtTime.setText("Chọn thời gian bay");
        imgDrone.setImageResource(R.drawable.drone_free);

        btnOk = (Button)findViewById(R.id.btnDrone);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDrone();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Drone newDrone = DroneUltis.createDroneById(getApplicationContext(), drone.getId());
                        Intent intent = new Intent(getApplicationContext(), ActivityDroneDetail.class);
                        intent.putExtra(getApplicationContext().getResources().getString(R.string.bundleDroneInfo), newDrone);
                        intent.putExtra("isFromList", false);
                        startActivity(intent);
                    }
                }, 1000);

            }
        });
    }

    private void reference() {
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        txtEnergy = (TextView)findViewById(R.id.txtEnergy);
        txtIdDrone = (TextView)findViewById(R.id.txtIdDrone);
        txtHeight = (TextView)findViewById(R.id.txtHeight);
        txtTime = (TextView)findViewById(R.id.txtTime);

        spinnerHeight = (Spinner)findViewById(R.id.droneHeightSpinner);
        spinnerTime = (Spinner)findViewById(R.id.droneTimeSpinner);
        imgDrone = (ImageView)findViewById(R.id.imgIdDrone);

        ArrayAdapter<CharSequence> adapterSpinnerHeight = ArrayAdapter.createFromResource(this, R.array.spinnerDroneHeight, android.R.layout.simple_spinner_item);
        adapterSpinnerHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeight.setAdapter(adapterSpinnerHeight);
        spinnerHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                currentHeight = Integer.parseInt(item.substring(0, item.indexOf('m')));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String item = parent.getItemAtPosition(0).toString();
                currentHeight = Integer.parseInt(item.substring(0, item.indexOf('m')));
            }
        });

        ArrayAdapter<CharSequence> adapterSpinnerTimer = ArrayAdapter.createFromResource(this, R.array.spinnerDroneTime, android.R.layout.simple_spinner_item);
        adapterSpinnerTimer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterSpinnerTimer);
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                currentTime = Integer.parseInt(item.substring(0, item.indexOf('p')));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String item = parent.getItemAtPosition(0).toString();
                currentTime = Integer.parseInt(item.substring(0, item.indexOf('p')));
            }
        });
    }

    private void registerDrone()
    {
        int droneId = drone.getId();
        String userId = getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "none");
        Log.e("Drone Register", "" + droneId);
        DroneConfig droneConfig = new DroneConfig(area.getLatitude(), area.getLongitude(), area.getZoom(), currentHeight, 0, currentTime);
        DroneUltis.registerDrone(getApplicationContext(), droneId, droneConfig, userId, listLocation);
    }
}
