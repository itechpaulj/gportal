package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class TeacherMainScreen extends AppCompatActivity {
    private Button logout,newcode;
    private TextView teacheruser;

    private RecyclerView teachersCodeRecView;
    private TeachersCodeRecViewAdapter adapter;

    ArrayList<TeachersCode> teachersCodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);

        teacheruser = (TextView) findViewById(R.id.teacheruser);
        logout = (Button) findViewById(R.id.logout);
        newcode = (Button) findViewById(R.id.newcode);

        //TODO: insert lines 48 - 67 in volley request function.
        // RecView Adapter and initialization
        teachersCodeRecView = findViewById(R.id.teachersCodeRecView);
        adapter = new TeachersCodeRecViewAdapter(TeacherMainScreen.this);
        teachersCodeRecView.setAdapter(adapter);
        teachersCodeRecView.setLayoutManager(new LinearLayoutManager(TeacherMainScreen.this));


        //adding data in recyclerview
        teachersCodes.clear();
        teachersCodes.add(new TeachersCode("1", "TE1234", "1", "1", "1", "1",
                        "1", "2021-02-20", "IIT", "Industrial and Information Technology", "ACT",
                "Associate in Computer Technology", "IT1A", "GENSCI", "General Science 101",
                "2020", "2021", "1st", "1st"));
        teachersCodes.add(new TeachersCode("1", "TE4321", "1", "1", "1", "1",
                "1", "2021-02-20", "IIT", "Industrial and Information Technology", "ACT",
                "Associate in Computer Technology", "IT1A", "GENSCI", "Physical Science",
                "2020", "2021", "2nd", "2nd"));

        adapter.setTeachersCodes(teachersCodes);

        // session teacher user
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("user")){
            SharedPreferences.Editor editor = sp.edit();
            teacheruser.setText(sp.getString("user",""));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user");
                    editor.putString("msg","Logged Out Successfully");
                    editor.commit();
                    Intent hasloggedout = new Intent(TeacherMainScreen.this , Login.class);
                    startActivity(hasloggedout);
                    finish();
                }

            }
        });
        // session teacher user

        newcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherMainScreen.this,newcode.class);
                startActivity(i);
            }
        });




        // has shown data teacher main screen

    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button", Toast.LENGTH_LONG).show();
        return;

    }
}