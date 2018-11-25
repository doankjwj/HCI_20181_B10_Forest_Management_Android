package com.example.doannd.hci_2018_forestmanagement.activities;

import android.os.Bundle;

import com.example.doannd.hci_2018_forestmanagement.R;

public class ActionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        settingBottom();
    }
}
