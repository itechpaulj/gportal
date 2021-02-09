package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class newcode extends AppCompatActivity {
    Spinner spinnerAy,spinnerCollege,spinnerProgram,spinnerSection,spinnerSubjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcode);

        spinnerAy = findViewById(R.id.spinnerAy);
        spinnerCollege = findViewById(R.id.spinnerCollege);
        spinnerProgram = findViewById(R.id.spinnerProgram);
        spinnerSection = findViewById(R.id.spinnerSection);
        spinnerSubjects = findViewById(R.id.spinnerSubjects);

        String[]  ay = {"Academic Year","Academic Year","Academic Year"};
        ArrayList<String> Ay = new ArrayList<>(Arrays.asList(ay));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,Ay);
        spinnerAy.setAdapter(arrayAdapter);

        String[]  co = {"College","College","College"};
        ArrayList<String> Co = new ArrayList<>(Arrays.asList(co));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.style_spinner,Co);
        spinnerCollege.setAdapter(arrayAdapter2);

        String[]  pro = {"Program","Program","Program"};
        ArrayList<String> Pro = new ArrayList<>(Arrays.asList(pro));
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,R.layout.style_spinner,Pro);
        spinnerProgram.setAdapter(arrayAdapter3);

        String[]  sec = {"Section","Section","Section"};
        ArrayList<String> Sec = new ArrayList<>(Arrays.asList(sec));
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this,R.layout.style_spinner,Sec);
        spinnerSection.setAdapter(arrayAdapter4);

        String[]  sub = {"Subject","Subject","Subject"};
        ArrayList<String> Sub = new ArrayList<>(Arrays.asList(sub));
        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<>(this,R.layout.style_spinner,Sub);
        spinnerSubjects.setAdapter(arrayAdapter5);
    }
}