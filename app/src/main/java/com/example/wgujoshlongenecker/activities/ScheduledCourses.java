package com.example.wgujoshlongenecker.activities;

import android.os.Bundle;
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

    private Button courseUpdate;
    private Button courseAdd;
    private Button courseDelete;
    private RecyclerView courseView;
    AppDatabase appDB;
    AppRepo appRepo;
    CourseDAO courseDao;
    private ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_courses);
        appDB = AppDatabase.getInstance(getApplicationContext());
        courseAdd = (Button) findViewById(R.id.assessmentAdd);
        courseView = findViewById(R.id.courseView);
        courseList = new ArrayList<>();
        appRepo = new AppRepo(getApplication());
        appRepo.getAllTerms();
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
        List<Course> allCourses = appDB.courseDao().getCourses();
        for (Course c : allCourses) {
            courseList.add(c);
        }
    }
}
