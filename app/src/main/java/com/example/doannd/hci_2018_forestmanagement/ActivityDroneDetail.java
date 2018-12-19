package com.example.doannd.hci_2018_forestmanagement;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.StringUtls;
import com.example.doannd.hci_2018_forestmanagement.activities.MainActivity;

public class ActivityDroneDetail extends AppCompatActivity {

    TextView txtId, txtModel, txtMfg, txtStatus, txtUserControlling, txtEnergy, txtPosition, txtHeight, txtSpeed;
    Button btnHistory, btnView, btnConfig;
    ImageView imgForest;
    VideoView videoView;

    Drone drone;
    boolean isFromList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_detaill);

        getSupportActionBar().setTitle("Drone");
        Intent intent = getIntent();
        drone = (Drone) intent.getSerializableExtra(getApplicationContext().getResources().getString(R.string.bundleDroneInfo));
        isFromList = intent.getBooleanExtra("isFromList", true);
        reference();
        matchData();
    }
    private void reference() {
        txtId = (TextView)findViewById(R.id.txtId);
        txtModel = (TextView)findViewById(R.id.txtModel);
        txtMfg = (TextView)findViewById(R.id.txtMfg);
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        txtUserControlling = (TextView)findViewById(R.id.txtUserControlling);
        txtEnergy = (TextView)findViewById(R.id.txtEnergy);
        txtPosition = (TextView)findViewById(R.id.txtPosition);
        txtHeight = (TextView)findViewById(R.id.txtTime);
        txtSpeed = (TextView)findViewById(R.id.txtSpeed);

        btnHistory = (Button)findViewById(R.id.btnHistory);
        btnConfig = (Button)findViewById(R.id.btnConfig);
        btnView = (Button)findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityControlDrone.class);
                intent.putExtra(getResources().getString(R.string.bundleDroneInfo), drone);
                startActivity(intent);
            }
        });

        imgForest = (ImageView)findViewById(R.id.imgForest);
        videoView = (VideoView)findViewById(R.id.videoView);
    }
    private void matchData()
    {
        txtId.setText(getResources().getString(R.string.drone_id) + " " + StringUtls.longString(drone.getId(), 5, '0', true));
        txtModel.setText(getResources().getString(R.string.drone_model) + " " + drone.getModel());
        txtMfg.setText(getResources().getString(R.string.drone_mfg) + " " + drone.getMfg());

        String status = "";
        String userControl = "None";
        String position = "(" + drone.getLatitude() + ", " + drone.getLongitude() + ")";
        String height = "None";
        String time = "None";
        int statusColor = 0;
        switch (drone.getStatus())
        {
            case Const.DRONE_STATUS_BUSY:
                status = getResources().getString(R.string.tab_drone_monitor);
                userControl = String.valueOf(drone.getId());
                height = String.valueOf(drone.getHeight());
                time = String.valueOf(drone.getTime());
                statusColor = getResources().getColor(R.color.droneMonitor);
                break;
            case Const.DRONE_STATUS_FREE:
                status = getResources().getString(R.string.tab_drone_free);
                statusColor = getResources().getColor(R.color.droneFree);
                break;
            case Const.DRONE_STATUS_MAINTENANCE:
                status = getResources().getString(R.string.tab_drone_maintenance);
                statusColor = getResources().getColor(R.color.droneMaintance);
                break;
        };
        txtStatus.setText(getResources().getString(R.string.drone_status) + " " + status);
        txtStatus.setTextColor(statusColor);

        txtEnergy.setText(getResources().getString(R.string.drone_energy) + " " + drone.getEnergy() + "%");
        txtUserControlling.setText(getResources().getString(R.string.drone_user) + " [" + userControl + "]");
        txtPosition.setText(getResources().getString(R.string.drone_pos) + position);

        txtHeight.setText(getResources().getString(R.string.drone_height) + " [" + height + "]m");
        txtSpeed.setText(getResources().getString(R.string.drone_time) + " [" + time + "]p");

        if (drone.getStatus() == Const.DRONE_STATUS_BUSY)
        {
            btnView.setVisibility(View.VISIBLE);
            btnConfig.setVisibility(View.INVISIBLE);
            imgForest.setVisibility(View.VISIBLE);
            playVideo();
        }
        if (drone.getStatus() == Const.DRONE_STATUS_FREE)
        {
            btnView.setVisibility(View.INVISIBLE);
            btnConfig.setVisibility(View.VISIBLE);
            imgForest.setVisibility(View.INVISIBLE);
        }
        if (drone.getStatus() == Const.DRONE_STATUS_MAINTENANCE)
        {
            btnView.setVisibility(View.INVISIBLE);
            btnConfig.setVisibility(View.INVISIBLE);
            imgForest.setVisibility(View.INVISIBLE);
        }
    }

    private void playVideo() {
        String path = "android.resource://" + getPackageName() + "/raw/forest_gif";
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoView.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFromList)
        {
            finish();
        }
        else
            startActivity(new Intent(ActivityDroneDetail.this, MainActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        playVideo();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Changes 'back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if (getIntent().getBooleanExtra("isFromControlDrone", false))
                startActivity(new Intent(ActivityDroneDetail.this, MainActivity.class));
            else
                super.onKeyDown(keyCode, event);
        }
        return true;
    }

}
