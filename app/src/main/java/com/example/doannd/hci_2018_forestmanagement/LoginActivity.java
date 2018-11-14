package com.example.doannd.hci_2018_forestmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView info;
    EditText edtUsername,edtPassword;
    Button btnLogin;
    //Nhập sai 5 lần
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText)findViewById(R.id.edittextuser);
        edtPassword = (EditText)findViewById(R.id.edittextpass);
        btnLogin=(Button)findViewById(R.id.buttonLogin);
        info=(TextView)findViewById(R.id.tvInfo);
        info.setText("Số lần còn lại: " + 5);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtUsername.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void validate(String userName, String password){
        if ((userName.equals("Admin"))&& (password.equals("1234"))){
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            counter--;
            info.setText("Số lần còn lại: " + String.valueOf(counter));
            if (counter==0){
                btnLogin.setEnabled(false);
            }
        }
    }
}