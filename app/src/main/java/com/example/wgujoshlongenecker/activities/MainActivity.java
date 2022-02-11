package com.example.wgujoshlongenecker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView label;
    private ImageView imageView;
    AppDatabase appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDB = AppDatabase.getInstance(getApplicationContext());
        button = (Button) findViewById(R.id.button);
        label = (TextView) findViewById(R.id.label);
        imageView = findViewById(R.id.imageView);
        Assessments assessment = new Assessments();
        assessment.setCourseId("2");
        assessment.setTitle("First Test");
        assessment.setStartDate("02-21-22");
        assessment.setEndDate("03-31-22");
        appDB.assessmentDao().insertAssessment(assessment);
        appDB.assessmentDao().delete(assessment);
    }

    public void toScheduledTerms(View view) {
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }
}