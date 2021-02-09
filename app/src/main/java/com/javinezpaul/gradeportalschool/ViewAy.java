package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewAy extends AppCompatActivity {

    private RecyclerView ayRecView;
    private AyRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ay);

        ayRecView = findViewById(R.id.ayRecView);
        adapter = new AyRecViewAdapter(this);

        ayRecView.setAdapter(adapter);
        ayRecView.setLayoutManager(new LinearLayoutManager(this));

        //to add data in arraylist
        ArrayList<Ay> ays = new ArrayList<>();
        ays.add(new Ay("1", "1st SEM", "2020", "2021", "1st Year"));
        ays.add(new Ay("2", "2nd SEM", "2020", "2021", "1st Year"));
        ays.add(new Ay("3", "1st SEM", "2021", "2022", "2nd Year"));
        ays.add(new Ay("4", "2nd SEM", "2021", "2022", "2nd Year"));

        adapter.setAys(ays);
    }
}