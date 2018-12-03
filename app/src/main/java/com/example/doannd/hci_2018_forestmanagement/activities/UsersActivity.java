package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.User;
import com.example.doannd.hci_2018_forestmanagement.adapters.UserListAdapter;

import java.util.List;

public class UsersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Intent intent = getIntent();
        intent.putExtra("currentMenuItem","users");

        settingBottom();

        String notify = intent.getStringExtra("notify");
        if (notify!=null){
            Toast.makeText(UsersActivity.this,notify,Toast.LENGTH_LONG).show();
        }

        final ListView listView = (ListView)findViewById(R.id.listUsers);

        MyDatabaseHelper db = MyDatabaseHelper.getInstance(UsersActivity.this);
        List<User> users=db.getAllUsers();

        listView.setAdapter(new UserListAdapter(this, users));

        // Khi người dùng click vào các ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                User user = (User) o;

                Intent intent=new Intent(UsersActivity.this, UserDetailActivity.class);
                intent.putExtra("username",user.getUserName());
                intent.putExtra("password",user.getPassWord());
                intent.putExtra("usertype",user.getUserType());
                intent.putExtra("hoten",user.getHoTen());
                intent.putExtra("birthday",user.getBirthDay());
                startActivity(intent);
            }
        });

        Button btnAdd=(Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UsersActivity.this,NewUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
