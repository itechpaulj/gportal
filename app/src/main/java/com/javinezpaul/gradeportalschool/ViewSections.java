package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewSections extends AppCompatActivity {

    private RecyclerView sectionsRecView;
    private SectionRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sections);

        sectionsRecView = findViewById(R.id.sectionsRecView);
        adapter = new SectionRecViewAdapter(this);

        sectionsRecView.setAdapter(adapter);
        sectionsRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section("1", "IT4A", "1"));
        sections.add(new Section("2", "BA1A", "2"));
        sections.add(new Section("3", "BA2A", "2"));
        sections.add(new Section("4", "BSED4A", "3"));

        adapter.setSections(sections);
    }
}