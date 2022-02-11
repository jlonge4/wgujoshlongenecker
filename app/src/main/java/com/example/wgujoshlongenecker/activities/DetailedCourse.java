package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class DetailedCourse extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "";
    private EditText courseName;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText instructorPhone;
    private EditText instructorEmail;
    private EditText instructorName;
    private Button saveCourse;
    private Button assessmentViewButton;
    private Button deleteCourse;
    private RadioButton completedRadio;
    private RadioButton droppedRadio;
    private RadioButton inProgRadio;
    private RadioButton planRadio;
    AppDatabase appDB;
    List<String> allTerms;
    Term selectedTerm = null;

    String name = "";
    String termId = "";
    int courseId;
    String start;
    String end;
    String status = "";
    String notes;
    String cinstructorName;
    String cinstructorPhone;
    String cinstructorEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_course);
        appDB = AppDatabase.getInstance(getApplicationContext());
        courseName = findViewById(R.id.courseName);
        courseStart = findViewById(R.id.courseStart);
        courseEnd = findViewById(R.id.courseEnd);
        saveCourse = findViewById(R.id.saveCourse);
        completedRadio = findViewById(R.id.completedRadio);
        droppedRadio = findViewById(R.id.droppedRadio);
        planRadio = findViewById(R.id.planRadio);
        inProgRadio = findViewById(R.id.inProgRadio);
        instructorEmail = findViewById(R.id.instructorEmail);
        instructorPhone = findViewById(R.id.instructorPhone);
        instructorName = findViewById(R.id.instructorName);
        deleteCourse = findViewById(R.id.deleteCourse);
        assessmentViewButton = findViewById(R.id.assessmentViewButton);

        Intent intent = getIntent();
        name = getIntent().getStringExtra("courseName");
        termId = getIntent().getStringExtra("termID");
        courseId = getIntent().getIntExtra("courseID", 0);
        start = getIntent().getStringExtra("courseStart");
        end = getIntent().getStringExtra("courseEnd");
        status = intent.getStringExtra("courseStatus");
        notes = getIntent().getStringExtra("courseNotes");
        cinstructorName = getIntent().getStringExtra("instructorName");
        cinstructorPhone = getIntent().getStringExtra("instructorPhone");
        cinstructorEmail= getIntent().getStringExtra("instructorEmail");
        System.out.println(status);
        if (status.equals("Dropped")) {
            droppedRadio.setChecked(true);
        } else if (status.equals("In Progress")) {
            inProgRadio.setChecked(true);
        } else if (status.equals("Plan To Take")) {
            planRadio.setChecked(true);
        } else {
            completedRadio.setChecked(true);
        }
//        switch (status) {
//            case "Dropped":
//                droppedRadio.setSelected(true);
//                break;
//            case "In Progress":
//                inProgRadio.setSelected(true);
//                break;
//            case "Plan To Take":
//                planRadio.setSelected(true);
//                break;
//            default:
//                completedRadio.setSelected(true);
//                break;
//        }
        courseName.setText(name);
        courseStart.setText(start);
        courseEnd.setText(end);
        instructorName.setText(cinstructorName);
        instructorPhone.setText(cinstructorPhone);
        instructorEmail.setText(cinstructorEmail);
    }

    public void saveEditedCourse(View view) {
        Course course = new Course();
        course.setCid(courseId);
        course.setTermId(String.valueOf(termId));
        course.setTitle(String.valueOf(courseName.getText()));
        course.setStartDate(String.valueOf(courseStart.getText()));
        course.setEndDate(String.valueOf(courseEnd.getText()));
        if (droppedRadio.isChecked()) {
            course.setStatus("Dropped");
        } else if (inProgRadio.isChecked()) {
            course.setStatus("In Progress");
        } else if (planRadio.isChecked()) {
            course.setStatus("Plan To Take");
        } else {
            course.setStatus("Completed");
        }
        course.setInstructorName(String.valueOf(instructorName.getText()));
        course.setInstructorPhone(String.valueOf(instructorPhone.getText()));
        course.setInstructorEmail(String.valueOf(instructorEmail.getText()));
        appDB.courseDao().update(course);
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = termId;
        System.out.println(message);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }

    public void deleteEditedCourse(View view) {
        appDB.courseDao().deleteCourse(String.valueOf(courseId));
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = termId;
        System.out.println(message);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }
//
    public void viewAssessments(View view) {
        Intent i = new Intent(this, ScheduledAssessments.class);
        startActivity(i);
    }








}