package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GradesAll extends AppCompatActivity {
    TextView viewallgrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_all);


        viewallgrade = findViewById(R.id.viewallgrade);

        viewallgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewgradesall = new Intent(GradesAll.this,GradesAll2.class);
                startActivity(viewgradesall);
            }
        });
    }
}