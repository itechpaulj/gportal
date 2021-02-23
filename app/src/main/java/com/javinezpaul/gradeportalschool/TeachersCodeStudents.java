package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class TeachersCodeStudents extends AppCompatActivity {

    //TODO: pass teacherscode here
    //Passed teacherscode
    private String teacherscode="";

    private RecyclerView teachersCodeStudentsRecView;
    private StudGradesRecViewAdapter adapter;

    TextView toolbarName;
    ImageView left_icon;

    private ArrayList<StudentsGrade> studentsGrades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_code_students);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Subjectname Students");

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            String a =(String) b.get("toolbarName");
            String c =(String) b.get("teacherscode");
            toolbarName.setText(a);
            teacherscode=c;
        }


//        Toast.makeText(this, teacherscode, Toast.LENGTH_LONG).show();
        left_icon=findViewById(R.id.left_icon);
        left_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeachersCodeStudents.this , TeacherMainScreen.class);
                startActivity(intent);
                finish();
            }
        });

        teachersCodeStudentsRecView=findViewById(R.id.teachersCodeStudentsRecView);
        showStudentsByTeachersCode();

    }

    private void showStudentsByTeachersCode() {
        Toast.makeText(getApplicationContext(),"teacherscode: "+teacherscode,Toast.LENGTH_LONG).show();

        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/studentsByTeachersCode.php?teacherscode="+teacherscode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int i=0;
                adapter = new StudGradesRecViewAdapter(TeachersCodeStudents.this);
                teachersCodeStudentsRecView.setAdapter(adapter);
                teachersCodeStudentsRecView.setLayoutManager(new LinearLayoutManager(TeachersCodeStudents.this));
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                studentsGrades.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(i=0;i<jsonArray.length();i++){
                        String gradeid=jsonArray.getJSONObject(i).get("gradeid").toString();
                        String subjectid=jsonArray.getJSONObject(i).get("subjectid").toString();
                        String teacherscode=jsonArray.getJSONObject(i).get("teacherscode").toString();
                        String userid=jsonArray.getJSONObject(i).get("userid").toString();
                        String grade=jsonArray.getJSONObject(i).get("grade").toString();
                        String date=jsonArray.getJSONObject(i).get("date").toString();
                        String note=jsonArray.getJSONObject(i).get("note").toString();
                        String name=jsonArray.getJSONObject(i).get("name").toString();
                        String cardid=jsonArray.getJSONObject(i).get("cardid").toString();
                        String subjecttitle=jsonArray.getJSONObject(i).get("subjecttitle").toString();

                        studentsGrades.add(new StudentsGrade(gradeid, grade, date, note, teacherscode, name, userid, cardid,subjectid, subjecttitle));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(i==0){
                    studentsGrades.add(new StudentsGrade("", ":(", "", "No note found", "", "No data found", "", "","", ""));
                }
                adapter.setStudentsGrades(studentsGrades);
            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Network unstable, please retry", Toast.LENGTH_LONG).show();
            }
        });//Stringrequest
        queueu.add(stringRequest);

    }
}