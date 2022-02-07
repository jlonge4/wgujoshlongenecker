package com.example.wgujoshlongenecker.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;

import java.util.List;


public class ScheduledCourses extends AppCompatActivity {

    private Button courseUpdate;
    private Button courseAdd;
    private Button courseDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_courses);

        courseAdd = (Button) findViewById(R.id.termAdd);
//        courseUpdate = (Button) findViewById(R.id.termUpdate);
//        courseDelete = (Button) findViewById(R.id.termDelete);

    }

//    private void updateLists() {
//        List<String> allTerms = appDB.termDao().getTerms();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allTerms);
//        scheduledView.setAdapter(adapter);
//        scheduledView.setOnItemClickListener(listClick);
//        this.allTerms = allTerms;
//        adapter.notifyDataSetChanged();
//    }
}
