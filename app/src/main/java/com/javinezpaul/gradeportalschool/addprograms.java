package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class addprograms extends AppCompatActivity {
    Spinner spinnerAYProg,spinnerColProg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprograms);

        spinnerAYProg =(Spinner)findViewById(R.id.spinnerAYProg);
        spinnerColProg=(Spinner)findViewById(R.id.spinnerColProg);

        String[] ay = {"Academic Year","Academic Year","Academic Year"};
        ArrayList<String> Ay = new ArrayList<>(Arrays.asList(ay));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,Ay);
        spinnerAYProg.setAdapter(arrayAdapter);

        String[] co = {"Academic Year","Academic Year","Academic Year"};
        ArrayList<String> Co = new ArrayList<>(Arrays.asList(co));
        ArrayAdapter<String> arrayAdapters = new ArrayAdapter<>(this,R.layout.style_spinner,Co);
        spinnerColProg.setAdapter(arrayAdapters);


    }

}