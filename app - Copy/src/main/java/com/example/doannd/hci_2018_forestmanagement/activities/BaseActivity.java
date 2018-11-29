package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.example.doannd.hci_2018_forestmanagement.LoginActivity;
import com.example.doannd.hci_2018_forestmanagement.R;

public class BaseActivity extends AppCompatActivity{

//    private ActionBar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bnb_setting:
                return true;
            case R.id.bnb_logout:
                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                sharedPref.edit().clear().commit();
                Intent intent=new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void settingBottom(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username=sharedPref.getString("username","");
        if (username=="kiemlam"){
            navigation.inflateMenu(R.menu.bottom_navigation_bar);
        }
        else if(username=="quantrivien"){
            navigation.inflateMenu(R.menu.qtv_bottom_navigation_bar);
        }
//        else if (username=="phantichvien"){
//            navigation.inflateMenu(R.menu.ptv_bottom_navigation_bar);
//        }
        else{
            navigation.inflateMenu(R.menu.bottom_navigation_bar);
        }

        String abc=sharedPref.getString("abc","");
        switch(abc){
//            case "forest":
//                navigation.setSelectedItemId(R.id.bnb_forest);
//                break;
//            case "drone":
//                navigation.setSelectedItemId(R.id.bnb_drone);
//                break;
//            case "action":
//                navigation.setSelectedItemId(R.id.bnb_action);
//                break;
//            case "profile":
//                navigation.setSelectedItemId(R.id.bnb_profile);
//                break;
//            case "users":
//                navigation.setSelectedItemId(R.id.bnb_admin_tool);
//                break;
//            case "analyze":
//                navigation.setSelectedItemId(R.id.bnb_analyze);
//                break;
            default:
                navigation.setSelectedItemId(R.id.bnb_forest);
                break;
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ActionBar toolBar = getSupportActionBar();
            Intent intent;
            switch (item.getItemId()) {
                case R.id.bnb_forest:
                    saveabc("forest");
                    toolBar.setTitle(R.string.toolBarMap_0);
                    intent=new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.bnb_drone:
                    saveabc("drone");
                    intent=new Intent(BaseActivity.this, DroneActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.bnb_action:
                    saveabc("action");
                    intent=new Intent(BaseActivity.this, ActionActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.bnb_profile:
                    saveabc("profile");
                    intent=new Intent(BaseActivity.this, ProfileActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                    finish();
                    startActivity(intent);
                    return true;
                case R.id.bnb_admin_tool:
                    saveabc("users");
                    intent=new Intent(BaseActivity.this, UsersActivity.class);
                    startActivity(intent);
                    return true;
//                case R.id.bnb_analyze:
//                    saveabc("analyze");
//                    intent=new Intent(BaseActivity.this, AnalyzeActivity.class);
//                    startActivity(intent);
//                    return true;
            }
            return false;
        }
    };

    private void saveabc(String abc){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        if (sharedPref.getString("abc","")!=null){
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("abc", abc);
//            editor.commit();
//        }
//        else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("abc",abc);
            editor.apply();
//        }
    }
}
