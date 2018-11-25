package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.example.doannd.hci_2018_forestmanagement.R;

public class ProfileChangePassword extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.profile_changepass,container,false);
        return  rootView;
    }
}