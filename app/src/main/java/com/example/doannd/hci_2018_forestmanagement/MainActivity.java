package com.example.doannd.hci_2018_forestmanagement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Forest");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bnb_forest:
                    toolbar.setTitle("Forest");
                    return true;
                case R.id.bnb_drone:
                    toolbar.setTitle("Drone");
                    return true;
                case R.id.bnb_action:
                    toolbar.setTitle("Action");
                    return true;
                case R.id.bnb_profile:
                    toolbar.setTitle("Profile");
                    return true;
                case R.id.bnb_admin_tool:
                    toolbar.setTitle("Admin Tool");
                    return true;
            }
            return false;
        }
    };
}
