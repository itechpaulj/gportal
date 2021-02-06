package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class addsubject extends AppCompatActivity {
    Spinner spinner4,spinner5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);

        spinner4 =(Spinner)findViewById(R.id.spinner4);
        spinner5 =(Spinner)findViewById(R.id.spinner5);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.programs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterAy = ArrayAdapter.createFromResource(this, R.array.Ay, android.R.layout.simple_spinner_item);
        adapterAy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapterAy);

    }
}