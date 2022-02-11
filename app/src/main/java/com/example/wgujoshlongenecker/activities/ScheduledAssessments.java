package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_assessments);

        assessmentAdd = (Button) findViewById(R.id.assessmentAdd);
        appDB = AppDatabase.getInstance(getApplicationContext());
        assessmentsRView = findViewById(R.id.assessmentsRView);
        assessmentsList = new ArrayList<>();
        Intent intent = getIntent();
        message = intent.getStringExtra("courseId");

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
        List<Assessments> allAssessments = appDB.assessmentDao().getAssociatedAssessments(message);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allTerms);
//        scheduledView.setAdapter(adapter);
//        scheduledView.setOnItemClickListener(listClick);
//        this.allTerms = allTerms;
//        adapter.notifyDataSetChanged();

        for (Assessments a : allAssessments) {
            assessmentsList.add(a);
        }
    }

//    public void addAssessment(View view) {
//        Intent i = new Intent(this, AddAssessments.class);
//        String message = String.valueOf(termId);
//        i.putExtra(EXTRA_MESSAGE, message);
//        startActivity(i);
//    }
}
