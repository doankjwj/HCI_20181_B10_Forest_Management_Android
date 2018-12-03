package com.example.doannd.hci_2018_forestmanagement.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.Video;

import java.util.List;

public class VideoGridAdapter extends BaseAdapter {
    private List<Video> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public VideoGridAdapter(Context aContext,  List<Video> listData) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_video_layout, null);
            holder = new ViewHolder();
            // holder.flagView = (ImageView) convertView.findViewById(R.id.imgUser);
            holder.droneView = (TextView) convertView.findViewById(R.id.tvDrone);
            holder.khuVucView = (TextView) convertView.findViewById(R.id.tvKhuVuc);
            holder.dateView = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Video video = this.listData.get(position);
        holder.droneView.setText(video.getDroneID());
        holder.khuVucView.setText(video.getKhuVucID());
        holder.dateView.setText(video.getDate());

        //int imageId = this.getMipmapResIdByName(user.getFlagName());

        //holder.flagView.setImageBitmap();

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        //ImageView flagView;
        TextView droneView;
        TextView khuVucView;
        TextView dateView;
    }
}
