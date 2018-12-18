package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.Data.Area;
import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Data.Location;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.Distance;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ActivityAreaInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback {

    Area area;
    Button btnOk;

    TextView txtLatitude, txtLongitude, txtWidth, txtHeight, txtSize;
    Spinner spinnerSelectDrone, spinnerSelectRoute;
    int currentSpinner;
    ArrayList<Location> listLocation;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_area_info);

        getSupportActionBar().setTitle("Thiết lập lộ trình bay cho Drone");
        area = (Area) getIntent().getSerializableExtra(getResources().getString(R.string.intentKey));
        reference();
        matchData();
        initMap();
    };

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }

    private void matchData() {
        txtLatitude.setText(getResources().getString(R.string.area_latidude) + " " + area.getLatitude());
        txtLongitude.setText(getResources().getString(R.string.area_longitude) + " " + area.getLongitude());
        txtWidth.setText(getResources().getString(R.string.area_width) + " " + area.getWidth() + " (m)");
        txtHeight.setText(getResources().getString(R.string.area_height) + " " + area.getHeight() + " (m)");
        txtSize.setText(getResources().getString(R.string.area_size) + " " + area.getArea() + " (m2)");
    }

    private void reference() {
        txtLatitude = (TextView)findViewById(R.id.txtLatitude);
        txtLongitude = (TextView)findViewById(R.id.txtLongitude);
        txtWidth = (TextView)findViewById(R.id.txtWidth);
        txtHeight = (TextView)findViewById(R.id.txtTime);
        txtSize = (TextView)findViewById(R.id.txtArea);
        spinnerSelectDrone = (Spinner)findViewById(R.id.spinnerSelectDrone);
        spinnerSelectRoute = (Spinner)findViewById(R.id.spinnerRoute);

        btnOk = (Button)findViewById(R.id.btnDrone);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Bundle bundle = new Bundle();
                switch (currentSpinner)
                {
                    case 0:
                        intent = new Intent(ActivityAreaInfo.this, ActivityConfigDrone.class);
                        intent.putExtra("areaConfig", area);
                        intent.putExtra("droneInfo", getDroneNearest(area));
                        bundle = new Bundle();
                        bundle.putSerializable("listLocation", listLocation);
                        intent.putExtra("route", bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ActivityAreaInfo.this, ActivitySelectDrone.class);
                        intent.putExtra("areaConfig", area);
                        bundle = new Bundle();
                        bundle.putSerializable("listLocation", listLocation);
                        intent.putExtra("route", bundle);
                        startActivity(intent);
                        break;
                }
            }
        });

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerDroneSelector, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectDrone.setAdapter(adapterSpinner);
        spinnerSelectDrone.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterSpinnerRoute = ArrayAdapter.createFromResource(this, R.array.spinnerRoute, android.R.layout.simple_spinner_item);
        adapterSpinnerRoute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectRoute.setAdapter(adapterSpinnerRoute);
        spinnerSelectRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addRoute(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spinnerSelectRoute.setBackgroundColor(R.color.color_grey_light);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = parent.getItemAtPosition(position).toString();
        currentSpinner = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private Drone getDroneNearest(Area area)
    {
        Drone drone;
        int droneId = 0;
        float areaLatitude = area.getLatitude();
        float areaLongitude = area.getLongitude();
        float disMin = Float.MAX_VALUE;

        AppDataBase mAppDataBase = new AppDataBase(ActivityAreaInfo.this, getResources().getString(R.string.data_base_name), null, 1);

        String sql = "SELECT * FROM Drone WHERE Status == '" + Const.DRONE_STATUS_FREE + "'";
        Cursor cursor = mAppDataBase.getData(sql);
        while (cursor.moveToNext())
        {
            float droneLatitude = cursor.getFloat(6);
            float droneLongitude = cursor.getFloat(7);
            float dis = Distance.distance(areaLatitude, areaLongitude, droneLatitude, droneLongitude);
            if (dis < disMin)
            {
                disMin = dis;
                droneId = cursor.getInt(0);
            };
        };

        sql = "SELECT * FROM Drone WHERE id == '" + droneId + "'";
        cursor = mAppDataBase.getData(sql);
        cursor.moveToNext();
        drone = DroneUltis.createDroneByCursor(cursor);
        return drone;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float latitude = area.getLatitude();
        float longitude = area.getLongitude();
        float zoom = area.getZoom();

        if (zoom != 0)
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
        };
    }

    private void addRoute(int index) {
        mMap.clear();
        if (index == 0) {
            listLocation = getListLocationByArea(area);
        }
        else {
            listLocation = getListLocationByAreaLinely(area);
        }
        for (int i=0; i<listLocation.size(); i++)
        {
            Location location = listLocation.get(i);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("" + i).icon(BitmapDescriptorFactory.fromResource(getBitmapRes(i+1)));
            mMap.addMarker(markerOptions);
        };
    }

    private ArrayList<Location> getListLocationByArea(Area area) {
        float latitude = area.getLatitude();
        float longitude = area.getLongitude();

        float dis = (float) 0.0032;
        int numRoute = 10;
        ArrayList<Location> list = new ArrayList<>();
        for (int i=0; i<numRoute; i++)
        {
            Location location = new Location((float)(latitude - dis/2 + Math.random() * dis), (float)(longitude -dis/2 + Math.random() * dis));
            list.add(location);
        }
        return list;
    };

    private ArrayList<Location> getListLocationByAreaLinely(Area area) {
        float latitude = area.getLatitude();
        float longitude = area.getLongitude();

        float dis = (float) 0.0032;
        int numRoute = 10;
        ArrayList<Location> list = new ArrayList<>();
        for (int i=0; i<numRoute; i++)
        {
            float x = (i+1)%4;
            float y = (i+1)/4;
            Location location = new Location((float)(latitude - dis/2 + y * dis/3), (float)(longitude -dis/2 + x * dis/3));
            list.add(location);
        }
        return list;
    };

    private int getBitmapRes(int i)
    {
        int res = 0;
        switch (i)
        {
            case 1: res = R.drawable.l_1;
                break;
            case 2: res = R.drawable.l_2;
                break;
            case 3: res = R.drawable.l_3;
                break;
            case 4: res = R.drawable.l_4;
                break;
            case 5: res = R.drawable.l_5;
                break;
            case 6: res = R.drawable.l_6;
                break;
            case 7: res = R.drawable.l_7;
                break;
            case 8: res = R.drawable.l_8;
                break;
            case 9: res = R.drawable.l_9;
                break;
            case 10: res = R.drawable.l_10;
                break;
        }
        return res;
    }
}
