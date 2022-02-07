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

public class AddTerm extends AppCompatActivity {

    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private Button saveTerm;
    private Button courseView;
    AppDatabase appDB;
    List<String> allTerms;
    Term selectedTerm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_term);
        appDB = AppDatabase.getInstance(getApplicationContext());
        termName = findViewById(R.id.termName);
        termStart = findViewById(R.id.termStart);
        termEnd = findViewById(R.id.termEnd);
        saveTerm = findViewById(R.id.saveTerm);
        courseView = findViewById(R.id.courseView);
    }

    public void saveTerm(View view) {
        Term term = new Term();
        term.setTermName(String.valueOf(termName.getText()));
        term.setStartDate(String.valueOf(termStart.getText()));
        term.setEndDate(String.valueOf(termEnd.getText()));
        appDB.termDao().insertTerm(term);
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }


}
