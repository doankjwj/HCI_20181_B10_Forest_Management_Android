package com.example.doannd.hci_2018_forestmanagement.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.fragments.DroneListFragment;

public class DroneListActivity extends BaseActivity {

    private ActionBar toolbar;

    private DroneListActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_list);

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
                switch (i) {
                    case 0:
                        color = R.color.droneMonitor;
                        break;
                    case 1:
                        color = R.color.droneFree;
                        break;
                    case 2:
                        color = R.color.droneMaintance;
                        break;
                    default:
                        break;
                };
                tabLayout.setBackgroundColor(getColor(color));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setCurrentItem(1, true);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
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