package com.example.doannd.hci_2018_forestmanagement.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.example.doannd.hci_2018_forestmanagement.R;

public class MainActivity extends BaseActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingBottom();

    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment fragment;
//            switch (item.getItemId()) {
//                case R.id.bnb_forest:
////                    toolbar.setTitle("Forest");
//                    return true;
//                case R.id.bnb_drone:
////                    toolbar.setTitle("Drone");
//                    return true;
//                case R.id.bnb_action:
////                    toolbar.setTitle("Action");
//                    return true;
//                case R.id.bnb_profile:
//                    Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivity(intent);
////                    toolbar.setTitle("Profile");
//                    return true;
//                case R.id.bnb_admin_tool:
////                    toolbar.setTitle("Admin Tool");
//                    return true;
//            }
//            return false;
//        }
//    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
