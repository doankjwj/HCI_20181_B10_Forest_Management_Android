package com.example.doannd.hci_2018_forestmanagement.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.User;

import java.util.List;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView info;
    EditText edtUsername,edtPassword;
    Button btnLogin;
    //Nhập sai 5 lần
    int counter = 5;

    //actor
    User user;
    List<User> listUser =new ArrayList<User>();

    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText)findViewById(R.id.edittextuser);
        edtPassword = (EditText)findViewById(R.id.edittextpass);
        btnLogin=(Button)findViewById(R.id.buttonLogin);
        info=(TextView)findViewById(R.id.tvInfo);
        info.setText("Số lần còn lại: " + 5);

        //database
        db = MyDatabaseHelper.getInstance(LoginActivity.this);
        //db.onUpgrade(db.getReadableDatabase(),1,2);
        db.createDefaultDatabase();

        createUsers();

        requestPermission();

        autoFillForm();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtUsername.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void autoFillForm() {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");
        edtUsername.setText(userName);
        edtPassword.setText(password);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.CAMERA},
            2);
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Xin cấp quyền thành công !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"Xin cấp quyền thất bại !", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void createUsers(){
        User user1=new User("kiemlam","1234","Kiểm lâm","Nguyễn Đình Khiêm", "1/1/2000");
        User user2=new User("quantrivien","1234","Quản trị viên","Nguyễn Đình Khiêm","1/1/2000");
        User user3=new User("phantichvien","1234","Phân tích viên","Nguyễn Đình Khiêm","1/1/2000");
        User user4=new User("a","a","Phân tích viên","Nguyễn Đình Khiêm","1/1/2000");
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);
    }

    private void validate(String userName, String password){
        boolean flag=false;
        List<User> list=db.getAllUsers();
        for(int i=0;i<list.size();i++){
            if ((userName.equals(list.get(i).getUserName()))&& (password.equals(list.get(i).getPassWord()))){
                saveInfo(list.get(i));
                flag=true;
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        if (!flag){
            counter--;
            info.setText("Số lần còn lại: " + String.valueOf(counter));
            if (counter==0){
                btnLogin.setEnabled(false);
            }
        }
    }

    private void saveInfo(User user){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",user.getUserName());
        editor.putString("password",user.getPassWord());
        editor.putString("usertype",user.getUserType());
        editor.putString("hoten",user.getHoTen());
        editor.putString("birthday",user.getBirthDay());
        editor.apply();
    }
}