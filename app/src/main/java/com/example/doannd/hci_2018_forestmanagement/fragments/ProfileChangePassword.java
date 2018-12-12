package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.activities.ProfileActivity;
import com.example.doannd.hci_2018_forestmanagement.model.User;

import static java.security.AccessController.getContext;

public class ProfileChangePassword extends Fragment {

    private Button btnChange;
    private TextView tvNotify;
    private EditText edtOldPass,edtNewPass,edtRePass;
    private String oldPass,newPass,rePass;
    private String username,password,usertype,hoten,birthday;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.profile_changepass,container,false);

        edtOldPass=(EditText)rootView.findViewById(R.id.edtOldPass);
        edtNewPass=(EditText)rootView.findViewById(R.id.edtNewPass);
        edtRePass=(EditText)rootView.findViewById(R.id.edtNewAgain);

        btnChange=(Button)rootView.findViewById(R.id.btnChange);
        tvNotify=(TextView)rootView.findViewById(R.id.tvNotify);

        SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        username=sharedPref.getString("username", "");
        password=sharedPref.getString("password", "");
        usertype=sharedPref.getString("usertype", "");
        hoten=sharedPref.getString("hoten", "");
        birthday=sharedPref.getString("birthday", "");

        final MyDatabaseHelper db = MyDatabaseHelper.getInstance(getContext());

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass=edtOldPass.getText().toString().trim();
                newPass=edtNewPass.getText().toString().trim();
                rePass=edtRePass.getText().toString().trim();

                if (!oldPass.equals(password)){
                    Toast.makeText(getContext(),"Mật khẩu cũ không đúng!",Toast.LENGTH_LONG).show();
                }
                else if (!newPass.equals(rePass)){
                    Toast.makeText(getContext(),"Mật khẩu nhập lại không đúng!",Toast.LENGTH_LONG).show();
                }
                else{
                    User user=db.getUser(username);
                    user.setPassWord(newPass);
                    db.updateUser(user);
                    SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPref.edit();
                    editor.putString("password",newPass);
                    editor.apply();
                    password=newPass;
//                    android.support.v4.app.Fragment frg = null;
//                    frg = getFragmentManager().findFragmentByTag("ProfileChangePassword");
//                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    ft.detach(frg).attach(frg).commit();
                    edtOldPass.setText("");
                    edtNewPass.setText("");
                    edtRePass.setText("");
                    Toast.makeText(getContext(),"Đổi mật khẩu thành công!",Toast.LENGTH_LONG).show();
                }
            }
        });

        return  rootView;
    }
}