package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;

public class DroneFragment extends Fragment {
    EditText edtUsername,edtHoTen,edtUserType;
    Button btnBack,btnUpdate;

    Spinner spnChucNang;

    String[] chucnang = {"", "Livestream", "Điều khiển", "Lộ trình bay", "Thông tin"};

    TextView lblban1;
    TextView lblban2;
    TextView lblban3;
    TextView lblban4;
    TextView lblban5;
    TextView lblban6;
    TextView lblban7;
    TextView lblban8;
    TextView lblban9;
    TextView lblban10;
    TextView lblban11;
    TextView lblban12;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.drone_fragment,container,false);

        return  rootView;
    }


}
