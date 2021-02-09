package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    Button RegisterTeacher,StudentRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterTeacher = findViewById(R.id.RegisterTeacher);
        StudentRegister = findViewById(R.id.StudentRegister);


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




        gportal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testing = new Intent(MainActivity.this,Testinganbutton.class);
                startActivity(testing);
            }
        });












    }
}