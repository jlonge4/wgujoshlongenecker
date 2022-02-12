package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

public class AddAssessments extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "" ;
    private AppDatabase appDB;
    private EditText dAssessName;
    private EditText dAssessStart;
    private EditText dAssessEnd;
    String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assessment);
        appDB = AppDatabase.getInstance(getApplicationContext());
        dAssessName = findViewById(R.id.dassessName);
        dAssessStart = findViewById(R.id.dassessStart);
        dAssessEnd = findViewById(R.id.dassessEnd);
        Intent intent = getIntent();
        courseId = intent.getStringExtra(ScheduledAssessments.EXTRA_MESSAGE);
//        intent
//        saveTerm = findViewById(R.id.saveTerm);
//        courseView = findViewById(R.id.courseView);
    }

    public void saveAssessment(View view) {
        Assessments assessments = new Assessments();
        assessments.setTitle(String.valueOf(dAssessName.getText()));
        assessments.setStartDate(String.valueOf(dAssessStart.getText()));
        assessments.setEndDate(String.valueOf(dAssessEnd.getText()));
        assessments.setCourseId(courseId);
        appDB.assessmentDao().insertAssessment(assessments);
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, courseId);
        startActivity(i);
    }

}