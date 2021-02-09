package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class addsubject extends AppCompatActivity {
    Spinner spinnerProgram,spinnerAcademicYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);

        spinnerProgram =findViewById(R.id.spinnerProgram);
        spinnerAcademicYear =findViewById(R.id.spinnerAcademicYear);


        String[] program = {"Program","Program","Program"};
        ArrayList<String> Program = new ArrayList<>(Arrays.asList(program));
        ArrayAdapter<String> arrayAdapterpro = new ArrayAdapter<>(this,R.layout.style_spinner,Program);
        spinnerProgram.setAdapter(arrayAdapterpro);

        String[] academic = {"Academic Year","Academic Year","Academic Year"};
        ArrayList<String> Academic = new ArrayList<>(Arrays.asList(academic));
        ArrayAdapter<String> arrayAdapteryear = new ArrayAdapter<>(this,R.layout.style_spinner,Academic);
        spinnerAcademicYear.setAdapter(arrayAdapteryear);

    }
}