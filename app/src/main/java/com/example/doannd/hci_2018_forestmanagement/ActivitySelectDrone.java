package com.example.doannd.hci_2018_forestmanagement;

import android.content.Intent;
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
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.Distance;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;

public class ActivitySelectDrone extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Area area;
    Button btnOk;

    TextView txtLatitude, txtLongitude, txtWidth, txtHeight, txtSize;
    Spinner spinnerSelectDrone;
    int currentSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_drone);

        area = (Area) getIntent().getSerializableExtra(getResources().getString(R.string.intentKey));
        reference();
        matchData();
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
        txtHeight = (TextView)findViewById(R.id.txtHeight);
        txtSize = (TextView)findViewById(R.id.txtArea);
        spinnerSelectDrone = (Spinner)findViewById(R.id.spinnerSelectDrone);

        btnOk = (Button)findViewById(R.id.btnDrone);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSpinner == 0)
                {
                    Intent intent = new Intent(ActivitySelectDrone.this, ActivityConfigDrone.class);
                    intent.putExtra("areaConfig", area);
                    intent.putExtra("droneInfo", getDroneNearest(area));
                    startActivity(intent);
                }
                else
                    Toast.makeText(ActivitySelectDrone.this,"Coming Soon..", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerDroneSelector, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectDrone.setAdapter(adapterSpinner);
        spinnerSelectDrone.setOnItemSelectedListener(this);
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

        AppDataBase mAppDataBase = new AppDataBase(ActivitySelectDrone.this, getResources().getString(R.string.data_base_name), null, 1);

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
}
