package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class ViewSubjects extends AppCompatActivity {
    private RecyclerView subjectsRecView;
    private SubjectRecViewAdapter adapter;
    private String schoolcode;
    TextView toolbarName;
    TextView toolbarAddButton;
    ImageView left_icon;

    ArrayList<Subject> subjects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subjects);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;
//        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Subjects");

        toolbarAddButton=findViewById(R.id.toolbarAddButton);
        left_icon=findViewById(R.id.left_icon);

        toolbarAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbarAddButton = new Intent(ViewSubjects.this , addsubject.class);
                startActivity(toolbarAddButton);
            }
        });

        left_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getFunctionValley();
    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/subjects.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                subjectsRecView = findViewById(R.id.subjectsRecView);
                adapter = new SubjectRecViewAdapter(ViewSubjects.this);

                subjectsRecView.setAdapter(adapter);
                subjectsRecView.setLayoutManager(new LinearLayoutManager(ViewSubjects.this));

                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        String subjectid=JA.getJSONObject(i).get("subjectid").toString();
                        String subjectcode=JA.getJSONObject(i).get("subjectcode").toString();
                        String subjecttitle=JA.getJSONObject(i).get("subjecttitle").toString();
                        String programid=JA.getJSONObject(i).get("programid").toString();
                        String programcode=JA.getJSONObject(i).get("programcode").toString();
                        String programname=JA.getJSONObject(i).get("programname").toString();
                        String ayid=JA.getJSONObject(i).get("ayid").toString();
                        String ay=JA.getJSONObject(i).get("ay").toString();

                        //to add data in arraylist
                        subjects.add(new Subject(subjectid, subjectcode, subjecttitle, programcode, ay));
                    }
                    adapter.setSubjects(subjects);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Reconnecting", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Resolving error", Toast.LENGTH_LONG).show();
                getFunctionValley();
            }
        });//Stringrequest
        queueu.add(stringRequest);
    }
}