package com.example.doannd.hci_2018_forestmanagement.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileChangePassword;
import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileHistory;
import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileUpdate;

public class ProfileActivity extends BaseActivity {

    private ProfileActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        settingBottom();

        mSectionsPagerAdapter =new SectionsPagerAdapter(getSupportFragmentManager());

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
                    ProfileUpdate tab1=new ProfileUpdate();
                    return tab1;
                case 1:
                    ProfileHistory tab3 = new ProfileHistory();
                    return tab3;
                case 2:
                    ProfileChangePassword tab2 = new ProfileChangePassword();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return 3;
        }

//        @Override
//        public CharSequence getPageTitle(int position){
//            switch (position){
//                case 0:
//                    return "ACCOUNT";
//                case 1:
//                    return "CHANGE PASSWORD";
//            }
//            return null;
//        }
    }
}


