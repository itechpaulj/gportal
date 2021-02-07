package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ViewCollege extends AppCompatActivity {

    //Declaration of components
    private RecyclerView collegesRecView;
    private CollegeRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_college);

        adapter = new CollegeRecViewAdapter(this);
        collegesRecView = findViewById(R.id.collegesRecView);

        collegesRecView.setAdapter(adapter);
        collegesRecView.setLayoutManager(new LinearLayoutManager(this));

        //to add data in arraylist
        ArrayList<College> colleges = new ArrayList<>();
        colleges.add(new College("1","IIT","Industrial and Information Technology"));
        colleges.add(new College("2", "BA", "Business Administration"));
        colleges.add(new College("3","GATE", "General Academic Tertiary Education"));
        colleges.add(new College("4", "COE", "College of Engineering"));

        adapter.setColleges(colleges);


        initViews();
    }

    private void initViews() {

    }
}