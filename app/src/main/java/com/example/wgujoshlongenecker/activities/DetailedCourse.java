package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class DetailedCourse extends AppCompatActivity {

    private EditText courseName;
    private EditText termStart;
    private EditText termEnd;
    private Button saveTerm;
    private Button delete;
    private Button assessmentViewButton;
    AppDatabase appDB;
    List<String> allTerms;
    Term selectedTerm = null;
    int termIdnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_course);
        appDB = AppDatabase.getInstance(getApplicationContext());
        courseName = findViewById(R.id.courseName);
        termStart =  findViewById(R.id.termStart);
        termEnd = findViewById(R.id.termEnd);
        saveTerm = findViewById(R.id.saveTerm);
        delete = findViewById(R.id.delete);
        assessmentViewButton = findViewById(R.id.assessmentViewButton);

        Intent intent = getIntent();
        String username = intent.getStringExtra(ScheduledTerms.EXTRA_MESSAGE);
        String name = getIntent().getStringExtra("courseName");
//        String start = getIntent().getStringExtra("termStart");
//        String end = getIntent().getStringExtra("termEnd");
//        termIdnew = getIntent().getIntExtra("termID", 0);
        courseName.setText(name);
//        termStart.setText(start);
//        termEnd.setText(end);
//        System.out.println(termIdnew);
    }

//    public void saveEditedCourse(View view) {
//        Course course = new Course();
//        course.setTid(termIdnew);
//        course.setTermName(String.valueOf(termName.getText()));
//        course.setStartDate(String.valueOf(termStart.getText()));
//        course.setEndDate(String.valueOf(termEnd.getText()));
//        appDB.courseDao().update(course);
//        Intent i = new Intent(this, ScheduledTerms.class);
//        startActivity(i);
//    }

//    public void deleteEditedTerm(View view) {
//        Term term = new Term();
//        term.setTid(termIdnew);
//        term.setTermName(String.valueOf(termName.getText()));
//        term.setStartDate(String.valueOf(termStart.getText()));
//        term.setEndDate(String.valueOf(termEnd.getText()));
//        appDB.termDao().delete(term);
//        Intent i = new Intent(this, ScheduledTerms.class);
//        startActivity(i);
//    }
//
    public void viewAssessments(View view) {
        Intent i = new Intent(this, ScheduledAssessments.class);
        startActivity(i);
    }








}