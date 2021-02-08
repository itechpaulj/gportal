package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewPrograms extends AppCompatActivity {

    private RecyclerView programsRecView;
    private ProgramRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_programs);

        programsRecView = findViewById(R.id.programsRecView);
        adapter = new ProgramRecViewAdapter(this);

        programsRecView.setAdapter(adapter);
        programsRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Program> programs = new ArrayList<>();
        programs.add(new Program(1, "BSED", "BS in Secondary Education", "Major in English", "1", "2020", "2021"));
        programs.add(new Program(1, "BSIT", "BS in Information Technology", "", "2", "2020", "2021"));
        programs.add(new Program(1, "BSBA", "BS in Business Administration", "Major in E-commerce", "3", "2020", "2021"));
        programs.add(new Program(1, "HM", "Hotel Management", "Major in Tourism", "4", "2020", "2021"));

        adapter.setPrograms(programs);
    }
}