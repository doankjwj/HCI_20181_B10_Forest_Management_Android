package com.example.doannd.hci_2018_forestmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.Data.Area;

import java.util.Random;

public class ActivityConfigDrone extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Area area;

    TextView txtStatus, txtEnergy, txtIdDrone, txtHeight;
    Button btnOk;
    Spinner spinner;
    int currentHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_config_drone);

        area = (Area) getIntent().getSerializableExtra("areaConfig");
        reference();
        matchData();
    }

    private void matchData() {
        txtStatus.setText("Trạng thái: sẵn sàng");
        txtEnergy.setText("Năng Lượng: 85%");
        txtIdDrone.setText("Id: 100" + new Random().nextInt(10) );
        txtHeight.setText("Chọn độ cao bay");


        btnOk = (Button)findViewById(R.id.btnDrone);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (currentHeight == 0)
//                {
//                    Intent intent = new Intent(ActivityConfigDrone.this, ActivityConfigDrone.class);
//                    startActivity(intent);
//                }
//                else
//                    Toast.makeText(ActivityConfigDrone.this,"Coming Soon..", Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivityConfigDrone.this, currentHeight + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reference() {
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        txtEnergy = (TextView)findViewById(R.id.txtEnergy);
        txtIdDrone = (TextView)findViewById(R.id.txtIdDrone);
        txtHeight = (TextView)findViewById(R.id.txtHeight);
        spinner = (Spinner)findViewById(R.id.droneHeightSpinner);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.spinnerDroneHeight, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        currentHeight = Integer.parseInt(item.substring(0, item.length() - 2));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
