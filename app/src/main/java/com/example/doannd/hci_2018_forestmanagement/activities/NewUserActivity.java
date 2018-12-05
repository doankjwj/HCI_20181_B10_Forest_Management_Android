package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.User;

public class NewUserActivity extends AppCompatActivity {

    private EditText edtHoTen,edtUsername;
    private Button btnCreate,btnReset;
    private TextView tvNotify;
    private Spinner spUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtHoTen = (EditText)findViewById(R.id.edtHoTen);
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        spUserType=(Spinner)findViewById(R.id.spUserType);

        String arr[]={
                "Kiểm lâm",
                "Phân tích viên",
                "Quản trị viên"
        };

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spUserType.setAdapter(adapter);
        spUserType.setSelection(0);

        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnReset=(Button)findViewById(R.id.btnReset);

        tvNotify=(TextView)findViewById(R.id.tvNotify);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen=edtHoTen.getText().toString();
                String userName=edtUsername.getText().toString();
                MyDatabaseHelper db=MyDatabaseHelper.getInstance(NewUserActivity.this);
                if (hoTen.trim().equals("")){
                    tvNotify.setText("Họ tên không được để trống!");
                }
                else if (userName.trim().equals("")){
                    tvNotify.setText("Username không được để trống!");
                }
                else if (db.checkExists(userName)){
                    tvNotify.setText("Username đã tồn tại!");
                }
                else {
                    db.addUser(new User(userName,"1",getUserType(spUserType.getSelectedItemPosition()),edtHoTen.getText().toString(),"1/1/2000"));
                    Intent intent=new Intent(NewUserActivity.this, UsersActivity.class);
                    intent.putExtra("notify","Thêm user thành công!");
                    startActivity(intent);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHoTen.setText("");
                edtUsername.setText("");
                spUserType.setSelection(0);
            }
        });
    }

    public String getUserType(int pos){
        switch (pos){
            case 0:
                return "kiemlam";
            case 1:
                return "phantichvien";
            case 2:
                return "quantrivien";
            default:
                return "kiemlam";
        }
    }
}
