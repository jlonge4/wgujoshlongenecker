package com.example.wgujoshlongenecker.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;


public class ScheduledAssessments extends AppCompatActivity {

    private Button assessmentsUpdate;
    private Button assessmentsAdd;
    private Button assessmentsDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_assessments);

        assessmentsAdd = (Button) findViewById(R.id.assessmentsAdd);
        assessmentsUpdate = (Button) findViewById(R.id.assessmentsUpdate);
        assessmentsDelete = (Button) findViewById(R.id.assessmentsDelete);

    }
}
