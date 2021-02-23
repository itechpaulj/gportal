package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class StudentJoinCode extends AppCompatActivity {

    EditText studcode;
    Button submitBtn;
    ImageButton backBtn;
    String cardid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join_code);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //To all Caps edit text code.
        studcode = findViewById(R.id.studcode);
        submitBtn = findViewById(R.id.submitBtn);
        studcode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: write intent here to check the viewcode, if found, go to viewgrades
//                Intent intent = new Intent(mContext , TeachersCodeStudents.class);
//                intent.putExtra("teacherscode", studentsGrades.get(position).getTeachescode() );
//                intent.putExtra("toolbarName", studentsGrades.get(position).getSubjecttitle() );
//                mContext.startActivity(intent);


                //sharePreference
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")) {
                    SharedPreferences.Editor editor = sp.edit();
                    cardid = sp.getString("user", "");

                }

                //sharePreference

                // request get volley
                RequestQueue requestQueue = Volley.newRequestQueue(StudentJoinCode.this);
                String url = "http://jeepcard.net/gportal/viewcode.php?viewcode="+studcode.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if(response.equals("View code not found!")){
                            Toast.makeText(getApplicationContext(), response,Toast.LENGTH_LONG).show();
                        }
                        else{
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",response);
                            editor.putString("viewcode","exist");
                            editor.commit();
                            Intent viewcode = new Intent(StudentJoinCode.this,StudentMianScreen.class);
                            startActivity(viewcode);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(stringRequest);
                // request get volley


            }
        });
    }
}