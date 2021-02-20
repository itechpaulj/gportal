package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentMianScreen extends AppCompatActivity {
   Button logout;
   TextView studentuser;

    //Declaration of components
    private RecyclerView gradesRecView;
    private gradesRecViewAdapter adapter;
    private String schoolcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian_screen);
        studentuser = (TextView) findViewById(R.id.studentuser);

        gradesRecView  = findViewById(R.id.gradesRecView);
        adapter = new gradesRecViewAdapter(this);
        gradesRecView.setAdapter(adapter);

        gradesRecView.setLayoutManager(new LinearLayoutManager(StudentMianScreen.this));

          //to add data in arraylist
          ArrayList<Grades> grades = new ArrayList<>();
          grades.add(new Grades("1", "1", "PHYSCI", "Physical Science", "1.00", "02-19-2021", "You rock!"));
          grades.add(new Grades("2", "2", "GENSCI", "General Science", "1.25", "02-19-2021", "Keep up the good work!"));
          grades.add(new Grades("3", "3", "IT101", "Economy Taxation & Agrarian Reform", "1.50", "02-19-2021", "Nice!"));


          adapter.setGrade(grades);



        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user");
                    editor.putString("msg","Logged Out Successfully");
                    editor.commit();
                    Intent hasloggedout = new Intent(StudentMianScreen.this , Login.class);
                    startActivity(hasloggedout);
                    finish();
                }

            }
        });
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("user")){
            SharedPreferences.Editor editor = sp.edit();
            studentuser.setText(sp.getString("user",""));
        }



    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button",Toast.LENGTH_LONG).show();
        return;
    }
}