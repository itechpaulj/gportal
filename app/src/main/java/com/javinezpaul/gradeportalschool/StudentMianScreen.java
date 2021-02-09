package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentMianScreen extends AppCompatActivity {
   Button Selectgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian_screen);


        Selectgrade =findViewById(R.id.Selectgrade);


        Selectgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selecgrade = new Intent(StudentMianScreen.this,StudentMainScreen2.class);
                startActivity(selecgrade);

            }
        });


    }
}