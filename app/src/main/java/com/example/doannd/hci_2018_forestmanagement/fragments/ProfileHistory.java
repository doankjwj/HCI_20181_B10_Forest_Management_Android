package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.model.History;
import com.example.doannd.hci_2018_forestmanagement.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ProfileHistory extends Fragment{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;

    View v;
    private RecyclerView myrecycleview;
    private List<History> listHistory;

    public ProfileHistory() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProfileHistory newInstance(int columnCount) {
        ProfileHistory fragment = new ProfileHistory();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        MyDatabaseHelper db= MyDatabaseHelper.getInstance(getContext());
        listHistory=db.getHistoriesByUsername(sharedPref.getString("username",""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_history, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        myrecycleview=(RecyclerView) view.findViewById(R.id.list);
        MyItemRecyclerViewAdapter rAdapter=new MyItemRecyclerViewAdapter(getContext(),listHistory);
        myrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecycleview.setAdapter(rAdapter);

        return view;
    }
}
