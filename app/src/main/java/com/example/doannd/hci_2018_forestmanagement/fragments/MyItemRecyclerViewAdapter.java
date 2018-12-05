package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.model.History;
import com.example.doannd.hci_2018_forestmanagement.R;
//import com.example.doannd.hci_2018_forestmanagement.fragments.ProfileHistory.OnListFragmentInteractionListener;
import com.example.doannd.hci_2018_forestmanagement.fragments.dummy.DummyContent.DummyItem;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<History> mData;
    private int stt=1;

    public MyItemRecyclerViewAdapter(Context mContext, List<History> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       holder.item_number.setText(String.valueOf(stt)+".");
        holder.content.setText(mData.get(position).getAction());
        holder.date.setText(mData.get(position).getDate());
        stt++;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_number,content,date;

        public ViewHolder(View view) {
            super(view);

            item_number=(TextView) view.findViewById(R.id.item_number);;
            content = (TextView) view.findViewById(R.id.content);
            date = (TextView) view.findViewById(R.id.date);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
