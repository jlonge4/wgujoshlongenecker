package com.example.wgujoshlongenecker.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class DetailedTerm extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "";
    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private Button saveTerm;
    private Button delete;
    private Button courseView;
    AppDatabase appDB;
    List<String> allTerms;
    List<Course> allCourses;
    Term selectedTerm = null;
    int termIdnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_term);
        appDB = AppDatabase.getInstance(getApplicationContext());
        termName = findViewById(R.id.dcourseName);
        termStart =  findViewById(R.id.dcourseStart);
        termEnd = findViewById(R.id.dcourseEnd);
        saveTerm = findViewById(R.id.saveTerm);
        delete = findViewById(R.id.delete);
        courseView = findViewById(R.id.courseView);

        Intent intent = getIntent();
        String username = intent.getStringExtra(ScheduledTerms.EXTRA_MESSAGE);
        String name = getIntent().getStringExtra("termName");
        String start = getIntent().getStringExtra("termStart");
        String end = getIntent().getStringExtra("termEnd");
        termIdnew = getIntent().getIntExtra("termID", 0);
        termName.setText(name);
        termStart.setText(start);
        termEnd.setText(end);
        System.out.println(termIdnew);
    }

    public void saveEditedTerm(View view) {
        Term term = new Term();
        term.setTid(termIdnew);
        term.setTermName(String.valueOf(termName.getText()));
        term.setStartDate(String.valueOf(termStart.getText()));
        term.setEndDate(String.valueOf(termEnd.getText()));
        appDB.termDao().update(term);
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }

    public void deleteEditedTerm(View view) {
        allCourses = appDB.courseDao().getCourses(String.valueOf(termIdnew));
        Term term = new Term();
        term.setTid(termIdnew);
        term.setTermName(String.valueOf(termName.getText()));
        term.setStartDate(String.valueOf(termStart.getText()));
        term.setEndDate(String.valueOf(termEnd.getText()));
        if (allCourses.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailedTerm.this);
            builder.setMessage("Please Remove Courses Before Removing This Term");
            builder.setTitle("Alert !");
            builder.setCancelable(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            appDB.termDao().delete(term);
            Intent i = new Intent(this, ScheduledTerms.class);
            startActivity(i);
        }
    }

    public void viewCourses(View view) {
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = String.valueOf(termIdnew);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }









}