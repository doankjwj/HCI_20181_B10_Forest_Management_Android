package com.example.doannd.hci_2018_forestmanagement.activities;

import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.activities.BaseActivity;

public class AnalyzeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        settingBottom();
    }
}
