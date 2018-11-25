package com.example.doannd.hci_2018_forestmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.activities.MainActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText)findViewById(R.id.edittextuser);
        edtPassword = (EditText)findViewById(R.id.edittextpass);
        btnLogin=(Button)findViewById(R.id.buttonLogin);
        info=(TextView)findViewById(R.id.tvInfo);
        info.setText("Số lần còn lại: " + 5);

        createUsers();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtUsername.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void createUsers(){
        User user1=new User("kiemlam","1234","Kiểm lâm","Nguyễn Đình Khiêm", "1/1/2000");
        User user2=new User("quantrivien","1234","Quản trị viên","Nguyễn Đình Khiêm","1/1/2000");
        User user3=new User("phantichvien","1234","Phân tích viên","Nguyễn Đình Khiêm","1/1/2000");
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
    }

    private void validate(String userName, String password){
        boolean flag=false;
        for(int i=0;i<listUser.size();i++){
            if ((userName.equals(listUser.get(i).userName))&& (password.equals(listUser.get(i).passWord))){
                saveInfo(listUser.get(i));
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
        editor.putString("username",user.userName);
        editor.putString("password",user.passWord);
        editor.putString("usertype",user.userType);
        editor.putString("hoten",user.hoTen);
        editor.putString("birthday",user.birthDay);
        editor.apply();
    }
}