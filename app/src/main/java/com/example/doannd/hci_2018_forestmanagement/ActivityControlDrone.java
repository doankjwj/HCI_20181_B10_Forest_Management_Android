package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.hardware.Camera;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.Data.Location;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.example.doannd.hci_2018_forestmanagement.Function.Ultis;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityControlDrone extends AppCompatActivity implements OnMapReadyCallback {

    FrameLayout frameLayout;
    Camera camera;
    ShowCamera showCamera;

    ImageButton btnAuto, btnHand, btnLeft, btnRight, btnFront, btnBack, btnUp, btnDown;
    TextView txtStatus, txtLatitude, txtLongitude, txtForward, txtHeight, txtSpeed, txtLimited;
    ConstraintLayout layoutControl, layoutLimited;

    Drone drone;
    boolean isHanding = false;
    int droneForward = 0;
    float latitude, longitude;
    int height, speed;

    ArrayList<Location> listLocation = new ArrayList<>();
    Location droneLocation;
    Location droneHandLocation;
    MarkerOptions markerDrone;

    Forward forward;
    GoogleMap mMap;
    Menu mMenu;

    int currentLocationIndex = 0;
    int currentLocationTime = 0;
    float currentLocationDis = 0;
    float disPerTime;

    Marker marker;
    public static float dis2Point = (float) 0.001068115234375;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_drone);
        drone = (Drone) getIntent().getSerializableExtra(getResources().getString(R.string.bundleDroneInfo));
        reference();
        addMap();
        latitude = drone.getLatitude();
        longitude = drone.getLongitude();
        height = drone.getHeight();
        speed = drone.getSpeed();

        droneLocation = new Location(0, 0, 0);
        matchData();

        getSupportActionBar().setTitle("Giám sát Drone");

        camera = Camera.open();
        showCamera = new ShowCamera(getApplicationContext(), camera);
        frameLayout.addView(showCamera);
    }

    private void addMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        supportMapFragment.getMapAsync(this);
    }

    private void reference() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        layoutLimited = (ConstraintLayout) findViewById(R.id.layoutLimitted);
        layoutLimited.setVisibility(View.INVISIBLE);
        layoutControl = (ConstraintLayout) findViewById(R.id.layOutControl);
        btnAuto = (ImageButton) findViewById(R.id.btnAuto);
        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("AUTO", "");
                if (isHanding)
                    pushLatLocation();
                isHanding = !isHanding;
//                if (isHanding)
//                    saveLastDroneHandLocation();
                matchData();
            }
        });
        btnHand = (ImageButton) findViewById(R.id.btnHand);
        btnHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("AUTO", "222");
                if (isHanding)
                    pushLatLocation();
                isHanding = !isHanding;
