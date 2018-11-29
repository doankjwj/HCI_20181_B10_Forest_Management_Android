package com.example.doannd.hci_2018_forestmanagement;

import android.os.Bundle;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.activities.BaseActivity;

public class DroneDetaillActivity extends BaseActivity {

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_detaill);

        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);

        textView.setText("Mã số: 1");
        textView2.setText("Khu vực bay: Hà Nội");
        textView3.setText("Tốc độ bay: 20km/h");
        textView4.setText("Độ cao tối đa: 200m");
        textView5.setText("Trạng thái: Đang hoạt động");
        textView6.setText("");
    }
}
