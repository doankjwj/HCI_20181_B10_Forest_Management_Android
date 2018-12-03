package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.User;

import static java.security.AccessController.getContext;

public class ProfileUpdate extends Fragment {

    TextView tvUsername, tvUserType;
    EditText edtUsername, edtUserType, edtHoTen, edtBirthDay;
    Button btnUpdate;
    String username,password,usertype,hoten,birthday;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_update, container, false);

        tvUsername = (TextView) rootView.findViewById(R.id.tvUsername);
        tvUserType = (TextView) rootView.findViewById(R.id.tvUserType);
        edtUsername = (EditText) rootView.findViewById(R.id.edtUsername);
        edtUserType = (EditText) rootView.findViewById(R.id.edtUserType);
        edtHoTen = (EditText) rootView.findViewById(R.id.edtHoTen);
        edtBirthDay = (EditText) rootView.findViewById(R.id.edtBirthDay);
        btnUpdate = (Button) rootView.findViewById(R.id.btnUpdate);

        SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        username=sharedPref.getString("username", "");
        password=sharedPref.getString("password", "");
        usertype=sharedPref.getString("usertype", "");
        hoten=sharedPref.getString("hoten", "");
        birthday=sharedPref.getString("birthday", "");

        edtUsername.setText(username);
        edtUserType.setText(usertype);
        edtHoTen.setText(hoten);
        edtBirthDay.setText(birthday);

        final MyDatabaseHelper db = MyDatabaseHelper.getInstance(getContext());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtHoTen.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Họ tên không được để trống!", Toast.LENGTH_LONG).show();
                }
                else{
                    hoten=edtHoTen.getText().toString().trim();
                    birthday=edtBirthDay.getText().toString().trim();
                    db.updateUser(new User(username,password,usertype,hoten,birthday));
                    SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPref.edit();
                    editor.putString("password",newPass);
                    editor.apply();
                    Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }
}
