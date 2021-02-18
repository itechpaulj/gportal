package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Testinganbutton extends AppCompatActivity {


    Button patungko,
            pagumaoc, viewCollege,
            studRegister, schoolMainScreen,
			addSchool, addcollege,
            addprograms, addsection,
            addsubject,addTeacher,
            addstudent,hasloggedin,
            loggedin2,teachermainscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinganbutton);

        /*
        id ni paul javinez
         */
        addSchool = (Button) findViewById(R.id.addSchool);
        addTeacher = findViewById(R.id.addTeacher);
        addstudent = findViewById(R.id.addstudent);
        hasloggedin = findViewById(R.id.hasloggedin);
        loggedin2 = findViewById(R.id.loggedin2);
        teachermainscreen = findViewById(R.id.teachermainscreen);
        /*
        id ni paul javinez
         */
        patungko = findViewById(R.id.patungko);
        pagumaoc = findViewById(R.id.pagumaoc);
        studRegister = findViewById(R.id.studRegister);
        schoolMainScreen = findViewById(R.id.schoolMainScreen);
        addcollege = findViewById(R.id.addcollege);
        addprograms = findViewById(R.id.addprograms);
        addsection = findViewById(R.id.addsection);
        addsubject = findViewById(R.id.addsubject);



        patungko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patungko = new Intent(Testinganbutton.this , Login.class);
                startActivity(patungko);

            }
        });



        pagumaoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagumaoc = new Intent(Testinganbutton.this , RegisterTeacher.class);
                startActivity(pagumaoc);

            }
        });

        


        studRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studRegister = new Intent(Testinganbutton.this , StudentRegister.class);
                startActivity(studRegister);

            }
        });

        schoolMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schoolMainScreen = new Intent(Testinganbutton.this , SchoolMainScreen.class);
                startActivity(schoolMainScreen);

            }
        });


		
		addSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addSchool = new Intent(Testinganbutton.this , School.class);
                startActivity(addSchool);
                finish();

            }
        });


		addcollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addcollege = new Intent(Testinganbutton.this , addcollege.class);
                startActivity(addcollege);

            }
        });
        addprograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addprograms = new Intent(Testinganbutton.this , addprograms.class);
                startActivity(addprograms);

            }
        });
		addsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addsection = new Intent(Testinganbutton.this , addsection.class);
                startActivity(addsection);

            }
        });

        addsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addsubject = new Intent(Testinganbutton.this , addsubject.class);
                startActivity(addsubject);

            }
        });

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTeacher = new Intent(Testinganbutton.this , RegisterTeacher.class);
                startActivity(addTeacher);
                finish();
            }
        });

        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addstudent = new Intent(Testinganbutton.this , StudentRegister.class);
                startActivity(addstudent);
                finish();
            }
        });

        hasloggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hasloggedin = new Intent(Testinganbutton.this , Login.class);
                startActivity(hasloggedin);
                finish();
            }
        });

        loggedin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hasloggedin = new Intent(Testinganbutton.this , TeacherMainScreen.class);
                startActivity(hasloggedin);
                finish();
            }
        });

        teachermainscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teachermainscreen = new Intent(Testinganbutton.this , TeacherMainScreen.class);
                startActivity(teachermainscreen);
                finish();
            }
        });
    }
}