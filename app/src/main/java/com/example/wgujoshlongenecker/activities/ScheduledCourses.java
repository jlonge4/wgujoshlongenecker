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
import com.example.wgujoshlongenecker.dao.CourseDAO;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.database.AppRepo;
import com.example.wgujoshlongenecker.entities.Course;

import java.util.ArrayList;
import java.util.List;


public class ScheduledCourses extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    private Button courseAdd;
    private RecyclerView courseView;
    AppDatabase appDB;
    AppRepo appRepo;
    CourseDAO courseDao;
    private ArrayList<Course> courseList;
    String termId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_courses);
        Intent intent = getIntent();
        termId = intent.getStringExtra(ScheduledTerms.EXTRA_MESSAGE);
        System.out.println(termId);
        appDB = AppDatabase.getInstance(getApplicationContext());
        courseAdd = (Button) findViewById(R.id.assessmentAdd);
        courseView = findViewById(R.id.courseView);
        courseList = new ArrayList<>();
        appRepo = new AppRepo(getApplication());
//        appRepo.getAllCourses();
//        appDB.courseDao().getCourses("1");
        updateLists();
        setAdapter();
    }

    private void setAdapter() {
        CourseAdapter adapter = new CourseAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        courseView.setLayoutManager(layoutManager);
        courseView.setItemAnimator(new DefaultItemAnimator());
        courseView.setAdapter(adapter);
        adapter.setCourses(courseList);
    }

    private void updateLists() {
        List<Course> allCourses = appDB.courseDao().getCourses(termId);
        for (Course c : allCourses) {
            courseList.add(c);
        }
    }

    public void addCourse(View view) {
        Intent i = new Intent(this, AddCourses.class);
        String message = String.valueOf(termId);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }
}
