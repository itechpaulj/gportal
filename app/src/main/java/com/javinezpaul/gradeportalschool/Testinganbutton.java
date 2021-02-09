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
            viewPrograms, viewSections,
            viewSubjects, viewAy,
			addSchool, addcollege,
            addprograms, addsection,
            addsubject,addTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinganbutton);

        /*
        id ni paul javinez
         */
        addSchool = (Button) findViewById(R.id.addSchool);
        addTeacher = findViewById(R.id.addTeacher);
        /*
        id ni paul javinez
         */
        patungko = findViewById(R.id.patungko);
        pagumaoc = findViewById(R.id.pagumaoc);
        viewCollege = findViewById(R.id.viewCollege);
        studRegister = findViewById(R.id.studRegister);
        schoolMainScreen = findViewById(R.id.schoolMainScreen);
        viewPrograms = findViewById(R.id.viewPrograms);
        viewSections = findViewById(R.id.viewSections);
        viewSubjects = findViewById(R.id.viewSubjects);
        viewAy = findViewById(R.id.viewAcadYear);
        addcollege = findViewById(R.id.addcollege);
        addprograms = findViewById(R.id.addprograms);
        addsection = findViewById(R.id.addsection);
        addsubject = findViewById(R.id.addsubject);



        patungko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patungko = new Intent(Testinganbutton.this , StudentJoinCode.class);
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

        viewCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCollege = new Intent(Testinganbutton.this , ViewCollege.class);
                startActivity(viewCollege);
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

        viewPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPrograms = new Intent(Testinganbutton.this , ViewPrograms.class);
                startActivity(viewPrograms);
            }
        });

        viewSections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSections = new Intent(Testinganbutton.this , ViewSections.class);
                startActivity(viewSections);
            }
        });

        viewSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSubjects = new Intent(Testinganbutton.this , ViewSubjects.class);
                startActivity(viewSubjects);
            }
        });
		
		addSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addSchool = new Intent(Testinganbutton.this , School.class);
                startActivity(addSchool);

            }
        });

				
		viewAy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAy = new Intent(Testinganbutton.this , ViewAy.class);
                startActivity(viewAy);

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
            }
        });
    }
}