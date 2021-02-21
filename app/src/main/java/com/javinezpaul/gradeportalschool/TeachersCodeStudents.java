package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeachersCodeStudents extends AppCompatActivity {

    //TODO: pass teacherscode here
    //Passed teacherscode
    private String teacherscode="";

    private RecyclerView teachersCodeStudentsRecView;
    private StudGradesRecViewAdapter adapter;

    TextView toolbarName;

    private ArrayList<StudentsGrade> studentsGrades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_code_students);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Subjectname Students");

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            String a =(String) b.get("toolbarName");
            String c =(String) b.get("teacherscode");
            toolbarName.setText(a);
            teacherscode=c;
        }


//        Toast.makeText(this, teacherscode, Toast.LENGTH_LONG).show();

        teachersCodeStudentsRecView=findViewById(R.id.teachersCodeStudentsRecView);
        adapter = new StudGradesRecViewAdapter(this);
        teachersCodeStudentsRecView.setAdapter(adapter);
        teachersCodeStudentsRecView.setLayoutManager(new LinearLayoutManager(TeachersCodeStudents.this));

        //add data to arraylist
        studentsGrades.add(new StudentsGrade("1","0.00", "2020-02-21", "No note found",
                "TE1234", "Adan, Allan A.", "11", "17500101", "1", "Physical Science"
                ));
        studentsGrades.add(new StudentsGrade("1","0.00", "2020-02-21", "No note found",
                "TE1234", "Adan, Allan A.", "11", "17500101", "1", "Physical Science"
                ));
        adapter.setStudentsGrades(studentsGrades);

    }
}