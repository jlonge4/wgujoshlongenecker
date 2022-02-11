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

import java.util.List;

public class AddCourses extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "";
    private EditText courseName;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText instructorPhone;
    private EditText instructorEmail;
    private EditText instructorName;
    private Button saveCourse;
    private Button courseView;
    private RadioButton completedRadio;
    private RadioButton droppedRadio;
    private RadioButton inProgRadio;
    private RadioButton planRadio;
    List<String> notes;
    AppDatabase appDB;
    String termId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        Intent intent = getIntent();
        termId = intent.getStringExtra(ScheduledTerms.EXTRA_MESSAGE);
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
    }

    public void saveCourse(View view) {
        Course course = new Course();
        course.setTermId(termId);
        course.setTitle(String.valueOf(courseName.getText()));
        course.setStartDate(String.valueOf(courseStart.getText()));
        course.setEndDate(String.valueOf(courseEnd.getText()));
        course.setInstructorName(String.valueOf(instructorName.getText()));
        course.setInstructorPhone(String.valueOf(instructorPhone.getText()));
        course.setInstructorEmail(String.valueOf(instructorEmail.getText()));
        if (inProgRadio.isSelected()) {
            course.setStatus("In Progress");
        } else if  (droppedRadio.isSelected()) {
            course.setStatus("Dropped");
        } else if  (completedRadio.isSelected()) {
            course.setStatus("Completed");
        } else {
            course.setStatus("Plan To Take");
        }
        appDB.courseDao().insertCourse(course);
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = termId;
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }


}
