package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewTeachers extends AppCompatActivity {

    //Declaration of components
    private RecyclerView teachersRecView;
    private TeacherRecViewadapter adapter;
    TextView toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teachers);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Teachers");

        adapter = new TeacherRecViewadapter(this);
        teachersRecView = findViewById(R.id.teachersRecView);

        teachersRecView.setAdapter(adapter);
        teachersRecView.setLayoutManager(new LinearLayoutManager(this));

//        to add data in arraylist
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("17-899098", "Dela Cruz", "Juan", "Filipino", "1", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        teachers.add(new Teacher("17-123456", "Astillero", "Yes", "No", "2", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        teachers.add(new Teacher("17-234567", "Adan", "Right", "Maybe", "3", "https://i.ytimg.com/vi/Z4zkIi11Psw/hqdefault.jpg"));
        teachers.add(new Teacher("17-345678", "Barcela", "Left", "Top", "1", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));

        adapter.setTeachers(teachers);
    }
}