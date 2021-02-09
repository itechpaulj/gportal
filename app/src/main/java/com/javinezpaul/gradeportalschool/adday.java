package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class adday extends AppCompatActivity {
    Spinner spinnerSem,spinnerYear1,spinnerYear2,spinnerLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adday);

        spinnerSem =findViewById(R.id.spinnerSem);
        spinnerYear1=findViewById(R.id.spinnerYear1);
        spinnerYear2 =findViewById(R.id.spinnerYear2);
        spinnerLevel =findViewById(R.id.spinnerLevel);


        String[] sem = {"Sem","Sem","Sem"};
        ArrayList<String> Sem = new ArrayList<>(Arrays.asList(sem));
        ArrayAdapter<String> arrayAdaptersem = new ArrayAdapter<>(this,R.layout.style_spinner,Sem);
        spinnerSem.setAdapter(arrayAdaptersem);

        String[] year1 = {"Year1","Year1","Year1"};
        ArrayList<String> Year1 = new ArrayList<>(Arrays.asList(year1));
        ArrayAdapter<String> arrayAdapterYear1 = new ArrayAdapter<>(this,R.layout.style_spinner,Year1);
        spinnerYear1.setAdapter(arrayAdapterYear1);

        String[] year2 = {"Year2","Year2","Year2"};
        ArrayList<String> Year2 = new ArrayList<>(Arrays.asList(year2));
        ArrayAdapter<String> arrayAdapterYear2 = new ArrayAdapter<>(this,R.layout.style_spinner,Year2);
        spinnerYear2.setAdapter(arrayAdapterYear2);

        String[] lvl= {"Level","Level","Level"};
        ArrayList<String> Lvl = new ArrayList<>(Arrays.asList(lvl));
        ArrayAdapter<String> arrayAdapterlevel = new ArrayAdapter<>(this,R.layout.style_spinner,Lvl);
        spinnerLevel.setAdapter(arrayAdapterlevel);
    }
}