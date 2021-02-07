package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentRegister extends AppCompatActivity {
    Button studentNext;
    TextView studloginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        studentNext = findViewById(R.id.studentNext);
        studloginbtn = findViewById(R.id.studloginbtn);

        studentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentreg = new Intent(StudentRegister.this,StudentRegister2.class);
                startActivity(studentreg);
            }
        });

        studloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentlog = new Intent(StudentRegister.this,Login.class);
                startActivity(studentlog);
            }
        });
    }
}