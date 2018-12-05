package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;

import java.io.IOException;

public class ActivityControlDrone extends AppCompatActivity {

    FrameLayout frameLayout;
    Camera camera;
    ShowCamera showCamera;

    ImageButton btnAuto, btnHand, btnLeft, btnRight, btnFront, btnBack, btnUp, btnDown;
    TextView txtStatus, txtLatitude, txtLongitude, txtForward, txtHeight, txtSpeed;
    ConstraintLayout layoutControl;

    Drone drone;
    boolean isHanding = false;
    float droneForward = 0;
    float latitude, longitude;
    int height, speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_drone);
        drone = (Drone) getIntent().getSerializableExtra(getResources().getString(R.string.bundleDroneInfo));
        reference();

        latitude = drone.getLatitude();
        longitude = drone.getLongitude();
        height = drone.getHeight();
        speed = drone.getSpeed();

        matchData();

        camera = Camera.open();
        showCamera = new ShowCamera(getApplicationContext(), camera);
        frameLayout.addView(showCamera);
    }

    private void reference() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        layoutControl = (ConstraintLayout) findViewById(R.id.layOutControl);
        btnAuto = (ImageButton) findViewById(R.id.btnAuto);
        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHanding = !isHanding;
                matchData();
            }
        });
        btnHand = (ImageButton) findViewById(R.id.btnHand);
        btnHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHanding = !isHanding;
                matchData();
            }
        });

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtForward = (TextView) findViewById(R.id.txtForward);
        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);

        btnFront = (ImageButton) findViewById(R.id.btnFront);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droneForward -= 0.5;
                matchData();
            }
        });
        btnLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("====", ".....");
                return false;
            }
        });
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droneForward += 0.5;
                matchData();
            }
        });
        btnRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("====", ".....");
                return false;
            }
        });
        btnUp = (ImageButton) findViewById(R.id.btnUp);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (height+1 >= 26)
                {
                    Toast.makeText(getApplicationContext(), "Đạt độ cao tối đa: 40m", Toast.LENGTH_SHORT ).show();
                    return;
                }
                height ++;
                matchData();
            }
        });
        btnDown = (ImageButton) findViewById(R.id.btnDown);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (height-1 <= 9)
                {
                    Toast.makeText(getApplicationContext(), "Đạt độ cao tối thiểu: 10m", Toast.LENGTH_SHORT ).show();
                    return;
                }
                height --;
                matchData();
            }
        });
    }

    private void matchData()
    {
        onVisibleHand();

        txtLatitude.setText("Vĩ độ: " + latitude);
        txtLongitude.setText("Kinh độ: " + longitude);
        txtForward.setText("Hướng: Chính Nam " + ((droneForward >= 0) ?  ("+" + droneForward) : (droneForward)));
        txtHeight.setText("Độ cao: " + height + "m");
        txtSpeed.setText("Tốc độ: " + speed + "m/s");
    }
    private void onVisibleHand()
    {
        if (isHanding)
        {
            layoutControl.setVisibility(View.VISIBLE);
            txtStatus.setText("Thủ Công");
            btnAuto.setVisibility(View.INVISIBLE);
            btnHand.setVisibility(View.VISIBLE);
        }
        else
        {
            layoutControl.setVisibility(View.INVISIBLE);
            txtStatus.setText("Tự động");
            btnAuto.setVisibility(View.VISIBLE);
            btnHand.setVisibility(View.INVISIBLE);
        };
    }

    public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback
    {
        Camera camera;
        SurfaceHolder holder;

        public ShowCamera(Context context, Camera camera) {
            super(context);
            this.camera = camera;
            holder = getHolder();
            holder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Camera.Parameters parameters = camera.getParameters();

            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
            {
                parameters.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
                parameters.setRotation(90);
            }
            else
            {
                parameters.set("orientation", "landscape");
                camera.setDisplayOrientation(0);
                parameters.setRotation(0);
            };

            camera.setParameters(parameters);
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
        }
    }
}
