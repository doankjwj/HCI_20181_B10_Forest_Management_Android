package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.Video;
import com.example.doannd.hci_2018_forestmanagement.activities.VideoDetail;
import com.example.doannd.hci_2018_forestmanagement.adapters.VideoGridAdapter;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ActionVideoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView listView;
    private String sort;

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;
    private static final int MY_REQUEST_CODE = 1000;

    private final List<Video> noteList = new ArrayList<Video>();
    private ArrayAdapter<Video> listViewAdapter;

    private String mParam1;
    private String mParam2;

    private Button btnTheoDrone,btnTheoNgay,btnTheoKhuVuc;

    public ActionVideoFragment() {
        // Required empty public constructor
    }

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

        final GridView gridView = (GridView) view.findViewById(R.id.grvVideo);

        //final MyDatabaseHelper db = new MyDatabaseHelper(getContext());
        final MyDatabaseHelper db = MyDatabaseHelper.getInstance(getContext());
        //db.createDefaultNotesIfNeed();

        List<Video> list=  db.getAllVideos("Video_Drone_Id");
        this.noteList.addAll(list);

//        this.listViewAdapter = new ArrayAdapter<Video>(getContext(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList);
//
//
//        // Đăng ký Adapter cho ListView.
//        this.listView.setAdapter(this.listViewAdapter);
//
//        // Đăng ký Context menu cho ListView.
//        registerForContextMenu(this.listView);

//        Video v1=new Video("video1","drone1","khuvuc1","20/11/2018");
//        Video v2=new Video("video2","drone2","khuvuc1","21/11/2018");
//        Video v3=new Video("video3","drone2","khuvuc2","22/11/2018");
//        Video v4=new Video("video4","drone3","khuvuc3","23/11/2018");
//        Video v5=new Video("video5","drone3","khuvuc5","24/11/2018");
//        Video v6=new Video("video6","drone3","khuvuc8","25/11/2018");
//
////        Video[] videos=new Video[]{v1,v2,v3,v4,v5,v6};
//        List<Video> videos =new ArrayList<>();
//        videos.add(v1);
//        videos.add(v2);
//        videos.add(v3);
//        videos.add(v4);
//        videos.add(v5);
//        videos.add(v6);
//
////        ArrayAdapter<Video> arrayAdapter
////                = new ArrayAdapter<Video>(getContext(), android.R.layout.list , videos);

        gridView.setAdapter(new VideoGridAdapter(getContext(),this.noteList));
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

        btnTheoDrone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //MyDatabaseHelper db = new MyDatabaseHelper(getContext());
                List<Video> list=  db.getAllVideos("Video_Drone_Id");
                noteList.clear();
                noteList.addAll(list);
                gridView.setAdapter(new VideoGridAdapter(getContext(),noteList));
                gridView.invalidateViews();
            }
        });

        btnTheoKhuVuc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                List<Video> list=  db.getAllVideos("Video_KhuVuc_Id");
                noteList.clear();
                noteList.addAll(list);
                gridView.setAdapter(new VideoGridAdapter(getContext(),noteList));
                gridView.invalidateViews();
            }
        });

        btnTheoNgay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                List<Video> list=  db.getAllVideos("Video_Date");
                noteList.clear();
                noteList.addAll(list);
                gridView.setAdapter(new VideoGridAdapter(getContext(),noteList));
                gridView.invalidateViews();
            }
        });

        return view;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View view,
//                                    ContextMenu.ContextMenuInfo menuInfo)    {
//        //super.onCreateContextMenu(menu, view, menuInfo);
//        menu.setHeaderTitle("Select The Action");
//
//        // groupId, itemId, order, title
//        menu.add(0, MENU_ITEM_VIEW , 0, "View Video");
//        menu.add(0, MENU_ITEM_CREATE , 1, "Create Video");
//        menu.add(0, MENU_ITEM_EDIT , 2, "Edit Video");
//        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Video");
//    }

    //chỗ này để thêm sửa xóa
//    @Override
//    public boolean onContextItemSelected(MenuItem item){
//        AdapterView.AdapterContextMenuInfo
//                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//
//        final Video selectedNote = (Video) this.listView.getItemAtPosition(info.position);
//
//        if(item.getItemId() == MENU_ITEM_VIEW){
//            Toast.makeText(getActivity().getApplicationContext(),selectedNote.getVideoID(),Toast.LENGTH_LONG).show();
//        }
//        else if(item.getItemId() == MENU_ITEM_CREATE){
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent, MY_REQUEST_CODE);
//        }
//        else if(item.getItemId() == MENU_ITEM_EDIT ){
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//            intent.putExtra("note", selectedNote);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent,MY_REQUEST_CODE);
//        }
//        else if(item.getItemId() == MENU_ITEM_DELETE){
//            // Hỏi trước khi xóa.
//            new AlertDialog.Builder(this)
//                    .setMessage(selectedNote.getNoteTitle()+". Are you sure you want to delete?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            deleteNote(selectedNote);
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//        else {
//            return false;
//        }
//        return true;
//    }
}
