package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.R;

public class UserDetailActivity extends BaseActivity {

    private EditText edtUsername,edtHoTen,edtBirthDay;
    private Spinner spUserType;
    private String username,usertype,hoten,birthday;
    private Button btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent data = getIntent();
        username=data.getStringExtra("username");
        usertype=data.getStringExtra("usertype");
        hoten=data.getStringExtra("hoten");
        birthday=data.getStringExtra("birthday");

        edtUsername=(EditText) findViewById(R.id.edtUsername);
        edtHoTen=(EditText) findViewById(R.id.edtHoTen);
        edtBirthDay=(EditText) findViewById(R.id.edtBirthDay);
        edtUsername.setText(username);
        edtHoTen.setText(hoten);
        edtBirthDay.setText(birthday);

        String arr[]={
                "Kiểm lâm",
                "Phân tích viên",
                "Quản trị viên"
        };
        spUserType=(Spinner) findViewById(R.id.spUserType);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spUserType.setAdapter(adapter);
        spUserType.setSelection(adapter.getPosition(usertype));
        spUserType.setOnItemSelectedListener(new MyProcessEvent());

        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        btnDelete=(Button) findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDetailActivity.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserDetailActivity.this,UsersActivity.class);
                intent.putExtra("notify","Đã xóa");
                startActivity(intent);
            }
        });
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            Toast.makeText(UserDetailActivity.this, spUserType.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
}
