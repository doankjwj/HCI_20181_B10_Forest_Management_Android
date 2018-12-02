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

import com.example.doannd.hci_2018_forestmanagement.R;

import static java.security.AccessController.getContext;


public class ProfileUpdate extends Fragment {

    TextView tvUsername,tvUserType;
    EditText edtUsername,edtUserType,edtHoTen,edtBirthDay;
    Button btnUpdate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.profile_update,container,false);

        tvUsername = (TextView) rootView.findViewById(R.id.tvUsername);
        tvUserType = (TextView) rootView.findViewById(R.id.tvUserType);
        edtUsername = (EditText)rootView.findViewById(R.id.edtUsername);
        edtUserType = (EditText)rootView.findViewById(R.id.edtUserType);
        edtHoTen = (EditText)rootView.findViewById(R.id.edtHoTen);
        edtBirthDay=(EditText)rootView.findViewById(R.id.edtBirthDay);
        btnUpdate=(Button)rootView.findViewById(R.id.btnUpdate);

        SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        edtUsername.setText(sharedPref.getString("username",""));
        edtUserType.setText(sharedPref.getString("usertype",""));
        edtHoTen.setText(sharedPref.getString("hoten",""));
        edtBirthDay.setText(sharedPref.getString("birthday",""));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Cập nhật thành công!",Toast.LENGTH_LONG).show();
            }
        });

        return  rootView;
    }
}
