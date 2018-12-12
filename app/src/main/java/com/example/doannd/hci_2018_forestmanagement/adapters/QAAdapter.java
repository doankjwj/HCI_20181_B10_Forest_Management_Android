package com.example.doannd.hci_2018_forestmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.QA;

import java.util.List;

public class QAAdapter extends BaseAdapter {
    private List<QA> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public QAAdapter(Context aContext,  List<QA> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        QAAdapter.QaViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_qa_layout, null);
            holder = new QAAdapter.QaViewHolder();
            // holder.flagView = (ImageView) convertView.findViewById(R.id.imgUser);
            holder.qaIdView = (TextView) convertView.findViewById(R.id.tvQAID);
            holder.cauHoiView = (TextView) convertView.findViewById(R.id.tvCauHoi);
            convertView.setTag(holder);
        } else {
            holder = (QAAdapter.QaViewHolder) convertView.getTag();
        }

        QA qa = this.listData.get(position);
        holder.qaIdView.setText("Q"+qa.getID()+": ");
        holder.cauHoiView.setText(qa.getCauHoi());

        //int imageId = this.getMipmapResIdByName(user.getFlagName());

        //holder.flagView.setImageBitmap();

        return convertView;
    }

    static class QaViewHolder {
        //ImageView flagView;
        TextView qaIdView;
        TextView cauHoiView;
    }
}
