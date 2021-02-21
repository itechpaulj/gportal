package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ViewStudents extends AppCompatActivity {

    //Declaration of components
    private RecyclerView studentsRecView;
    private StudentRecViewAdapter adapter;
    TextView toolbarName;
    ImageView left_icon;
    private String schoolcode;

    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Students");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;
//        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        left_icon=findViewById(R.id.left_icon);
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
        String url = "http://jeepcard.net/gportal/students.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                adapter = new StudentRecViewAdapter(ViewStudents.this);
                studentsRecView = findViewById(R.id.studentsRecView);

                studentsRecView.setAdapter(adapter);
                studentsRecView.setLayoutManager(new LinearLayoutManager(ViewStudents.this));
                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        //receive JSON response to variables
                        String userid=JA.getJSONObject(i).get("userid").toString();
                        String cardid=JA.getJSONObject(i).get("cardid").toString();
                        String lname=JA.getJSONObject(i).get("lname").toString();
                        String fname=JA.getJSONObject(i).get("fname").toString();
                        String mname=JA.getJSONObject(i).get("mname").toString();
                        String gender=JA.getJSONObject(i).get("gender").toString();
                        String viewcode=JA.getJSONObject(i).get("viewcode").toString();
                        String collegeid=JA.getJSONObject(i).get("collegeid").toString();
                        String collegecode=JA.getJSONObject(i).get("collegecode").toString();
                        String sectionid=JA.getJSONObject(i).get("sectionid").toString();
                        String sectioncode=JA.getJSONObject(i).get("sectioncode").toString();
                        String programid=JA.getJSONObject(i).get("programid").toString();
                        String programcode=JA.getJSONObject(i).get("programcode").toString();
                        String photo=JA.getJSONObject(i).get("photo").toString();


                        //to add data in arraylist
                        students.add(new Student(cardid,lname, fname, mname, collegecode, programcode, sectioncode, viewcode, photo));
                    }
                    adapter.setStudents(students);
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