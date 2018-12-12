package com.example.doannd.hci_2018_forestmanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.doannd.hci_2018_forestmanagement.MyDatabaseHelper;
import com.example.doannd.hci_2018_forestmanagement.R;
import com.example.doannd.hci_2018_forestmanagement.adapters.QAAdapter;
import com.example.doannd.hci_2018_forestmanagement.model.QA;

import java.util.List;

public class QAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        final ListView listView = (ListView)findViewById(R.id.listQA);

        MyDatabaseHelper db = MyDatabaseHelper.getInstance(QAActivity.this);
        List<QA> list=db.getAllQA();

        listView.setAdapter(new QAAdapter(this, list));

        // Khi người dùng click vào các ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                QA qa = (QA) o;

                Intent intent=new Intent(QAActivity.this, QADetailActivity.class);
                intent.putExtra("qaid",qa.getID());
                intent.putExtra("cauhoi",qa.getCauHoi());
                intent.putExtra("traloi",qa.getTraLoi());
                startActivity(intent);
            }
        });
    }
}
