package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.fragments.DroneListFragment;

public class DroneListActivity extends BaseActivity {

    private ActionBar toolbar;

    private DroneListActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    int numDroneMonitor = 0;
    int numDroneFree = 0;
    int numDroneManetence = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_list);

        Intent intent = getIntent();
        intent.putExtra("currentMenuItem","drone");

        getSupportActionBar().setTitle("Danh sách Drone");

        addData();

        settingBottom();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int color = 0;
                int numDrone = 0;
                switch (i) {
                    case 0:
                        color = R.color.droneMonitor;
                        numDrone = numDroneMonitor;
                        break;
                    case 1:
                        color = R.color.droneFree;
                        numDrone = numDroneFree;
                        break;
                    case 2:
                        color = R.color.droneMaintance;
                        numDrone = numDroneManetence;
                        break;
                    default:
                        break;
                };
                tabLayout.setBackgroundColor(getColor(color));
                getSupportActionBar().setTitle("Hiện có: " + numDrone);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setCurrentItem(1, true);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    private void addData() {
        AppDataBase appDataBase = new AppDataBase(getApplicationContext(), getResources().getString(R.string.data_base_name), null, 1);
        String sql = "";
        Cursor cursor;
        int droneStatus = Const.DRONE_STATUS_BUSY;
        switch (droneStatus)
        {
            case Const.DRONE_STATUS_FREE:
            case Const.DRONE_STATUS_MAINTENANCE:
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "'";
                break;
            case Const.DRONE_STATUS_BUSY:
                String userId = getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "none");
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "' AND UserControlling = '" + userId + "'";
                break;
        };
        cursor = appDataBase.getData(sql);
        numDroneMonitor = cursor.getCount();

        droneStatus = Const.DRONE_STATUS_FREE;
        switch (droneStatus)
        {
            case Const.DRONE_STATUS_FREE:
            case Const.DRONE_STATUS_MAINTENANCE:
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "'";
                break;
            case Const.DRONE_STATUS_BUSY:
                String userId = getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "none");
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "' AND UserControlling = '" + userId + "'";
                break;
        };
        cursor = appDataBase.getData(sql);
        numDroneFree = cursor.getCount();

        droneStatus = Const.DRONE_STATUS_MAINTENANCE;
        switch (droneStatus)
        {
            case Const.DRONE_STATUS_FREE:
            case Const.DRONE_STATUS_MAINTENANCE:
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "'";
                break;
            case Const.DRONE_STATUS_BUSY:
                String userId = getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "none");
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "' AND UserControlling = '" + userId + "'";
                break;
        };
        cursor = appDataBase.getData(sql);
        numDroneManetence = cursor.getCount();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            DroneListFragment droneFragment = new DroneListFragment();

            int droneStatus = 0;

            switch (position) {
                case 0:
                    droneStatus = Const.DRONE_STATUS_BUSY;
                    break;
                case 1:
                    droneStatus = Const.DRONE_STATUS_FREE;
                    break;
                case 2:
                    droneStatus = Const.DRONE_STATUS_MAINTENANCE;
                    break;
                default:
                    droneStatus = Const.DRONE_STATUS_FREE;
                    break;
            };
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.bundleDroneStaus), droneStatus);
            droneFragment.setArguments(bundle);

            return droneFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.tab_drone_monitor);
                case 1:
                    return getResources().getString(R.string.tab_drone_free);
                case 2:
                    return getResources().getString(R.string.tab_drone_maintenance);
            }
            return null;
        }
    }
}