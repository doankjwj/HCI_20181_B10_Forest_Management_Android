package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;

public class VideoDetail extends AppCompatActivity {

    private TextView tvVideoID,tvDroneID,tvKhuVucID,tvNgay,tvTime,tvHeight,tvNumWarning;
    private Button btnAnalyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        Intent data = getIntent();

        tvVideoID=(TextView) findViewById(R.id.tvVideoID);
        tvDroneID=(TextView) findViewById(R.id.tvDroneID);
        tvKhuVucID=(TextView) findViewById(R.id.tvKhuVucID);
        tvNgay=(TextView) findViewById(R.id.tvNgay);
        tvTime=(TextView) findViewById(R.id.tvTime);
        tvHeight=(TextView) findViewById(R.id.tvHeight);
        tvNumWarning=(TextView) findViewById(R.id.tvNumWarning);
        tvVideoID.setText(data.getStringExtra("videoid"));
        tvDroneID.setText(data.getStringExtra("droneid"));
        tvKhuVucID.setText(data.getStringExtra("khuvucid"));
        tvNgay.setText(data.getStringExtra("date"));
        tvTime.setText(data.getStringExtra("time"));
        tvHeight.setText(data.getStringExtra("height"));
        tvNumWarning.setText(data.getStringExtra("warning"));

        btnAnalyze=(Button) findViewById(R.id.btnAnalyze);
        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
