package com.example.wgujoshlongenecker.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.database.AppRepo;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.ArrayList;
import java.util.List;


public class ScheduledAssessments extends AppCompatActivity {

    private Button assessmentAdd;
    AppDatabase appDB;
    private RecyclerView assessmentsRView;
    private ArrayList<Assessments> assessmentsList;
    List<Assessments> allAssessments;
    AppRepo appRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_assessments);

        assessmentAdd = (Button) findViewById(R.id.assessmentAdd);
        appDB = AppDatabase.getInstance(getApplicationContext());
        assessmentsRView = findViewById(R.id.assessmentsRView);
        assessmentsList = new ArrayList<>();
//        appRepo = new AppRepo(getApplication());
//        appRepo.getAllAssessments();

        updateLists();
        setAdapter();
    }

    private void setAdapter() {
        AssessmentAdapter adapter = new AssessmentAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        assessmentsRView.setLayoutManager(layoutManager);
        assessmentsRView.setItemAnimator(new DefaultItemAnimator());
        assessmentsRView.setAdapter(adapter);
        adapter.setAssessments(assessmentsList);
    }

    private void updateLists() {
        List<Assessments> allAssessments = appDB.assessmentDao().getAssessments();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allTerms);
//        scheduledView.setAdapter(adapter);
//        scheduledView.setOnItemClickListener(listClick);
//        this.allTerms = allTerms;
//        adapter.notifyDataSetChanged();

        for (Assessments a : allAssessments) {
            assessmentsList.add(a);
        }
    }
}
