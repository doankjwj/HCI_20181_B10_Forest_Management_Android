package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;

public class VideoDetail extends AppCompatActivity {

    private TextView tvVideoID,tvDroneID,tvKhuVucID,tvNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        Intent data = getIntent();

        tvVideoID=(TextView) findViewById(R.id.tvVideoID);
        tvDroneID=(TextView) findViewById(R.id.tvDroneID);
        tvKhuVucID=(TextView) findViewById(R.id.tvKhuVucID);
        tvNgay=(TextView) findViewById(R.id.tvNgay);
        tvVideoID.setText(data.getStringExtra("videoid"));
        tvDroneID.setText(data.getStringExtra("droneid"));
        tvKhuVucID.setText(data.getStringExtra("khuvucid"));
        tvNgay.setText(data.getStringExtra("date"));
    }
}
