package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

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
   Button Selectgrade,logout;
   TextView studentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian_screen);
        studentuser = (TextView) findViewById(R.id.studentuser);
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


        Selectgrade =findViewById(R.id.Selectgrade);


        Selectgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selecgrade = new Intent(StudentMianScreen.this,StudentMainScreen2.class);
                startActivity(selecgrade);

            }
        });


    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button",Toast.LENGTH_LONG).show();
        return;
    }
}