package com.example.doannd.hci_2018_forestmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doannd.hci_2018_forestmanagement.Data.Drone;
import com.example.doannd.hci_2018_forestmanagement.ActivityDroneDetail;
import com.example.doannd.hci_2018_forestmanagement.Function.Const;
import com.example.doannd.hci_2018_forestmanagement.Function.StringUtls;
import com.example.doannd.hci_2018_forestmanagement.R;

import java.util.ArrayList;

public class DroneListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Drone> listDrone;

    public DroneListAdapter(Context context, int layout, ArrayList<Drone> listDrone) {
        this.context = context;
        this.layout = layout;
        this.listDrone = listDrone;
    }

    @Override
    public int getCount() {
        return listDrone.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgDrone = (ImageView) convertView.findViewById(R.id.droneImg);
            viewHolder.txtId = (TextView) convertView.findViewById(R.id.txtId);
            viewHolder.txtLatitude = (TextView) convertView.findViewById(R.id.txtLatitude);
            viewHolder.txtLongitude = (TextView) convertView.findViewById(R.id.txtLongitude);
            viewHolder.btnMore = (ImageButton) convertView.findViewById(R.id.btnMore);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Drone drone = listDrone.get(position);
        int imgResource = 0;
        int idColor = 0;
        switch (drone.getStatus())
        {
            case Const.DRONE_STATUS_FREE:
                imgResource = R.drawable.drone_free;
                idColor = context.getResources().getColor(R.color.droneFree);
                break;
            case Const.DRONE_STATUS_BUSY:
//                imgResource = R.drawable.drone_monitor;
                imgResource = R.drawable.forest_demo;
                idColor = context.getResources().getColor(R.color.blue_light);
                break;
            case Const.DRONE_STATUS_MAINTENANCE:
                imgResource = R.drawable.drone_manertance;
                idColor = context.getResources().getColor(R.color.droneMaintance);
                break;
            default:
                imgResource = R.drawable.drone_grey;
                break;
        }
        viewHolder.imgDrone.setImageResource(imgResource);;
        viewHolder.txtId.setText(context.getResources().getString(R.string.id) + " " + StringUtls.longString(String.valueOf(drone.getId()), 5, '0', true));
        viewHolder.txtId.setTextColor(idColor);
        viewHolder.txtLatitude.setText(context.getResources().getString(R.string.area_latidude) + " " + drone.getLatitude());
        viewHolder.txtLongitude.setText(context.getResources().getString(R.string.area_longitude) + " " + drone.getLongitude());
        viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDroneInfo(drone);
            }
        });

        return convertView;
    }

    private class ViewHolder{
        ImageView imgDrone;
        TextView txtId;
        TextView txtLatitude;
        TextView txtLongitude;
        ImageButton btnMore;
    }

    private void viewDroneInfo(Drone drone)
    {
        Intent intent = new Intent(context, ActivityDroneDetail.class);
        intent.putExtra(context.getResources().getString(R.string.bundleDroneInfo), drone);
        context.startActivity(intent);
    }
}
