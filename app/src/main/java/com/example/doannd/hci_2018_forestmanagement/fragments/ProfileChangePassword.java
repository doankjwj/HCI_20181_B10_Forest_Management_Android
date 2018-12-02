package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.R;

public class ProfileChangePassword extends Fragment {

    private Button btnChange;
    private TextView tvNotify;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.profile_changepass,container,false);

        btnChange=(Button)rootView.findViewById(R.id.btnChange);
        tvNotify=(TextView)rootView.findViewById(R.id.tvNotify);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1>2){
                    tvNotify.setText("Mật khẩu cũ không đúng");
                }
                else if (2>3){
                    tvNotify.setText("Mật khẩu nhập lại không đúng");
                }
                else{
                    Toast.makeText(getContext(),"Đổi mật khẩu thành công!",Toast.LENGTH_LONG).show();
                }
            }
        });

        return  rootView;
    }
}