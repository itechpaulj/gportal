package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ViewCollege extends AppCompatActivity {

    //Declaration of components
    private RecyclerView collegesRecView;
    private CollegeRecViewAdapter adapter;
    private String schoolcode;

    TextView toolbarName;
    TextView toolbarAddButton;
    ImageView left_icon;

//    declare College arraylist
    public ArrayList<College> colleges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_college);
//        String schoolcode=getIntent().getStringExtra("schoolcode");
        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();
        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Colleges");

        toolbarAddButton=findViewById(R.id.toolbarAddButton);
        left_icon=findViewById(R.id.left_icon);
        
        toolbarAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbarAddButton = new Intent(ViewCollege.this , addcollege.class);
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
        String url = "http://jeepcard.net/gportal/colleges.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                adapter = new CollegeRecViewAdapter(ViewCollege.this);
                collegesRecView = findViewById(R.id.collegesRecView);

                collegesRecView.setAdapter(adapter);
                collegesRecView.setLayoutManager(new LinearLayoutManager(ViewCollege.this));

                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        String collegeid=JA.getJSONObject(i).get("collegeid").toString();
                        String collegecode=JA.getJSONObject(i).get("collegecode").toString();
                        String collegename=JA.getJSONObject(i).get("collegename").toString();

                        //to add data in arraylist
                        colleges.add(new College(collegeid,collegecode,collegename));
                    }
                    adapter.setColleges(colleges);
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