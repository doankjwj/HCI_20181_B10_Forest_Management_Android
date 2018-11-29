package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.User;
import com.example.doannd.hci_2018_forestmanagement.Video;
import com.example.doannd.hci_2018_forestmanagement.activities.ActionActivity;
import com.example.doannd.hci_2018_forestmanagement.activities.UserDetailActivity;
import com.example.doannd.hci_2018_forestmanagement.activities.UsersActivity;
import com.example.doannd.hci_2018_forestmanagement.activities.VideoDetail;
import com.example.doannd.hci_2018_forestmanagement.adapters.VideoGridAdapter;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionVideoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActionVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionVideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnTheoDrone,btnTheoNgay,btnTheoKhuVuc;

    public ActionVideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionVideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionVideoFragment newInstance(String param1, String param2) {
        ActionVideoFragment fragment = new ActionVideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_action_video, container, false);

        btnTheoDrone=(Button) view.findViewById(R.id.btnTheoDrone);
        btnTheoKhuVuc=(Button) view.findViewById(R.id.btnTheoKhuVuc);
        btnTheoNgay=(Button) view.findViewById(R.id.btnTheoNgay);

        btnTheoDrone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //sort gridview
            }
        });

        btnTheoKhuVuc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });

        btnTheoNgay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });

        final GridView gridView = (GridView) view.findViewById(R.id.grvVideo);

        Video v1=new Video("video1","drone1","khuvuc1","20/11/2018");
        Video v2=new Video("video2","drone2","khuvuc1","21/11/2018");
        Video v3=new Video("video3","drone2","khuvuc2","22/11/2018");
        Video v4=new Video("video4","drone3","khuvuc3","23/11/2018");
        Video v5=new Video("video5","drone3","khuvuc5","24/11/2018");
        Video v6=new Video("video6","drone3","khuvuc8","25/11/2018");

//        Video[] videos=new Video[]{v1,v2,v3,v4,v5,v6};
        List<Video> videos =new ArrayList<>();
        videos.add(v1);
        videos.add(v2);
        videos.add(v3);
        videos.add(v4);
        videos.add(v5);
        videos.add(v6);

//        ArrayAdapter<Video> arrayAdapter
//                = new ArrayAdapter<Video>(getContext(), android.R.layout.list , videos);


        gridView.setAdapter(new VideoGridAdapter(getContext(),videos));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Object o = gridView.getItemAtPosition(position);
//                Video video = (Video) o;
//                Toast.makeText(getContext(), "Selected :"
//                                + " " + video.getVideoID()+"\n("+ video.getDroneID()+")",
//                        Toast.LENGTH_LONG).show();
                Object o = gridView.getItemAtPosition(position);
                Video video = (Video) o;

                Intent intent=new Intent(getContext(), VideoDetail.class);
                intent.putExtra("videoid",video.getVideoID());
                intent.putExtra("droneid",video.getDroneID());
                intent.putExtra("khuvucid",video.getKhuVucID());
                intent.putExtra("date",video.getDate());
                startActivity(intent);
            }
        });
        return view;
    }
}
