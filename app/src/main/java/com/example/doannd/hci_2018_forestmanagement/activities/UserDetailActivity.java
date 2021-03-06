package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.User;

public class UserDetailActivity extends BaseActivity {

    private EditText edtUsername, edtHoTen, edtBirthDay;
    private Spinner spUserType;
    private String username, usertype, hoten, birthday, password;
    private Button btnUpdate, btnDelete,btnPass;
    private TextView tvNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent data = getIntent();
        username = data.getStringExtra("username");
        usertype = data.getStringExtra("usertype");
        hoten = data.getStringExtra("hoten");
        birthday = data.getStringExtra("birthday");
        password = data.getStringExtra("password");

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtBirthDay = (EditText) findViewById(R.id.edtBirthDay);
        spUserType = (Spinner) findViewById(R.id.spUserType);
        tvNotify = (TextView) findViewById(R.id.tvNotify);
        edtUsername.setText(username);
        edtHoTen.setText(hoten);
        edtBirthDay.setText(birthday);

        String arr[] = {
                "Kiểm lâm",
                "Phân tích viên",
                "Quản trị viên"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);

        spUserType.setAdapter(adapter);
        spUserType.setSelection(setPositonSpinner(usertype));
        //spUserType.setOnItemSelectedListener(new MyProcessEvent());

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnPass = (Button) findViewById(R.id.btnPass);

        final MyDatabaseHelper db = MyDatabaseHelper.getInstance(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String birthDay = edtBirthDay.getText().toString();
                if (hoTen.trim().equals("")) {
                    tvNotify.setText("Họ tên không được để trống!");
                } else {
                    db.updateUser(new User(username, password, getUserType(spUserType.getSelectedItemPosition()), hoTen, birthDay));
                    //tvNotify.setText("Cập nhật thành công!");
                    Intent intent = new Intent(UserDetailActivity.this, UsersActivity.class);
                    intent.putExtra("notify", "Cập nhật user thành công!");
                    startActivity(intent);
                }
            }
        });

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=db.getUser(username);
                user.setPassWord("1");
                db.updateUser(user);
                tvNotify.setText("Mật khẩu đã được đặt lại là \"1\"");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteUser(username);
                Intent intent = new Intent(UserDetailActivity.this, UsersActivity.class);
                intent.putExtra("notify", "Đã xóa user!");
                startActivity(intent);
            }
        });
    }

    public String getUserType(int pos) {
        switch (pos) {
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

    public int setPositonSpinner(String userType) {
        switch (userType) {
            case "kiemlam":
                return 0;
            case "phantichvien":
                return 1;
            case "quantrivien":
                return 2;
            default:
                return 0;
        }
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
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
