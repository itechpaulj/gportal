package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class newcode extends AppCompatActivity {
    private Spinner spinnerAy,spinnerCollege,spinnerProgram,spinnerSection,spinnerSubjects;
    private String schoolcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcode);

        spinnerAy = findViewById(R.id.spinnerAy);
        spinnerCollege = findViewById(R.id.spinnerCollege);
        spinnerProgram = findViewById(R.id.spinnerProgram);
        spinnerSection = findViewById(R.id.spinnerSection);
        spinnerSubjects = findViewById(R.id.spinnerSubjects);

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;

        // academic year
        String[]  ay = {};
        ArrayList<String> Ay = new ArrayList<>(Arrays.asList(ay));
        ArrayAdapter<String> arrayAdapter_ay = new ArrayAdapter<>(this,R.layout.style_spinner,Ay);

        RequestQueue requestQueue_ay = Volley.newRequestQueue(newcode.this);
        String url_ay = "http://jeepcard.net/gportal/newcode_ay.php?schoolcode="+schoolcode;
        StringRequest stringRequest_ay = new StringRequest(Request.Method.GET, url_ay, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    Ay.add("[Academic Year]");
                    JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                           String ayear = (String) jsonArray.getJSONObject(i).get("ayear").toString();
                            Ay.add(ayear);
                            spinnerAy.setAdapter(arrayAdapter_ay);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue_ay.add(stringRequest_ay);
        //academic year


        // college code
        String[]  co = {};
        ArrayList<String> Co = new ArrayList<>(Arrays.asList(co));
        ArrayAdapter<String> arrayAdapter_co = new ArrayAdapter<>(this,R.layout.style_spinner,Co);

        RequestQueue requestQueue_co = Volley.newRequestQueue(newcode.this);
        String url_co = "http://jeepcard.net/gportal/newcode_college.php?schoolcode="+schoolcode;
        StringRequest stringRequest_co = new StringRequest(Request.Method.GET, url_co, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Co.add("[College Code]");
                try {
                    JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            String college = jsonArray.getJSONObject(i).get("co").toString();
                            Co.add(college);
                            spinnerCollege.setAdapter(arrayAdapter_co);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue_co.add(stringRequest_co);
        // college code

        // program code
        String[]  pro = {};
        ArrayList<String> Pro = new ArrayList<>(Arrays.asList(pro));
        ArrayAdapter<String> arrayAdapter_pro = new ArrayAdapter<>(this,R.layout.style_spinner,Pro);


        RequestQueue requestQueue_pro = Volley.newRequestQueue(newcode.this);
        String url_pro = "http://jeepcard.net/gportal/newcode_program.php?schoolcode="+schoolcode;
        StringRequest stringRequest_pro = new StringRequest(Request.Method.GET, url_pro, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Pro.add("[Program Code]");
                        for(int i=0;i<jsonArray.length();i++){
                            String pro = (String) jsonArray.getJSONObject(i).get("pro").toString();
                            Pro.add(pro);
                            spinnerProgram.setAdapter(arrayAdapter_pro);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue_pro.add(stringRequest_pro);
        // program code

        // section code
        String[]  sec = {};
        ArrayList<String> Sec = new ArrayList<>(Arrays.asList(sec));
        ArrayAdapter<String> arrayAdapter_section = new ArrayAdapter<>(this,R.layout.style_spinner,Sec);

        RequestQueue requestQueue_sec = Volley.newRequestQueue(newcode.this);
        String url_sec = "http://jeepcard.net/gportal/newcode_section.php?schoolcode="+schoolcode;
        StringRequest stringRequest_sec = new StringRequest(Request.Method.GET, url_sec, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Sec.add("[Section Code]");

                try {
                    JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            String sec = (String) jsonArray.getJSONObject(i).get("sec").toString();
                            Sec.add(sec);
                            spinnerSection.setAdapter(arrayAdapter_section);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue_sec.add(stringRequest_sec);
        // section code

        // subject code
        String[]  sub = {};
        ArrayList<String> Sub = new ArrayList<>(Arrays.asList(sub));
        ArrayAdapter<String> arrayAdapter_sub = new ArrayAdapter<>(this,R.layout.style_spinner,Sub);
        spinnerSubjects.setAdapter(arrayAdapter_sub);

        RequestQueue requestQueue_sub = Volley.newRequestQueue(newcode.this);
        String url_sub = "http://jeepcard.net/gportal/newcode_subject.php?schoolcode="+schoolcode;
        StringRequest stringRequest_sub = new StringRequest(Request.Method.GET, url_sub, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Sub.add("[Subject Code]");
                try {
                    JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            String sub = (String) jsonArray.getJSONObject(i).get("sub").toString();
                            Sub.add(sub);
                            spinnerSubjects.setAdapter(arrayAdapter_sub);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue_sub.add(stringRequest_sub);
        // subject code
    }
}