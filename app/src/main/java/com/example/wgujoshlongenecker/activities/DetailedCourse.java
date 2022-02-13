package com.example.wgujoshlongenecker.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
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
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailedCourse extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
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
    public static int numAlert;
    long date;


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
        if (status.equals("Dropped")) {
            droppedRadio.setChecked(true);
        } else if (status.equals("In Progress")) {
            inProgRadio.setChecked(true);
        } else if (status.equals("Plan To Take")) {
            planRadio.setChecked(true);
        } else {
            completedRadio.setChecked(true);
        }
        courseName.setText(name);
        courseStart.setText(start);
        courseEnd.setText(end);
        instructorName.setText(cinstructorName);
        instructorPhone.setText(cinstructorPhone);
        instructorEmail.setText(cinstructorEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_real, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.moreVertical:
                Intent viewNotes = new Intent(DetailedCourse.this, ViewNotes.class);
                viewNotes.putExtra(EXTRA_MESSAGE, String.valueOf(name));
                startActivity(viewNotes);
                return true;
            case R.id.addStart:
                    Intent intent=new Intent(DetailedCourse.this,Receiver.class);
                    intent.putExtra("key","Course Reminder for: " + courseName + " at " + courseStart);
                    PendingIntent sender= PendingIntent.getBroadcast(DetailedCourse.this,++numAlert,intent,0);
                    AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                    String start = courseStart.getText().toString();
                    Date startDate = null;
                    try {
                        startDate = sdf.parse(start);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long trigger = startDate.getTime();
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;

            case R.id.addEnd:
                    Intent i=new Intent(DetailedCourse.this,Receiver.class);
                    i.putExtra("key","Course Reminder for: " + courseName + " at " + courseEnd);
                    PendingIntent sender2= PendingIntent.getBroadcast(DetailedCourse.this,++numAlert,i,0);
                    AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

                    String end = courseEnd.getText().toString();
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
        appDB.noteDao().deleteNote(String.valueOf(name));
        Intent i = new Intent(this, ScheduledCourses.class);
        String message = termId;
        System.out.println(message);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }
//
    public void viewAssessments(View view) {
        Intent i = new Intent(this, ScheduledAssessments.class);
        i.putExtra(EXTRA_MESSAGE, String.valueOf(courseId));
        System.out.println(String.valueOf(courseId));
        startActivity(i);

    }

//    private void updateLabel() {
//        String myFormat = "MM/dd/yyyy";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        String dateStart = String.valueOf(courseStart.getText());
//        courseStart.setText(sdf.format(dateStart));
//        //editEnd.setText(sdf.format(myCalendar.getTime()));
//    }








}