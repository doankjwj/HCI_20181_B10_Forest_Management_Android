package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.DataBase.AppDataBase;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.DroneUltis;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.adapters.DroneListAdapter;

import java.util.ArrayList;

public class DroneListFragment extends Fragment {
    int droneStatus;
    ListView listViewDrone;
    DroneListAdapter droneListAdapter;
    ArrayList<Drone> listDrone;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.drone_fragment,container,false);
        reference(rootView);

        listDrone = new ArrayList<>();
        droneStatus = getArguments().getInt(getActivity().getResources().getString(R.string.bundleDroneStaus));
        droneListAdapter = new DroneListAdapter(getActivity(), R.layout.layout_drone_item, listDrone);
        listViewDrone.setAdapter(droneListAdapter);

        addData();

        return  rootView;
    }

    private void reference(View rootView) {
        listViewDrone = rootView.findViewById(R.id.listViewDrone);
    }

    private void addData() {
        AppDataBase appDataBase = new AppDataBase(getActivity(), getContext().getResources().getString(R.string.data_base_name), null, 1);
        String sql = "";
        switch (droneStatus)
        {
            case Const.DRONE_STATUS_FREE:
            case Const.DRONE_STATUS_MAINTENANCE:
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "'";
                break;
            case Const.DRONE_STATUS_BUSY:
                String userId = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "none");
                sql = "SELECT * FROM Drone WHERE Status == '" + droneStatus + "' AND UserControlling = '" + userId + "'";
                break;
        };
        Cursor cursor = appDataBase.getData(sql);
        while (cursor.moveToNext())
        {
            Drone drone = DroneUltis.createDroneByCursor(cursor);
            listDrone.add(drone);
        };
        droneListAdapter.notifyDataSetChanged();
    }
}
