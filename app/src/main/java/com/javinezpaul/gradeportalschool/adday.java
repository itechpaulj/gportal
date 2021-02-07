package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class adday extends AppCompatActivity {
    Spinner sem,year1,year2,level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adday);

        sem =(Spinner)findViewById(R.id.sem);
        year1 =(Spinner)findViewById(R.id.year1);
        year2 =(Spinner)findViewById(R.id.year2);
        level =(Spinner)findViewById(R.id.level);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sem, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapteryr= ArrayAdapter.createFromResource(this, R.array.yr, android.R.layout.simple_spinner_item);
        adapteryr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year1.setAdapter(adapteryr);

        ArrayAdapter<CharSequence> adapteryear = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapteryear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year2.setAdapter(adapteryear);

        ArrayAdapter<CharSequence> adapterlevel = ArrayAdapter.createFromResource(this, R.array.lvl, android.R.layout.simple_spinner_item);
        adapterlevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(adapterlevel);
    }
}