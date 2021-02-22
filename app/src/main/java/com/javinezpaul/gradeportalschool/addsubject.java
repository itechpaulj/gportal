package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class addsubject extends AppCompatActivity {
    private Spinner spinnerProgram,spinnerAcademicYear;
    private String schoolcode;
    private Button addSubject;
    private EditText subjectcode,subjecttitle;
    ImageView backBtn;

    // programcode
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    // academic year
    ArrayList<String> arrayList1 = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        backBtn = findViewById(R.id.backBtn);
        spinnerProgram = (Spinner) findViewById(R.id.spinnerProgram);
        spinnerAcademicYear = (Spinner) findViewById(R.id.spinnerAcademicYear);

        addSubject = (Button) findViewById(R.id.addSubject);

        subjectcode = (EditText) findViewById(R.id.subjectcode);
        subjecttitle = (EditText) findViewById(R.id.subjecttitle);

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;


        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
         // ------------
        // Programcode
        RequestQueue requestQueue = Volley.newRequestQueue(addsubject.this);
        String url = "http://jeepcard.net/gportal/spinnersubjectprogramcode.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                            arrayList.add("[Program Code]");
                        if(success==1){
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String getProgamid = data.getString("progcodeid");
                                arrayList.add(getProgamid);
                                arrayAdapter = new ArrayAdapter<>(addsubject.this, android.R.layout.simple_spinner_item,arrayList);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                spinnerProgram.setAdapter(arrayAdapter);
                            }
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
        requestQueue.add(stringRequest);
        // end programcode


        //---
        // Academic Year
        RequestQueue requestQueue1 = Volley.newRequestQueue(addsubject.this);
        String url1 = "http://jeepcard.net/gportal/spinnersubjectacdemicyear.php?schoolcode="+schoolcode;
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                        arrayList1.add("[Academic Year]");
                        if(success==1){
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String ayid = data.getString("acyrid");
                                arrayList1.add(ayid);
                                arrayAdapter1 = new ArrayAdapter<>(addsubject.this, android.R.layout.simple_spinner_item,arrayList1);
                                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                spinnerAcademicYear.setAdapter(arrayAdapter1);
                            }

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
        requestQueue1.add(stringRequest1);

        //end Academic Year



        //---
        //addSubject btn
        // TODO need getString as split in array

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prorgamcode
                String get_input_spinner = ""+spinnerProgram.getSelectedItem();
                String[] getFirstIndex = get_input_spinner.split("-");
                // academicyear
                String get_input_spinner1 = ""+spinnerAcademicYear.getSelectedItem();
                String[] getFirstIndex1 = get_input_spinner1.split("-");
                //Toast.makeText(getApplicationContext(),getFirstIndex[0]+" "+getFirstIndex1[0],Toast.LENGTH_LONG).show();

                    String input_get_subjectcode = (String) subjectcode.getText().toString().trim();
                    String input_get_subjecttitle = (String) subjecttitle.getText().toString().trim();
                    if(input_get_subjectcode.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Please Input Subject Code",Toast.LENGTH_LONG).show();
                    }
                    else if(input_get_subjecttitle.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Please Input Subject Name ",Toast.LENGTH_LONG).show();
                    }
                    else if(get_input_spinner.equals("[Program Code]")){
                        Toast.makeText(getApplicationContext(),"Please Select Program code",Toast.LENGTH_LONG).show();
                    }
                    else if(get_input_spinner1.equals("[Academic Year]")){
                        Toast.makeText(getApplicationContext(),"Please Select Academic year",Toast.LENGTH_LONG).show();
                    }
                    else{

                        RequestQueue requestQueue2 = Volley.newRequestQueue(addsubject.this);
                        String url2 = "http://jeepcard.net/gportal/addSubject.php";
                        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                if(response.equals("Success")){
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<>();
                                params.put("subjectcode",input_get_subjectcode);
                                params.put("subjecttitle",input_get_subjecttitle);
                                params.put("programecode",getFirstIndex[0]);
                                params.put("academicyear",getFirstIndex1[0]);
                                params.put("schoolcode",schoolcode);
                                return params;
                            }
                        };
                        requestQueue2.add(stringRequest2);

                    }
            }
        });


//        String[] program = {"Program","Program","Program"};
//        ArrayList<String> Program = new ArrayList<>(Arrays.asList(program));
//        ArrayAdapter<String> arrayAdapterpro = new ArrayAdapter<>(this,R.layout.style_spinner,Program);
//        spinnerProgram.setAdapter(arrayAdapterpro);
//
//        String[] academic = {"Academic Year","Academic Year","Academic Year"};
//        ArrayList<String> Academic = new ArrayList<>(Arrays.asList(academic));
//        ArrayAdapter<String> arrayAdapteryear = new ArrayAdapter<>(this,R.layout.style_spinner,Academic);
//        spinnerAcademicYear.setAdapter(arrayAdapteryear);

    }
}