package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class addprograms extends AppCompatActivity {
    Spinner Spinner1,Spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprograms);

        Spinner1 =(Spinner)findViewById(R.id.Spinner1);
        Spinner2 =(Spinner)findViewById(R.id.Spinner2);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.college, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterAy = ArrayAdapter.createFromResource(this, R.array.Ay, android.R.layout.simple_spinner_item);
        adapterAy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapterAy);
    }

}