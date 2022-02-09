package com.example.wgujoshlongenecker.activities;

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

public class DetailedCourse extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "";
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
    String name = "";
    String termId = "";
    int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_course);
        appDB = AppDatabase.getInstance(getApplicationContext());
        courseName = findViewById(R.id.dcourseName);
        termStart =  findViewById(R.id.dcourseStart);
        termEnd = findViewById(R.id.dcourseEnd);
        saveTerm = findViewById(R.id.saveTerm);
        delete = findViewById(R.id.delete);
        assessmentViewButton = findViewById(R.id.assessmentViewButton);

        Intent intent = getIntent();
        String name = getIntent().getStringExtra("courseName");
        termId = getIntent().getStringExtra("termID");
        courseId = getIntent().getIntExtra("courseID", 0);
//        start = getIntent().getStringExtra("courseStart");
//        end = getIntent().getStringExtra("courseEnd");
//        status = getIntent().getStringExtra("courseStatus");
//        notes = getIntent().getStringExtra("courseNotes");
//        instructorInfo = getIntent().getStringExtra("instructorInfo");

        System.out.println(termId);
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

    public void deleteEditedCourse(View view) {
//        Course course = new Course();
//        course.setTermId(String.valueOf(termId));
//        course.setTitle(String.valueOf(courseName.getText()));
//        course.setStartDate("020202");
//        course.setEndDate("0202020");
//        course.setStatus("In Progress");
//        course.setNoteInfo("empty");
        appDB.courseDao().deleteCourse(String.valueOf(courseId));
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = termId;
        System.out.println(message);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }
//
    public void viewAssessments(View view) {
        Intent i = new Intent(this, ScheduledCourses.class);
        startActivity(i);
    }








}