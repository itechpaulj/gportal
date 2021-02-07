package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Testinganbutton extends AppCompatActivity {

    Button patungko,pagumaoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinganbutton);



        patungko = findViewById(R.id.patungko);
        pagumaoc = findViewById(R.id.pagumaoc);

        patungko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patungko = new Intent(Testinganbutton.this , GradesAll2.class);
                startActivity(patungko);
            }
        });


        pagumaoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagumaoc = new Intent(Testinganbutton.this , RegisterTeacher2.class);
                startActivity(pagumaoc);
            }
        });

    }
}