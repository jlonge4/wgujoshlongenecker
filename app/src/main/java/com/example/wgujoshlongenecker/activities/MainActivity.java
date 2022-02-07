package com.example.wgujoshlongenecker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView label;
    AppDatabase appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        label = (TextView) findViewById(R.id.label);

    }

    public void toScheduledTerms(View view) {
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }
}