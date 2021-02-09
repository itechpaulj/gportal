package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterTeacher extends AppCompatActivity {
    EditText schoolcode;
    Spinner spinnercollegename;
    ArrayList<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        spinnercollegename = (Spinner) findViewById(R.id.spinnercollegename);
        schoolcode = (EditText) findViewById(R.id.schoolcode);

        spinnerList.add("CLick Me");
        adapter1 = new ArrayAdapter<>(RegisterTeacher.this, android.R.layout.simple_spinner_item,spinnerList);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnercollegename.setAdapter(adapter1);

        spinnercollegename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerList.add("CLick Me22");
                adapter1 = new ArrayAdapter<>(RegisterTeacher.this, android.R.layout.simple_spinner_item,spinnerList);
                adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnercollegename.setAdapter(adapter1);
                Object item = parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),item.toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}