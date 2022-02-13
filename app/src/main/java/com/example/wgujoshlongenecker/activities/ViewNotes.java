package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.entities.CourseNotes;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class ViewNotes extends AppCompatActivity {

    private EditText noteAdd;
    private String courseId;
    private ListView noteView;
    AppDatabase appDB;
    List<String> allNotes;
    String courseNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notes);
        Intent intent = getIntent();
        courseId = intent.getStringExtra(DetailedCourse.EXTRA_MESSAGE);
        appDB = AppDatabase.getInstance(getApplicationContext());
        noteView = findViewById(R.id.noteView);
        noteAdd = findViewById(R.id.noteAdd);
        updateLists();
    }

    private void updateLists() {
        List<String> allNotes = appDB.noteDao().getCourseNotes(courseId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allNotes);
        noteView.setAdapter(adapter);
        this.allNotes = allNotes;
        adapter.notifyDataSetChanged();
    }

    public void addNotes(View view) {
        CourseNotes newNote = new CourseNotes();
        newNote.setNote(String.valueOf(noteAdd.getText()));
        newNote.setCid(courseId);
        appDB.noteDao().insert(newNote);
        updateLists();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.moreOptions:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNotes);
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Course Notes");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}