//                if (isHanding)
//                    saveLastDroneHandLocation();
                matchData();
            }
        });

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtForward = (TextView) findViewById(R.id.txtForward);
        txtHeight = (TextView) findViewById(R.id.txtTime);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        txtLimited = (TextView) findViewById(R.id.txtLimitted);

        final float latitudeA = dis2Point/10;
        final float longitudeA = dis2Point/10;
        btnFront = (ImageButton) findViewById(R.id.btnFront);
        btnFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward = getForward( droneForward);
                Log.e("FORWOARD", forward.getLatitude() + " : " + forward.getLongitude());
                moveDrone(forward.getLatitude() * latitudeA, forward.getLongitude() * longitudeA, droneLocation);
                matchData();
            }
        });
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward = getForward( droneForward);
                Log.e("FORWOARD", forward.getLatitude() + " : " + forward.getLongitude());
                moveDrone(-forward.getLatitude() * latitudeA, -forward.getLongitude() * longitudeA, droneLocation);
                matchData();
            }
        });
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droneForward += 3;
                droneForward = droneForward%4;
                getForward(droneForward);
                matchData();
            }
        });
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droneForward += 1;
                droneForward = droneForward%4;
                getForward(droneForward);
                matchData();
            }
        });
        btnUp = (ImageButton) findViewById(R.id.btnUp);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (height+1 >= 26)
                {
                    Ultis.onVibrate(getApplicationContext());
                    layoutLimited.setVisibility(View.VISIBLE);
                    txtLimited.setText("Độ cao cho phép Max: 25m" );
                    return;
                }
                layoutLimited.setVisibility(View.INVISIBLE);
                height ++;
                matchData();
            }
        });
        btnDown = (ImageButton) findViewById(R.id.btnDown);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (height-1 <= listLocation.size()-1)
                {
                    Ultis.onVibrate(getApplicationContext());
                    layoutLimited.setVisibility(View.VISIBLE);
                    txtLimited.setText("Độ cao cho phép Min: 10m" );
                    return;
                }
                layoutLimited.setVisibility(View.INVISIBLE);
                height --;
                matchData();
            }
        });
    }

    private void pushLatLocation() {
        listLocation.set(currentLocationIndex, new Location(droneLocation.getLatitude(), droneLocation.getLongitude(), 0));
        resetLikeNewLocation();
    }

    private void resetLikeNewLocation() {
        currentLocationTime = 0;
    }

    public Forward getForward(int orientation)
    {
        Log.e("ORIENTATION", " " + orientation);
        switch (orientation)
        {
            case 0:
                return new Forward(1, 0);
            case 1:
                return new Forward(0, 1);
            case 2:
                return new Forward(-1, 0);
            case 3:
                return new Forward(0, -1);

        };
        return new Forward(0, 0);
    };
    public class Forward {
        float latitude, longitude, orientation;

        public Forward(float latitude, float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        };

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        public float getOrientation() {
            return orientation;
        }

        public void setOrientation(float orientation) {
            this.orientation = orientation;
        }
    }

    public void saveLastDroneHandLocation()
    {
        droneHandLocation = new Location(droneLocation.getLatitude(), droneLocation.getLongitude(), 0);
    };
    private void matchData()
    {
        onVisibleHand();

        txtLatitude.setText("Vĩ độ: " + droneLocation.getLatitude());
        txtLongitude.setText("Kinh độ: " + droneLocation.getLongitude());
        if (currentLocationIndex != listLocation.size() - 1)
        {
            txtForward.setText("Di chuyển từ: " + (currentLocationIndex+1) + " sang " + (currentLocationIndex+2) );
            txtSpeed.setText("Hướng: " + getOrientation());
        }
        else
        {
            txtForward.setText("Hoàn tất:");
            txtSpeed.setText("Tốc độ: " + 0 + "m/s");
        }
        txtHeight.setText("Độ cao: " + height + "m");
        if (mMenu != null)
            mMenu.findItem(R.id.btn_report).setVisible(isHanding);
    }

    private String getOrientation() {
        if (!isHanding)
            return "Tự động";
        switch (droneForward)
        {
            case 0: return "Bắc";
            case 1: return "Đông";
            case 2: return "Nam";
            default: return "Tây";
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap = googleMap;
        float latitude = drone.getLatitude();
        float longitude = drone.getLongitude();
        float zoom = drone.getZoom();

        if (zoom != 0)
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
        };
        
        initLocationRoute();
        addRouteMarker();
        handlerDroneFly();
    }
    private void handlerDroneFly() {
        droneLocation = new Location(drone.getLatitude(), drone.getLongitude(), 0);
        final Handler handler = new Handler();
        disPerTime = dis2Point/60;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentLocationIndex == listLocation.size() - 1)
                {
                    onCompleteRoute();
                    return;
                }
                executeLocation();
                handler.postDelayed(this, 50);
            }
        }, 50);

        Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                matchData();
                handler.postDelayed(this, 100);
            }
        }, 100);
    }

    private void onCompleteRoute() {
        Log.e("TAG", "COMPLETE DRONE");
        Toast.makeText(getApplicationContext(), "Hoàn tất giám sát, dữ liệu được lưu lại", Toast.LENGTH_SHORT).show();
        DroneUltis.freeDone(getApplicationContext(), drone.getId());
        Intent intent = new Intent(ActivityControlDrone.this, ActivityDroneDetail.class);
        Drone drone2 = DroneUltis.createDroneById(getApplicationContext(), drone.getId());
        intent.putExtra("droneInfo", drone2);
        intent.putExtra("isFromControlDrone", true);
        startActivity(intent);
    }

    public void executeLocation()
    {
        if (isHanding)
            return;
        currentLocationDis = currentLocationTime * disPerTime;
        float dis2Lol = dis2Location(currentLocationIndex, currentLocationIndex+1);
        if (currentLocationDis >= dis2Lol)
        {
            currentLocationIndex++;
            currentLocationTime = 0;
            return;
        }
        float latitudeAdd = Math.abs(listLocation.get(currentLocationIndex+1).getLatitude() - listLocation.get(currentLocationIndex).getLatitude());
        float longitudeAdd = Math.abs(listLocation.get(currentLocationIndex+1).getLongitude() - listLocation.get(currentLocationIndex).getLongitude());
        latitudeAdd = latitudeAdd * currentLocationDis/dis2Lol;
        longitudeAdd = longitudeAdd * currentLocationDis/dis2Lol;

        if (listLocation.get(currentLocationIndex+1).getLatitude() < listLocation.get(currentLocationIndex).getLatitude())
            latitudeAdd = -latitudeAdd;
        if (listLocation.get(currentLocationIndex+1).getLongitude() < listLocation.get(currentLocationIndex).getLongitude())
            longitudeAdd = -longitudeAdd;
        moveDrone(latitudeAdd, longitudeAdd, listLocation.get(currentLocationIndex));
        currentLocationTime++;
    };
    public void moveDrone(float latitude, float longitude, Location location)
    {
        Log.e("MOVE", latitude + " : " + longitude);
        droneLocation.setLatitude(location.getLatitude() + latitude);
        droneLocation.setLongitude(location.getLongitude() + longitude);
        updatePosDrone();
    };
    public void updatePosDrone()
    {
        marker.setPosition(new LatLng(droneLocation.getLatitude(), droneLocation.getLongitude()));
    };
    public float dis2Location(int index1, int index2)
    {
        return (float) Math.sqrt(Math.pow((listLocation.get(index1).getLatitude() - listLocation.get(index2).getLatitude()), 2) + Math.pow((listLocation.get(index1).getLongitude() - listLocation.get(index2).getLongitude()), 2));
    }

    private void initLocationRoute() {
        listLocation = new ArrayList<>();
        AppDataBase appDataBase = new AppDataBase(getApplicationContext(), getResources().getString(R.string.data_base_name), null, 1);
        String sql = "SELECT * FROM Route WHERE DroneId = '" + drone.getId() + "'";
        Cursor cursor = appDataBase.getData(sql);
        while (cursor.moveToNext())
        {
            Location location = new Location(cursor.getFloat(2), cursor.getFloat(3), cursor.getInt(4));
            listLocation.add(location);
        }
    }

    private void addRouteMarker() {
        for (int i=0; i<listLocation.size(); i++)
        {
            Location location = listLocation.get(i);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOption = new MarkerOptions().position(latLng).title(location.getLatitude() + ":" + location.getLongitude()).icon(BitmapDescriptorFactory.fromResource(ActivityAreaInfo.getBitmapRes(i+1)));
            mMap.addMarker(markerOption);
        };
        markerDrone = new MarkerOptions().position(new LatLng(listLocation.get(0).getLatitude(), listLocation.get(0).getLongitude())).title("Vị trí hiện tại").icon(BitmapDescriptorFactory.fromResource(R.drawable.drone_red_32));
        marker = mMap.addMarker(markerDrone);
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
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_control_drone, menu);
        mMenu = menu;
        mMenu.findItem(R.id.btn_report).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.btn_report:
                onReport();
                break;
        }
        return true;
    }

    private void onReport() {
        String toast = "Ghi nhận chặt phá kèm hình ảnh tại (" + droneLocation.getLatitude() + "," + droneLocation.getLongitude() + ")";
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

}
