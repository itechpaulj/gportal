package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    Button RegisterTeacher,StudentRegister;
    TextView schoolbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterTeacher = findViewById(R.id.RegisterTeacher);
        StudentRegister = findViewById(R.id.StudentRegister);
        schoolbtn=findViewById(R.id.schoolbtn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        TextView viewcode = (TextView) findViewById(R.id.viewcode);
        viewcode.setPaintFlags(viewcode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BAHNSCHRIFT.TTF");
        viewcode = (TextView) findViewById(R.id.viewcode);
        TextView footertext=(TextView)findViewById(R.id.footertext);
        TextView footertext2 =(TextView)findViewById(R.id.footertext2);
        TextView gportal = (TextView)findViewById(R.id.gportal);
        TextView schoolbtn = (TextView)findViewById(R.id.schoolbtn);


        footertext.setTypeface(typeface);
        footertext2.setTypeface(typeface);
        viewcode.setTypeface(typeface);
        gportal.setTypeface(typeface);
        schoolbtn.setTypeface(typeface);


        //Register form button for School
        schoolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerschool = new Intent(MainActivity.this,School.class);
                startActivity(registerschool);
            }
        });

        //Register form button for teacher
        RegisterTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerteacher = new Intent(MainActivity.this,RegisterTeacher.class);
                startActivity(registerteacher);
            }
        });

        //Register form  button for Student
        StudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentregister = new Intent(MainActivity.this,StudentRegister.class);
                startActivity(studentregister);
            }
        });


        //JoinCode button for Student
        viewcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joincode = new Intent(MainActivity.this,StudentJoinCode.class);
                startActivity(joincode);
            }
        });




        gportal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testing = new Intent(MainActivity.this,Testinganbutton.class);
                startActivity(testing);
            }
        });

    }
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("access")) {
//            Toast.makeText(getApplicationContext(),sp.getString("user","")+"\n"
//                    +sp.getString("name","")+"\n"
//                            +sp.getString("image","")+"\n"
//                            +sp.getString("cardid","")+"\n"
//                            +sp.getString("access","")+"\n"
//                    ,Toast.LENGTH_LONG).show();
            String access = (String) sp.getString("access","");
            if(access.equals("School")){
                Intent school = new Intent(MainActivity.this, SchoolMainScreen.class);
                startActivity(school);
            }

            if (access.equals("Teacher")){

                Intent teacher = new Intent(MainActivity.this, TeacherMainScreen.class);
                startActivity(teacher);
            }



        }

        else {
           Toast.makeText(getApplicationContext(), "Welcome Gportal", Toast.LENGTH_LONG).show();
        }
    }
}