package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.User;
import com.example.doannd.hci_2018_forestmanagement.adapters.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        settingBottom();

        final ListView listView = (ListView)findViewById(R.id.listUsers);

        List<User> users = new ArrayList<>();
        users.add(new User("kiemlam","1234","Kiểm lâm","Nguyễn Đình Khiêm","1/1/2000"));
        users.add(new User("quantrivien","1234","Quản trị viên","Hoa Nguyễn","1/1/2000"));
        users.add(new User("kiemlam","1234","Kiểm lâm","Nguyễn Đình Khiêm","1/1/2000"));
        users.add(new User("kiemlam","1234","Kiểm lâm","Hoa Nguyễn","1/1/2000"));
        users.add(new User("kiemlam","1234","Kiểm lâm","Nguyễn Đình Khiêm","1/1/2000"));
        users.add(new User("phantichvien","1234","Phân tích viên","Hoa Nguyễn","1/1/2000"));
        users.add(new User("quantrivien","1234","Quản trị viên","Nguyễn Đình Khiêm","1/1/2000"));


        // android.R.layout.simple_list_item_1: Là một hằng số Layout định nghĩa sẵn của Android
        // ý nghĩa của nó là ListView với ListItem đơn giản (Duy nhất một TextView).
// dùng mảng
        //ArrayAdapter<User> arrayAdapter
               // = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1 , users);

        //listView.setAdapter(arrayAdapter);

        listView.setAdapter(new UserListAdapter(this, users));

        // Khi người dùng click vào các ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                User user = (User) o;
                Toast.makeText(UsersActivity.this, "Selected :" + " " + user, Toast.LENGTH_LONG).show();

                Intent intent=new Intent(UsersActivity.this, UserDetailActivity.class);
                intent.putExtra("username",user.getUserName());
                intent.putExtra("usertype",user.getUserType());
                intent.putExtra("hoten",user.getHoTen());
                intent.putExtra("birthday",user.getBirthDay());
                startActivity(intent);
            }
        });
    }
}
