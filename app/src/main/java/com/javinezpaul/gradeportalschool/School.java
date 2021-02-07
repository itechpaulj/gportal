package com.javinezpaul.gradeportalschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class School extends AppCompatActivity {

    TextView Loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);


        Loginbtn = findViewById(R.id.Loginbtn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginschool = new Intent(School.this, Login2.class);
                startActivity(loginschool);
            }
        });

    }
}
