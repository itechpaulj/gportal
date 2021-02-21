package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
   Button logout, joinbtn;
   TextView studentuser, gwa, subjectsEnrolled;
   Spinner spinnerAcademicYear, spinnerSem;

    //Declaration of components
    private RecyclerView gradesRecView;
    private gradesRecViewAdapter adapter;
    public String ayearlevel="", aysem="";

    public String userid="";
    private String m_Text = "";

    private  double gwa2=0.00, subjectsEnrolled2=0.00;

    ArrayList<Grades> grades = new ArrayList<>();


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


        spinnerAcademicYear=findViewById(R.id.spinnerYearLevel);
        spinnerSem=findViewById(R.id.spinnerSem);
        gwa=findViewById(R.id.gwa);
        subjectsEnrolled=findViewById(R.id.subjectsEnrolled);
        joinbtn=findViewById(R.id.joinbtn);


        //spinner initialization
        String[] value = {"1", "2", "3", "4","5"};
        ArrayList<String> ayspinner = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.style_spinner,ayspinner);
        spinnerAcademicYear.setAdapter(arrayAdapter);
        spinnerAcademicYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ayearlevel = spinnerAcademicYear.getSelectedItem().toString();
//                Toast.makeText(getApplicationContext(), ayearlevel, Toast.LENGTH_LONG).show();
                getGrades();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //spinner initialization
        String[] value1 = {"1", "2"};
        ArrayList<String> ayspinnersem = new ArrayList<>(Arrays.asList(value1));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.style_spinner,ayspinnersem);
        spinnerSem.setAdapter(arrayAdapter2);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                aysem = spinnerSem.getSelectedItem().toString();
//                Toast.makeText(getApplicationContext(), aysem, Toast.LENGTH_LONG).show();
                getGrades();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //button functionalities
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


        joinbtn=findViewById(R.id.joinbtn);
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentMianScreen.this);
                builder.setTitle("Enter the class code");

                // Set up the input
                final EditText input = new EditText(StudentMianScreen.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Toast.makeText(getApplicationContext(),m_Text,Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        //Function calls
        getGrades();
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button",Toast.LENGTH_LONG).show();
        return;
    }

    public void getGrades(){
        //HTTP request
        Boolean hasRecord=false;
        ayearlevel = spinnerAcademicYear.getSelectedItem().toString();
        aysem = spinnerSem.getSelectedItem().toString();
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/gradesay.php?userid="+userid+"&ayearlevel="+ayearlevel+"&aysem="+aysem;

        //reset
        grades.clear(); gwa2=0.00; subjectsEnrolled2=0.00;
        //print average results
        gwa2=gwa2/subjectsEnrolled2;
        gwa.setText(String.valueOf(gwa2));
        subjectsEnrolled.setText(String.valueOf(subjectsEnrolled2));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response +" : \nuserid: "+ userid + "\nayearlevel: " + ayearlevel + " aysem: " + aysem, Toast.LENGTH_LONG).show();

                gradesRecView  = findViewById(R.id.gradesRecView);
                adapter = new gradesRecViewAdapter(StudentMianScreen.this);

                gradesRecView.setAdapter(adapter);
                gradesRecView.setLayoutManager(new LinearLayoutManager(StudentMianScreen.this));
                grades.clear(); gwa2=0.00; subjectsEnrolled2=0.00;
                grades.add(new Grades("", "", "", "SUBJECT", "GRADE", "", "Header"));
                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        String gradeid=JA.getJSONObject(i).get("gradeid").toString();
                        String subjectid=JA.getJSONObject(i).get("subjectid").toString();
                        String subjectcode=JA.getJSONObject(i).get("subjectcode").toString();
                        String subjecttitle=JA.getJSONObject(i).get("subjecttitle").toString();
                        String grade=JA.getJSONObject(i).get("grade").toString();
                        gwa2=gwa2+Double.parseDouble(grade);
                        String date=JA.getJSONObject(i).get("date").toString();
                        String note=JA.getJSONObject(i).get("note").toString();
                        String ayear1=JA.getJSONObject(i).get("ayear1").toString();
                        String ayear2=JA.getJSONObject(i).get("ayear2").toString();
                        String ayearlevel=JA.getJSONObject(i).get("ayearlevel").toString();
                        String aysem=JA.getJSONObject(i).get("aysem").toString();

                        //to add data in grades arraylist
                        grades.add(new Grades(gradeid, subjectid, subjectcode, subjecttitle, grade, date, note));
                        ++subjectsEnrolled2;
                    }
                    adapter.setGrade(grades);

                    //print average results
                    gwa2=gwa2/subjectsEnrolled2;
                    gwa.setText(String.valueOf(gwa2));
                    subjectsEnrolled.setText(String.valueOf(subjectsEnrolled2));
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
    }
}