package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentMainScreen2 extends AppCompatActivity {
    Spinner Ay,Sem,Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen2);


        Ay = findViewById(R.id.Ay);
        Sem = findViewById(R.id.Sem);
        Level = findViewById(R.id.Level);


        String[]  value = {"  2021-2022","  2022-2023","  2023-2024"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList);
        Ay.setAdapter(arrayAdapter);

        String[]  value2 = {"  First Sem","  Second Sem","  4th Gear"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(value2));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList2);
        Sem.setAdapter(arrayAdapter2);

        String[]  value3 = {"  One","  Two","  Three"};
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList(value3));
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList3);
        Level.setAdapter(arrayAdapter3);

    }
}