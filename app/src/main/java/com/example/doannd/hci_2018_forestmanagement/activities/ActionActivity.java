package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
//import android.app.Fragment;
//import android.app.FragmentManager;
import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.fragments.ActionDataFragment;
import com.example.doannd.hci_2018_forestmanagement.fragments.ActionReportFragment;
import com.example.doannd.hci_2018_forestmanagement.fragments.ActionVideoFragment;
import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileChangePassword;
import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileHistory;

public class ActionActivity extends BaseActivity {

    private ActionActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Intent intent = getIntent();
        intent.putExtra("currentMenuItem","action");

        settingBottom();

        mSectionsPagerAdapter =new ActionActivity.SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter{
        public SectionsPagerAdapter(FragmentManager fm){super(fm);}

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    ActionVideoFragment tab1=new ActionVideoFragment();
                    return tab1;
                case 1:
                    ActionDataFragment tab2=new ActionDataFragment();
                    return tab2;
                case 2:
                    ActionReportFragment tab3=new ActionReportFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return 3;
        }
    }
}
