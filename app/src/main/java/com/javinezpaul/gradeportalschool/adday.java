package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;

public class adday extends AppCompatActivity {
    Spinner spinnerSem,spinnerYear1,spinnerYear2,spinnerLevel;
    Button addAyBtn;
    ImageView backBtn;
    int c=0;
    private String schoolcode="";
    private String semToSend="";
    private String year1ToSend="";
    private String year2ToSend="";
    private String levelToSend="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adday);
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("schoolcode")){
            schoolcode= (sp.getString("schoolcode",""));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        backBtn = findViewById(R.id.backBtn);
        spinnerSem =findViewById(R.id.spinnerSem);
        spinnerYear1=findViewById(R.id.spinnerYear1);
        spinnerYear2 =findViewById(R.id.spinnerYear2);
        spinnerLevel =findViewById(R.id.spinnerLevel);
        addAyBtn =findViewById(R.id.addAyBtn);

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String[] sem = {"1st SEM","2nd SEM"};
        ArrayList<String> Sem = new ArrayList<>(Arrays.asList(sem));
        ArrayAdapter<String> arrayAdaptersem = new ArrayAdapter<>(this,R.layout.style_spinner,Sem);
        spinnerSem.setAdapter(arrayAdaptersem);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                semToSend=Integer.toString(position+1);
//                semToSend=spinnerSem.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), spinnerYear1.getItemAtPosition(position).toString() + " at position " + position + " Selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select Sem", Toast.LENGTH_LONG).show();
            }

        });

        String[] year1 = {"2024"};
        ArrayList<String> Year1 = new ArrayList<>(Arrays.asList(year1));
        for(int i=2023; i>=1970; i--){
            Year1.add(Integer.toString(i));
        }
        ArrayAdapter<String> arrayAdapterYear1 = new ArrayAdapter<>(this,R.layout.style_spinner,Year1);
        spinnerYear1.setAdapter(arrayAdapterYear1);
        spinnerYear1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                year1ToSend=spinnerYear1.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), spinnerYear1.getItemAtPosition(position).toString() + " at position " + position + " Selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select Year(from)", Toast.LENGTH_LONG).show();
            }

        });

        String[] year2 = {"2025"};
        ArrayList<String> Year2 = new ArrayList<>(Arrays.asList(year2));
        for(int i=2024; i>=1970; i--){
            Year2.add(Integer.toString(i));
        }
        ArrayAdapter<String> arrayAdapterYear2 = new ArrayAdapter<>(this,R.layout.style_spinner,Year2);
        spinnerYear2.setAdapter(arrayAdapterYear2);
        spinnerYear2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                year2ToSend=spinnerYear2.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), spinnerYear1.getItemAtPosition(position).toString() + " at position " + position + " Selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select Year(to)", Toast.LENGTH_LONG).show();
            }

        });

        String[] lvl= {"1st year","2nd year","3rd year", "4th year"};
        ArrayList<String> Lvl = new ArrayList<>(Arrays.asList(lvl));
        ArrayAdapter<String> arrayAdapterlevel = new ArrayAdapter<>(this,R.layout.style_spinner,Lvl);
        spinnerLevel.setAdapter(arrayAdapterlevel);
        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                levelToSend=Integer.toString(position+1);
//                semToSend=spinnerSem.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), spinnerYear1.getItemAtPosition(position).toString() + " at position " + position + " Selected", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select Year Level", Toast.LENGTH_LONG).show();
            }

        });

        addAyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(year1ToSend)>=Integer.parseInt(year2ToSend) || schoolcode.equals("") ||
                        !(Integer.parseInt(year1ToSend)+1==Integer.parseInt(year2ToSend))){
                    AlertDialog alertDialog = new AlertDialog.Builder(adday.this).create();
                    alertDialog.setTitle("Warning");
                    alertDialog.setMessage("Year duration behind \nOR more than 1 year\nOR School code missing");//Message to be shown
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{
                    addAy(semToSend, year1ToSend, year2ToSend, levelToSend, schoolcode);
                }

            }
        });

    }

    private void addAy(String semToSend, String year1ToSend, String year2ToSend, String levelToSend, String schoolcode) {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/adday.php?aysem="+semToSend+"&ayear1="+year1ToSend+"&ayear2="+year2ToSend+"&ayearlevel="+levelToSend+"&schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(adday.this).create();
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