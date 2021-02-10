package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewStudents extends AppCompatActivity {

    //Declaration of components
    private RecyclerView studentsRecView;
    private StudentRecViewAdapter adapter;
    TextView toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Students");

        adapter = new StudentRecViewAdapter(this);
        studentsRecView = findViewById(R.id.studentsRecView);

        studentsRecView.setAdapter(adapter);
        studentsRecView.setLayoutManager(new LinearLayoutManager(this));

//        to add data in arraylist
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "1(collegecode)", "1(programcode)", "1(sectioncode)", "123456(viewcode)", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));
        students.add(new Student("17-500101","Adan", "Allan", "Astillero", "IIT", "BSIT", "IT1A", "123456", "https://static.s123-cdn-static.com/uploads/24865/400_5c9b11b3ab476.jpg"));

        adapter.setStudents(students);
    }
}