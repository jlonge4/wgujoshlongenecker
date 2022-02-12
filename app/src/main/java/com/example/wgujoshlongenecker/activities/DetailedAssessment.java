package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class DetailedAssessment extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    private EditText aName;
    private EditText aStart;
    private EditText aEnd;
    private Button aSave;
    private Button delete;
    AppDatabase appDB;
    String name;
    String start;
    String end;
    int aID;
    String cID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_assessment);
        appDB = AppDatabase.getInstance(getApplicationContext());
        aName = findViewById(R.id.aName);
        aStart =  findViewById(R.id.aStart);
        aEnd = findViewById(R.id.aEnd);
        aSave = findViewById(R.id.aSave);
        delete = findViewById(R.id.delete);

        Intent intent = getIntent();
        name = intent.getStringExtra("assessmentName");
        start = intent.getStringExtra("assessmentStart");
        end = intent.getStringExtra("assessmentEnd");
        aID = intent.getIntExtra("aID", 0);
        cID = intent.getStringExtra("cID");
        aName.setText(name);
        aStart.setText(start);
        aEnd.setText(end);
    }

    public void saveEditedAssessment(View view) {
        Assessments assessments = new Assessments();
        assessments.setAid(aID);
        assessments.setCourseId(cID);
        assessments.setTitle(String.valueOf(aName.getText()));
        assessments.setStartDate(String.valueOf(aStart.getText()));
        assessments.setEndDate(String.valueOf(aEnd.getText()));
        appDB.assessmentDao().update(assessments);
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, cID);
        startActivity(i);
    }

    public void deleteEditedAssessment(View view) {
        appDB.assessmentDao().deleteById(String.valueOf(aID));
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, cID);
        startActivity(i);
    }

    public void goHome(View view) {
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }








}