package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class ViewTeachers extends AppCompatActivity {

    //Declaration of components
    private RecyclerView teachersRecView;
    private TeacherRecViewadapter adapter;
    TextView toolbarName;
    private String schoolcode;

    ArrayList<Teacher> teachers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teachers);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Teachers");

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;
        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        getFunctionValley();

    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/teachers.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                adapter = new TeacherRecViewadapter(ViewTeachers.this);
                teachersRecView = findViewById(R.id.teachersRecView);

                teachersRecView.setAdapter(adapter);
                teachersRecView.setLayoutManager(new LinearLayoutManager(ViewTeachers.this));


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
                        String collegeid=JA.getJSONObject(i).get("collegeid").toString();
                        String collegecode=JA.getJSONObject(i).get("collegecode").toString();
                        String photo=JA.getJSONObject(i).get("photo").toString();


                        //to add data in arraylist
                        teachers.add(new Teacher(cardid, lname, fname, mname, collegecode, photo));

                    }
                    adapter.setTeachers(teachers);
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