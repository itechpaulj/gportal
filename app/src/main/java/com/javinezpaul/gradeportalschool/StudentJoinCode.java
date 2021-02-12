package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

public class StudentJoinCode extends AppCompatActivity {

    EditText studcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join_code);

        //To all Caps edit text code.
        studcode = findViewById(R.id.studcode);
        studcode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }
}