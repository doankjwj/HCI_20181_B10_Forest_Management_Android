package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.LoginActivity;
import com.example.doannd.hci_2018_forestmanagement.R;

public class NewUserActivity extends AppCompatActivity {

    private EditText edtHoTen,edtUsername,edtPassword,edtPasswordAgain;
    private Button btnCreate,btnReset;
    private TextView tvNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtHoTen = (EditText)findViewById(R.id.edtHoTen);
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtPasswordAgain = (EditText)findViewById(R.id.edtPasswordAgain);

        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnReset=(Button)findViewById(R.id.btnReset);

        tvNotify=(TextView)findViewById(R.id.tvNotify);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtUsername.getText().toString(),edtPassword.getText().toString(),edtPasswordAgain.getText().toString());
            }
        });
    }

    private void validate(String userName, String password, String rePassword){
        if (1>2){
            //đã tồn tại username
            tvNotify.setText("Username đã tồn tại!");
        }
        else if (2>3){
            //rePass ko đúng
            tvNotify.setText("Nhập lại mật khẩu không đúng");
        }
        else {
            Intent intent=new Intent(NewUserActivity.this, UsersActivity.class);
            startActivity(intent);
        }
    }
}
