package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentJoinCode extends AppCompatActivity {

    EditText studcode;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join_code);

        //To all Caps edit text code.
        studcode = findViewById(R.id.studcode);
        submitBtn = findViewById(R.id.submitBtn);
        studcode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: write intent here to check the viewcode, if found, go to viewgrades
            }
        });
    }
}