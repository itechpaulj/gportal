package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewSubjects extends AppCompatActivity {
    private RecyclerView subjectsRecView;
    private SubjectRecViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subjects);

        subjectsRecView = findViewById(R.id.subjectsRecView);
        adapter = new SubjectRecViewAdapter(this);

        subjectsRecView.setAdapter(adapter);
        subjectsRecView.setLayoutManager(new LinearLayoutManager(this));

        //to add data in arraylist
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("1", "IT123", "Web Development", "1", "1"));
        subjects.add(new Subject("2", "SOCSCI123", "Agrarian and Tax Reform 2000", "1", "1"));
        subjects.add(new Subject("3", "COMM", "Communication Skills", "2", "1"));
        subjects.add(new Subject("4", "SOCSCI224", "General Psychology", "3", "1"));

        adapter.setSubjects(subjects);
    }
}