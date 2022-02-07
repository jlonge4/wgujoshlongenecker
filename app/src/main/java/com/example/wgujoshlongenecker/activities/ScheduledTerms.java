package com.example.wgujoshlongenecker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.database.AppDatabase;
import com.example.wgujoshlongenecker.database.AppRepo;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.ArrayList;
import java.util.List;


public class ScheduledTerms extends AppCompatActivity {

    private AppRepo appRepo;
    public static final String EXTRA_MESSAGE = "";
    private Button termUpdate;
    private Button termAdd;
    private Button termDelete;
    private ListView scheduledView;
    AppDatabase appDB;
    List<Term> allTerms;
    Term selectedTerm = null;
    private RecyclerView termsRView;
    private ArrayList<Term> termsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_terms);

        termAdd = (Button) findViewById(R.id.termAdd);
        termUpdate = (Button) findViewById(R.id.termUpdate);
        appDB = AppDatabase.getInstance(getApplicationContext());
        termsRView = findViewById(R.id.termsRView);
        termsList = new ArrayList<>();
        appRepo = new AppRepo(getApplication());
        appRepo.getAllTerms();

        updateLists();
        setAdapter();
    }

    private void setAdapter() {
        TermAdapter adapter = new TermAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        termsRView.setLayoutManager(layoutManager);
        termsRView.setItemAnimator(new DefaultItemAnimator());
        termsRView.setAdapter(adapter);
        adapter.setTerms(termsList);
    }

    private void updateLists() {
        List<Term> allTerms = appDB.termDao().getTerms();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,allTerms);
//        scheduledView.setAdapter(adapter);
//        scheduledView.setOnItemClickListener(listClick);
//        this.allTerms = allTerms;
//        adapter.notifyDataSetChanged();

        for (Term t : allTerms) {
            termsList.add(t);
        }
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Term term = (Term) scheduledView.getSelectedItem();
//            selectedTerm = (String) scheduledView.getItemAtPosition(i);
            selectedTerm = term;
            System.out.println(selectedTerm);
        }
    };

    public void updateTerm(View view) {
        Intent i = new Intent(this, DetailedTerm.class);
        String message = String.valueOf(selectedTerm.getTid());
        System.out.println(message);
        i.putExtra(EXTRA_MESSAGE, message);
        startActivity(i);
    }

    public void addTerm(View view) {
        Intent i = new Intent(this, AddTerm.class);
        startActivity(i);
    }
}
