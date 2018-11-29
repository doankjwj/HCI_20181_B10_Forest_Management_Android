package com.example.doannd.hci_2018_forestmanagement.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.ActivitySelectDrone;
import com.example.doannd.hci_2018_forestmanagement.Data.Area;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends BaseActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
//        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private ActionBar toolbar;

    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap mMap;
    LatLng latLng;
    boolean alerted = false;
    boolean isCropping = false;

    ImageButton btnCrop, btnCancel, btnOk, btnConstraint, btnZoomIn, btnZoomOut;
    ImageView imgFrame, imgFrameCenter;
    ConstraintLayout layoutLimited;

    private final int MAP_ZOOM_MAX = 19;
    private final int MAP_ZOOM_MIN = 13;
    private final int MAP_DEFAULT_ZOOM = 15;
    private final float MAP_SCALE = (float) 1042.43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.toolBarMap_0));
        reference();
        settingBottom();
        initMap();
    }

    private void reference() {
        btnCrop = (ImageButton) findViewById(R.id.btnCrop);
        btnCrop.setVisibility(View.VISIBLE);
        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCrop();
            }
        });
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.INVISIBLE);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUnCrop();
            }
        });
        btnOk = (ImageButton) findViewById(R.id.btnOk);
        btnOk.setVisibility(View.INVISIBLE);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUnCrop();
                onOk();
            }
        });
        btnConstraint = (ImageButton) findViewById(R.id.btnConstraint);
        btnConstraint.setVisibility(View.INVISIBLE);
        btnConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng latLng = new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_ZOOM_MIN));
            }
        });
        imgFrame = (ImageView) findViewById(R.id.imageFrame);
        imgFrame.setVisibility(View.INVISIBLE);
        imgFrameCenter = (ImageView) findViewById(R.id.imageFrameCenter);
        imgFrameCenter.setVisibility(View.INVISIBLE);

        btnZoomIn = (ImageButton) findViewById(R.id.btnZoomIn);
        btnZoomIn.setVisibility(View.INVISIBLE);
        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mapZoom = (int) mMap.getCameraPosition().zoom;
                mapZoom += 0.5;
                if (mapZoom >= MAP_ZOOM_MAX)
                {
                    mapZoom = MAP_ZOOM_MAX;
                    if (imgFrame.getVisibility() == View.VISIBLE)
                        Toast.makeText(MainActivity.this, "Diện tích khu vực chưa đủ rộng", Toast.LENGTH_SHORT).show();
                };
                Toast.makeText(MainActivity.this, mapZoom + "", Toast.LENGTH_SHORT).show();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMap.getCameraPosition().target, mapZoom));
            }
        });

        btnZoomOut = (ImageButton) findViewById(R.id.btnZoomOut);
        btnZoomOut.setVisibility(View.INVISIBLE);
        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mapZoom = (int) mMap.getCameraPosition().zoom;
                mapZoom -= 0.5;
                if (mapZoom <= MAP_ZOOM_MIN)
                {
                    mapZoom = MAP_ZOOM_MIN;
                    if (imgFrame.getVisibility() == View.VISIBLE)
                        Toast.makeText(MainActivity.this, "Đã đạt diện tích lớn nhất vùng bay của Drone", Toast.LENGTH_SHORT).show();
                };
                Toast.makeText(MainActivity.this, mapZoom + "", Toast.LENGTH_SHORT).show();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMap.getCameraPosition().target, mapZoom));
            }
        });

        layoutLimited = (ConstraintLayout) findViewById(R.id.layoutLimitted);
        layoutLimited.setVisibility(View.INVISIBLE);
    }

    private void onOk() {
        Intent intent = new Intent(MainActivity.this, ActivitySelectDrone.class);
        Area area = getAreaInfo();
        intent.putExtra(String.valueOf(getResources().getString(R.string.intentKey)), area);
        startActivity(intent);
    }

    private Area getAreaInfo()
    {
        LatLng latLng = mMap.getCameraPosition().target;
        float latitude = (float) latLng.latitude;
        float longitude = (float) latLng.longitude;

        float zoom = mMap.getCameraPosition().zoom;
        float width = MAP_DEFAULT_ZOOM/zoom * MAP_SCALE;
        float height = MAP_DEFAULT_ZOOM/zoom * MAP_SCALE;
        float size = width * height;
        Area area = new Area(latitude, longitude, width, height, size);
        return area;
    };

    private void onCrop()
    {
        btnCrop.setVisibility(View.INVISIBLE);
        btnOk.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        imgFrame.setVisibility(View.VISIBLE);
        imgFrameCenter.setVisibility(View.VISIBLE);
        if (!alerted)
            Toast.makeText(MainActivity.this, "Khoanh vùng thả Drone vào khung xanh", Toast.LENGTH_SHORT).show();
        alerted = true;
        isCropping = true;
        toolbar.setTitle(getResources().getString(R.string.toolBarMap_1));
    };
    private void onUnCrop()
    {
        btnCrop.setVisibility(View.VISIBLE);
        btnOk.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
        imgFrame.setVisibility(View.INVISIBLE);
        imgFrameCenter.setVisibility(View.INVISIBLE);
        isCropping = false;
        toolbar.setTitle(getResources().getString(R.string.toolBarMap_0));
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }

    private void moveCamera(LatLng latLng, float zoom)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        latLng = new LatLng(21.003052, 105.846670);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this,"Not Permision", Toast.LENGTH_SHORT).show();
            return;
        };

        MarkerOptions markerOptions = new MarkerOptions()
                .title(String.valueOf("Vị trí hiện tại"))
                .snippet(String.valueOf("21.003052 : 105.846670"))
                .position(latLng);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_DEFAULT_ZOOM));

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        
        initThreadControlZoom();
    }

    private void initThreadControlZoom() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int zoom = (int) mMap.getCameraPosition().zoom;
                if (zoom < MAP_ZOOM_MIN)
                {
                    if (isCropping)
                    {
                        layoutLimited.setVisibility(View.VISIBLE);
                        btnConstraint.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        layoutLimited.setVisibility(View.INVISIBLE);
                        btnConstraint.setVisibility(View.INVISIBLE);
                    }
                    btnOk.setVisibility(View.INVISIBLE);
                }
                else
                {
                    layoutLimited.setVisibility(View.INVISIBLE);
                    btnConstraint.setVisibility(View.INVISIBLE);
                    if (isCropping)
                    {
                        btnOk.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        btnOk.setVisibility(View.INVISIBLE);
                    }
                }
                handler.postDelayed(this, 250);
            }
        }, 250);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, mMap.getCameraPosition().zoom));
        return true;
    }
}
