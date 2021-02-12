package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class addsection extends AppCompatActivity{
Spinner spinnerSection;
ArrayAdapter <String> adapter;
ArrayList<String> spinnerList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsection);
        Spinner spinnerSection = findViewById(R.id.spinnerSection);

//        String[] sec = {"Section Code","Section Code","Section Code"};
//        ArrayList<String> Sec = new ArrayList<>(Arrays.asList(sec));
//        ArrayAdapter<String> arrayAdaptersec = new ArrayAdapter<>(this,R.layout.style_spinner,Sec);
//        spinnerSection.setAdapter(arrayAdaptersec);
        spinnerList.add("test");
        adapter = new ArrayAdapter<>(addsection.this, android.R.layout.simple_spinner_item,spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerSection.setAdapter(adapter);





    }
}