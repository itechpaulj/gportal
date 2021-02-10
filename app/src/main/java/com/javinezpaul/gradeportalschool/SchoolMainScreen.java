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

        collegeCounterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFunctionValley();
            }
        });

    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/schoolmainscreen.php?schoolcode=66527c19b9";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray JA= new JSONArray(response);
                    collegeCounterTextView.setText(JA.getJSONObject(0).get("numofcolleges").toString());
                    instructorCounterTextView.setText(JA.getJSONObject(1).get("numofteachers").toString());
                    studentsCounterTextView.setText(JA.getJSONObject(2).get("numofstudents").toString());
                    programCounterTextView.setText(JA.getJSONObject(3).get("numofprograms").toString());
                    subjectsCounterTextView.setText(JA.getJSONObject(4).get("numofsubjects").toString());
                    sectionCounterTextView.setText(JA.getJSONObject(5).get("numofsections").toString());
                    ayCounterTextView.setText(JA.getJSONObject(6).get("numofay").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Reconnecting", Toast.LENGTH_LONG).show();
                getFunctionValley();
            }
        });//Stringrequest
        queueu.add(stringRequest);
    }
}