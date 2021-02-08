package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Testinganbutton extends AppCompatActivity {

    Button patungko,pagumaoc,viewCollege, studRegister, schoolMainScreen, viewPrograms, viewSections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinganbutton);



        patungko = findViewById(R.id.patungko);
        pagumaoc = findViewById(R.id.pagumaoc);
        viewCollege = findViewById(R.id.viewCollege);
        studRegister = findViewById(R.id.studRegister);
        schoolMainScreen = findViewById(R.id.schoolMainScreen);
        viewPrograms = findViewById(R.id.viewPrograms);
        viewSections = findViewById(R.id.viewSections);

        patungko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patungko = new Intent(Testinganbutton.this , GradesAll2.class);
                startActivity(patungko);
            }
        });


        pagumaoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagumaoc = new Intent(Testinganbutton.this , RegisterTeacher2.class);
                startActivity(pagumaoc);
            }
        });

        viewCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCollege = new Intent(Testinganbutton.this , ViewCollege.class);
                startActivity(viewCollege);
            }
        });

        studRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studRegister = new Intent(Testinganbutton.this , StudentRegister.class);
                startActivity(studRegister);
            }
        });

        schoolMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schoolMainScreen = new Intent(Testinganbutton.this , SchoolMainScreen.class);
                startActivity(schoolMainScreen);
            }
        });

        viewPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPrograms = new Intent(Testinganbutton.this , ViewPrograms.class);
                startActivity(viewPrograms);
            }
        });

        viewSections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSections = new Intent(Testinganbutton.this , ViewSections.class);
                startActivity(viewSections);
            }
        });
    }
}