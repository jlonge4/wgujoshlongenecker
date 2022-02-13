package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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
    private RadioButton paRadio;
    private RadioButton oaRadio;
    String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assessment);
        appDB = AppDatabase.getInstance(getApplicationContext());
        dAssessName = findViewById(R.id.dassessName);
        dAssessStart = findViewById(R.id.dassessStart);
        dAssessEnd = findViewById(R.id.dassessEnd);
        paRadio = findViewById(R.id.paRadio);
        oaRadio = findViewById(R.id.oaRadio);
        Intent intent = getIntent();
        courseId = intent.getStringExtra(ScheduledAssessments.EXTRA_MESSAGE);
        dAssessStart.setText("MM/dd/YYYY");
        dAssessEnd.setText("MM/dd/YYYY");
    }

    public void saveAssessment(View view) {
        Assessments assessments = new Assessments();
        assessments.setTitle(String.valueOf(dAssessName.getText()));
        assessments.setStartDate(String.valueOf(dAssessStart.getText()));
        assessments.setEndDate(String.valueOf(dAssessEnd.getText()));
        assessments.setCourseId(courseId);
        if (paRadio.isChecked()) {
            assessments.setType("pa");
        } else {
            assessments.setType("oa");
        }
        appDB.assessmentDao().insertAssessment(assessments);
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, courseId);
        startActivity(i);
    }

}