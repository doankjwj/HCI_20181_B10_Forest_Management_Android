package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.Video;

import static java.security.AccessController.getContext;

public class VideoDetail extends AppCompatActivity {

    private TextView tvVideoID,tvDroneID,tvKhuVucID,tvNgay,tvTime,tvHeight,tvNumWarning;
    private Button btnAnalyze;
    private ImageView imgVideo;
    private int isXuLy;
    private Video video;

    ConstraintLayout layoutResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        Intent data = getIntent();

        layoutResult = (ConstraintLayout) findViewById(R.id.layoutResult);
        layoutResult.setVisibility(View.INVISIBLE);
        imgVideo=(ImageView) findViewById(R.id.imgVideo);
        //imgVideo.setImageResource(R.drawable.forest_video);

        tvVideoID=(TextView) findViewById(R.id.tvVideoID);
        tvDroneID=(TextView) findViewById(R.id.tvDroneID);
        tvKhuVucID=(TextView) findViewById(R.id.tvKhuVucID);
        tvNgay=(TextView) findViewById(R.id.tvNgay);
        //tvTime=(TextView) findViewById(R.id.tvTime);
        //tvHeight=(TextView) findViewById(R.id.tvHeight);
        tvNumWarning=(TextView) findViewById(R.id.tvNumWarning);
        String videoId=data.getStringExtra("videoid");
        tvVideoID.setText(data.getStringExtra("videoid"));
        tvDroneID.setText(data.getStringExtra("droneid"));
        tvKhuVucID.setText(data.getStringExtra("khuvucid"));
        tvNgay.setText(data.getStringExtra("date"));
//        tvTime.setText(data.getStringExtra("time"));
//        tvHeight.setText(data.getStringExtra("height"));
//        tvNumWarning.setText(data.getStringExtra("warning"));

        btnAnalyze =(Button)findViewById(R.id.btnAnalyze);

        final MyDatabaseHelper db= MyDatabaseHelper.getInstance(VideoDetail.this);
        video=db.getVideo(videoId);
        isXuLy = video.getIsAnalyze();
        if (video.getIsAnalyze()==1){
            btnAnalyze.setText("Đã Phân Tích");
            layoutResult.setVisibility(View.VISIBLE);
        }
        else{
            btnAnalyze.setText("Phân Tích");
            layoutResult.setVisibility(View.INVISIBLE);
        }

        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isXuLy==0){
                    video.setIsAnalyze(1);
                    db.updateVideo(video);
                    layoutResult.setVisibility(View.VISIBLE);
                    btnAnalyze.setText("Đã Phân Tích");
                    Toast.makeText(VideoDetail.this, "Đã cập nhật dữ liệu lên hệ thống!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
