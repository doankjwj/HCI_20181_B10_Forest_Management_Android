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

import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
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
            case R.id.bnb_qa:
                Intent intent1=new Intent(BaseActivity.this, QAActivity.class);
                startActivity(intent1);
                return true;
            case R.id.bnb_logout:
                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                sharedPref.edit().clear().commit();
                Intent intent=new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.bnb_resetDataBase:
                DroneUltis.initDataBase(getApplicationContext(), 30);
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
        String usertype=sharedPref.getString("usertype","");
        if (usertype.equals("kiemlam")){
            navigation.inflateMenu(R.menu.bottom_navigation_bar);
        }
        else if(usertype.equals("quantrivien")){
            navigation.inflateMenu(R.menu.qtv_bottom_navigation_bar);
        }
        else{
            navigation.inflateMenu(R.menu.bottom_navigation_bar);
        }


        Intent intent = getIntent();
        String menuItem=intent.getStringExtra("currentMenuItem");
        if (menuItem != null)
        {
            switch(menuItem){
                case "forest":
                    navigation.setSelectedItemId(R.id.bnb_forest);
                    break;
                case "drone":
                    navigation.setSelectedItemId(R.id.bnb_drone);
                    break;
                case "action":
                    navigation.setSelectedItemId(R.id.bnb_action);
                    break;
                case "profile":
                    navigation.setSelectedItemId(R.id.bnb_profile);
                    break;
                case "users":
                    navigation.setSelectedItemId(R.id.bnb_admin_tool);
                    break;
                default:
                    navigation.setSelectedItemId(R.id.bnb_forest);
                    break;
            }
        }
        else
            navigation.setSelectedItemId(R.id.bnb_forest);
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
//                    intent.putExtra("currentMenuItem", "forest");
                    startActivity(intent);
                    return true;
                case R.id.bnb_drone:
                    saveabc("drone");
                    intent=new Intent(BaseActivity.this, DroneListActivity.class);
//                    intent.putExtra("currentMenuItem", "drone");
                    startActivity(intent);
                    return true;
                case R.id.bnb_action:
                    saveabc("action");
                    intent=new Intent(BaseActivity.this, ActionActivity.class);
//                    intent.putExtra("currentMenuItem", "action");
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
