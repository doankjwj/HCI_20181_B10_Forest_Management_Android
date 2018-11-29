package com.example.doannd.hci_2018_forestmanagement.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.fragments.DroneFragment;

public class DroneActivity extends BaseActivity {

    private ActionBar toolbar;

    private DroneActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    TextView lblban1;
    TextView lblban2;
    TextView lblban3;
    TextView lblban4;
    TextView lblban5;
    TextView lblban6;
    TextView lblban7;
    TextView lblban8;
    TextView lblban9;
    TextView lblban10;
    TextView lblban11;
    TextView lblban12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone);

        settingBottom();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        lblban1 = (TextView) findViewById(R.id.lblban1);
//        lblban1.setTextColor(Color.GREEN);
        lblban2 = (TextView) findViewById(R.id.lblban2);
//        lblban2.setTextColor(Color.GREEN);
        lblban3 = (TextView) findViewById(R.id.lblban3);
//        lblban3.setTextColor(Color.GREEN);
        lblban4 = (TextView) findViewById(R.id.lblban4);
//        lblban4.setTextColor(Color.GREEN);
        lblban5 = (TextView) findViewById(R.id.lblban5);
//        lblban5.setTextColor(Color.GREEN);
        lblban6 = (TextView) findViewById(R.id.lblban6);
//        lblban6.setTextColor(Color.GREEN);
        lblban7 = (TextView) findViewById(R.id.lblban7);
//        lblban7.setTextColor(Color.GREEN);
        lblban8 = (TextView) findViewById(R.id.lblban8);
//        lblban8.setTextColor(Color.GREEN);
        lblban9 = (TextView) findViewById(R.id.lblban9);
//        lblban9.setTextColor(Color.GREEN);
        lblban10 = (TextView) findViewById(R.id.lblban10);
//        lblban10.setTextColor(Color.GREEN);
        lblban11 = (TextView) findViewById(R.id.lblban11);
//        lblban11.setTextColor(Color.GREEN);
        lblban12 = (TextView) findViewById(R.id.lblban12);
//        lblban12.setTextColor(Color.GREEN);

//        lblban1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//
//        lblban2.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban3.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban4.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban5.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban6.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban7.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban8.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban9.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban10.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban11.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
//        lblban12.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent ban1 = new Intent(DroneActivity.this, DroneDetaillActivity.class);
//                startActivity(ban1);
//            }
//        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    DroneFragment tab1 = new DroneFragment();
                    return tab1;
                case 1:
                    DroneFragment tab2 = new DroneFragment();
                    return tab2;
                case 2:
                    DroneFragment tab3 = new DroneFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INPROCESS";
                case 1:
                    return "WAITING";
                case 2:
                    return "MAINTANCE";
            }
            return null;
        }
    }
}