package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.Arrays;

public class StudentMianScreen extends AppCompatActivity {
   Button logout;
   TextView studentuser;
   Spinner spinnerAcademicYear;

    //Declaration of components
    private RecyclerView gradesRecView;
    private gradesRecViewAdapter adapter;
    private String schoolcode;

    public String userid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian_screen);
        studentuser = (TextView) findViewById(R.id.studentuser);
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("user")){
            SharedPreferences.Editor editor = sp.edit();
            studentuser.setText(sp.getString("user",""));
            userid=sp.getString("user","").toString();
        }


        gradesRecView  = findViewById(R.id.gradesRecView);
        adapter = new gradesRecViewAdapter(this);
        gradesRecView.setAdapter(adapter);

        spinnerAcademicYear=findViewById(R.id.spinnerAcademicYear);


        //spinner initialization
        String[] value = {"2021-2022","2021-2022","2021-2022"};
        ArrayList<String> ayspinner = new ArrayList<>(Arrays.asList(value));
        ayspinner.clear();

        //HTTP request
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/gradesay.php?userid="+userid+"&ayid=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        String collegeid=JA.getJSONObject(i).get("collegeid").toString();
                        String collegecode=JA.getJSONObject(i).get("collegecode").toString();
                        String collegename=JA.getJSONObject(i).get("collegename").toString();

                        //to add data in arraylist spinner
                        ayspinner.add("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Reconnecting", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "An error occured\n" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });//Stringrequest
        queueu.add(stringRequest);


        //spinner adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,ayspinner);
        spinnerAcademicYear.setAdapter(arrayAdapter);


        gradesRecView.setLayoutManager(new LinearLayoutManager(StudentMianScreen.this));

          //to add data in grades arraylist
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

    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button",Toast.LENGTH_LONG).show();
        return;
    }
}