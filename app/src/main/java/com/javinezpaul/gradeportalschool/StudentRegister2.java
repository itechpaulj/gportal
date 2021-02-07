package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentRegister2 extends AppCompatActivity {
    Spinner spinner1,spinner2,spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register2);


        spinner1 = findViewById(R.id.spinnersample);
        spinner2 = findViewById(R.id.spinnersample2);
        spinner3 = findViewById(R.id.spinnersample3);

        String[]  value = {"College","College","College"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList);
        spinner1.setAdapter(arrayAdapter);

        String[]  value2 = {"Programs","Programs","Programs"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(value2));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList2);
        spinner2.setAdapter(arrayAdapter2);

        String[]  value3 = {"Section","Section","Section"};
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList(value3));
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList3);
        spinner3.setAdapter(arrayAdapter3);
    }
}