package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import org.json.JSONObject;

public class SchoolMainScreen extends AppCompatActivity {

    TextView collegeCounterTextView, instructorCounterTextView,
              studentsCounterTextView, programCounterTextView,
            subjectsCounterTextView, sectionCounterTextView,
            ayCounterTextView;
    private String r="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_main_screen);

        collegeCounterTextView=findViewById(R.id.collegeCounterTextView);
        instructorCounterTextView=findViewById(R.id.instructorCounterTextView);
        studentsCounterTextView=findViewById(R.id.studentsCounterTextView);
        programCounterTextView=findViewById(R.id.programCounterTextView);
        subjectsCounterTextView=findViewById(R.id.subjectsCounterTextView);
        sectionCounterTextView=findViewById(R.id.sectionCounterTextView);
        ayCounterTextView=findViewById(R.id.ayCounterTextView);

        getFunctionValley();
    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/schoolmainscreen.php?schoolcode=66527c19b9";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                r=response;
                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(response);
                    int success = jsonResult.getInt("success");
                    if(success==1){
                        JSONArray datas = jsonResult.getJSONArray("data");
                        for(int i=0;i<datas.length();i++){
                            JSONObject data = datas.getJSONObject(i);
//                            $numofcolleges, $numofteachers, $numofstudents, $numofprograms, $numofsubjects, $numofsections, $numofay);
                            collegeCounterTextView.setText(data.getString("numofcolleges"));
                            instructorCounterTextView.setText(data.getString("$numofteachers"));
                            studentsCounterTextView.setText(data.getString("$numofstudents"));
                            programCounterTextView.setText(data.getString("$numofprograms"));
                            subjectsCounterTextView.setText(data.getString("$numofsubjects"));
                            sectionCounterTextView.setText(data.getString("$numofsections"));
                            ayCounterTextView.setText(data.getString("$numofay"));

                        }
                    }
                } catch (JSONException e) {
                        e.printStackTrace();
                    }
            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
//                collegeCounterTextView.setText("Response Get: Failed");
                Toast.makeText(getApplicationContext(), r + error.toString(), Toast.LENGTH_LONG).show();
            }
        });//Stringrequest
        queueu.add(stringRequest);
    }
}