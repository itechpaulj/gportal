package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class addprograms extends AppCompatActivity {
    private Spinner spinnerCollege;
    private EditText programcode, programname, major;
    private Button addProgBtn;
    ImageView backBtn;

    private String schoolcode;
    private String code, name, major2, collegeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprograms);

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("schoolcode")){
            schoolcode= (sp.getString("schoolcode",""));
        }
        backBtn = findViewById(R.id.backBtn);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        spinnerCollege=(Spinner)findViewById(R.id.spinnerCollege);
        programcode=findViewById(R.id.programcode);
        programname=findViewById(R.id.programname);
        major=findViewById(R.id.major);
        addProgBtn=findViewById(R.id.addProgBtn);

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addProgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code=programcode.getText().toString();
                name=programname.getText().toString();
                major2=major.getText().toString();

                if(!code.equals("") && !name.equals("") &&!schoolcode.equals("") ){
                    addProgram(code, name, major2, collegeid, schoolcode);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(addprograms.this).create();
                    alertDialog.setTitle("Warning");
                    alertDialog.setMessage("Please supply the form");//Message to be shown
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }

        });

        //Arraylist for spinnercollege
        String[] co = {};
        ArrayList<String> Co = new ArrayList<>(Arrays.asList(co));

        //Array Adapter for Spinner college
        ArrayAdapter<String> arrayAdapters = new ArrayAdapter<>(this,R.layout.style_spinner,Co);

        //webrequest
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/collegespinner.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        String collegeidresponse=JA.getJSONObject(i).get("collegeid").toString();
                        //adding data in spinner's collegeid arraylist
                        Co.add(collegeidresponse);
                    }
                    //setting arrayadapter for spinnercollege
                    spinnerCollege.setAdapter(arrayAdapters);
                    spinnerCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                           //TODO: split string to get the id. example: 20- IIT, need to split before the character(-)
                            collegeid=spinnerCollege.getItemAtPosition(position).toString();
                            String[] values=collegeid.split("-");
                            collegeid=values[0];
//                            Toast.makeText(getApplicationContext(), "Selected " +collegeid, Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), "Plese select College", Toast.LENGTH_LONG).show();
                        }

                    });
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


    private void addProgram(String code, String name, String major2, String collegeid, String schoolcode) {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/addprogram.php?schoolcode="+schoolcode+"&programcode="+code+"&programname="+
                name+"&major="+major2+"&collegeid="+collegeid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(addprograms.this).create();
                alertDialog.setTitle("Message");
                alertDialog.setMessage(response);//Message to be shown
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
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