package com.example.wgujoshlongenecker.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailedAssessment extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    private EditText aName;
    private EditText aStart;
    private EditText aEnd;
    private RadioButton paRadio;
    private RadioButton oaRadio;
    private Button aSave;
    private Button delete;
    AppDatabase appDB;
    String name;
    String start;
    String end;
    int aID;
    String cID;
    String type;
    public static int numAlert;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_assessment);
        appDB = AppDatabase.getInstance(getApplicationContext());
        aName = findViewById(R.id.aName);
        aStart =  findViewById(R.id.aStart);
        aEnd = findViewById(R.id.aEnd);
        aSave = findViewById(R.id.aSave);
        delete = findViewById(R.id.delete);
        paRadio = findViewById(R.id.paRadio);
        oaRadio = findViewById(R.id.oaRadio);

        Intent intent = getIntent();
        name = intent.getStringExtra("assessmentName");
        start = intent.getStringExtra("assessmentStart");
        end = intent.getStringExtra("assessmentEnd");
        aID = intent.getIntExtra("aID", 0);
        cID = intent.getStringExtra("cID");
        type = intent.getStringExtra("type");
        aName.setText(name);
        aStart.setText(start);
        aEnd.setText(end);
        System.out.println(type);
        if (type.equals("pa")) {
            paRadio.setChecked(true);
        } else {
            oaRadio.setChecked(true);
        }
    }

    public void saveEditedAssessment(View view) {
        Assessments assessments = new Assessments();
        assessments.setAid(aID);
        assessments.setCourseId(cID);
        assessments.setTitle(String.valueOf(aName.getText()));
        assessments.setStartDate(String.valueOf(aStart.getText()));
        assessments.setEndDate(String.valueOf(aEnd.getText()));
        if (paRadio.isChecked()) {
            assessments.setType("pa");
        } else {
            assessments.setType("oa");
        }
        appDB.assessmentDao().update(assessments);
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, cID);
        startActivity(i);
    }

    public void deleteEditedAssessment(View view) {
        appDB.assessmentDao().deleteById(String.valueOf(aID));
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, cID);
        startActivity(i);
    }

    public void goHome(View view) {
        Intent i = new Intent(this, ScheduledTerms.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.assessment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addaStart:
                Intent intent=new Intent(DetailedAssessment.this,Receiver.class);
                intent.putExtra("key","Course Reminder for: " + aName + " at " + aStart);
                PendingIntent sender= PendingIntent.getBroadcast(DetailedAssessment.this,++numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                String start = aStart.getText().toString();
                Date startDate = null;
                try {
                    startDate = sdf.parse(start);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long trigger = startDate.getTime();
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.addaEnd:
                Intent i=new Intent(DetailedAssessment.this,Receiver.class);
                i.putExtra("key","Course Reminder for: " + aName + " at " + aEnd);
                PendingIntent sender2= PendingIntent.getBroadcast(DetailedAssessment.this,++numAlert,i,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

                String end = aEnd.getText().toString();
                Date endDate = null;
                try {
                    endDate = sdf2.parse(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long trigger2 = endDate.getTime();
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }








}