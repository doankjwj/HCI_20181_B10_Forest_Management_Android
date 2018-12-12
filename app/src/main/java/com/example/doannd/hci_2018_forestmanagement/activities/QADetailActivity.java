package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;

public class QADetailActivity extends AppCompatActivity {
    private TextView tvCauHoii,tvTraLoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qadetail);

        Intent data = getIntent();
        tvCauHoii =(TextView) findViewById(R.id.tvCauHoii);
        tvTraLoi =(TextView) findViewById(R.id.tvTraLoi);

        tvCauHoii.setText("Hỏi: "+data.getStringExtra("cauhoi"));
        tvTraLoi.setText("Trả lời: "+ data.getStringExtra("traloi"));
    }
}
