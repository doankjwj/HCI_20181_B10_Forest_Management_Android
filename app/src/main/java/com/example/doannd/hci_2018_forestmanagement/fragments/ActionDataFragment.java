package com.example.doannd.hci_2018_forestmanagement.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.model.District;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActionDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spKhuVuc,spLoaiXuLy,spHinhThucXuLy;
    private TextView tvNhietDo,tvDoAm,tvDoBaoPhu,tvNguyCoChayRung,tvKhaNangXamHai;
    private Button btnXuLy,btnGui;
    private ConstraintLayout layoutResult;
    private EditText edtGhiChu;

//    private OnFragmentInteractionListener mListener;

    public ActionDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionDataFragment newInstance(String param1, String param2) {
        ActionDataFragment fragment = new ActionDataFragment();
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
        View view=inflater.inflate(R.layout.fragment_action_data, container, false);

        spKhuVuc=(Spinner) view.findViewById(R.id.spKhuVuc);
        spLoaiXuLy=(Spinner) view.findViewById(R.id.spLoaiXuLy);
        spHinhThucXuLy=(Spinner) view.findViewById(R.id.spHinhThucXuLy);

        tvNhietDo=(TextView) view.findViewById(R.id.tvNhietDo);
        tvDoAm=(TextView) view.findViewById(R.id.tvDoAm);
        tvDoBaoPhu=(TextView) view.findViewById(R.id.tvDoBaoPhu);
        tvNguyCoChayRung=(TextView) view.findViewById(R.id.tvNguyCoChayRung);
        tvKhaNangXamHai=(TextView) view.findViewById(R.id.tvKhaNangXamHai);
        edtGhiChu=(EditText) view.findViewById(R.id.edtGhiChu);

        btnXuLy=(Button) view.findViewById(R.id.btnXuLy);
        btnGui=(Button) view.findViewById(R.id.btnGui);

        layoutResult =(ConstraintLayout) view.findViewById(R.id.layoutResult);

        final MyDatabaseHelper db=MyDatabaseHelper.getInstance(getContext());

        List<District> listKhuVuc=db.getAllKhuVuc();
        //ArrayList<District> arr=new ArrayList<District>();
        //arr.addAll(listKhuVuc);

        String arr[]=new String[listKhuVuc.size()];
        int i=0;
        for (District d: listKhuVuc
             ) {
            arr[i]=d.getID();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);

        spKhuVuc.setAdapter(adapter);
//        spKhuVuc.setSelection(0);

        String arr1[] = {
                "Xử lý cháy rừng",
                "Xử lý xâm hại"
        };

        String arr2[] = {
                "Rà soát lại",
                "Cảnh báo",
                "Yêu cầu kiểm tra trực tiếp"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1);
        adapter1.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);

        spLoaiXuLy.setAdapter(adapter1);
//        spLoaiXuLy.setSelection(0);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr2);
        adapter2.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);

        spHinhThucXuLy.setAdapter(adapter2);
//        spHinhThucXuLy.setSelection(0);

        spKhuVuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=spKhuVuc.getSelectedItem().toString();
                District district =db.getKhuVuc(s);
                tvNhietDo.setText(district.getNhietDo());
                tvDoAm.setText(district.getDoAm());
                tvDoBaoPhu.setText(district.getDoBaoPhu());
                tvNguyCoChayRung.setText(district.getNguyCoChayRung());
                tvKhaNangXamHai.setText(district.getKhaNangXamHai());

                if (district.getXuLy()==1){
                    btnXuLy.setText("Đã xử lý");
                    layoutResult.setVisibility(View.VISIBLE);
                    spLoaiXuLy.setEnabled(false);
                    spHinhThucXuLy.setEnabled(false);
                    edtGhiChu.setEnabled(false);
                    btnGui.setVisibility(View.INVISIBLE);
                }
                else {
                    btnXuLy.setText("Quyết định xử lý");
                    spLoaiXuLy.setEnabled(true);
                    spHinhThucXuLy.setEnabled(true);
                    edtGhiChu.setEnabled(true);
                    layoutResult.setVisibility(View.INVISIBLE);
                    btnGui.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spLoaiXuLy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spHinhThucXuLy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutResult.getVisibility()==View.VISIBLE){
                    layoutResult.setVisibility(View.INVISIBLE);
                }
                else{
                    layoutResult.setVisibility(View.VISIBLE);
                }
            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=spKhuVuc.getSelectedItem().toString();
                District khuVuc =db.getKhuVuc(s);
                khuVuc.setXuLy(1);
                db.updateDistrict(khuVuc);
                spLoaiXuLy.setEnabled(false);
                spHinhThucXuLy.setEnabled(false);
                edtGhiChu.setEnabled(false);
                btnXuLy.setText("Đã xử lý");
                Toast.makeText(getContext(),"Đa gửi quyết định xử lý!",Toast.LENGTH_LONG).show();
            }
        });

        spKhuVuc.setSelection(0);

        return view;
    }


}
