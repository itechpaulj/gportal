package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class ViewAy extends AppCompatActivity {

    private RecyclerView ayRecView;
    private AyRecViewAdapter adapter;
    TextView toolbarName;
    TextView toolbarAddButton;
    ImageView left_icon;
    private String schoolcode;

    ArrayList<Ay> ays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ay);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Academic Year");

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;
//        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        toolbarAddButton=findViewById(R.id.toolbarAddButton);
        left_icon=findViewById(R.id.left_icon);

        toolbarAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbarAddButton = new Intent(ViewAy.this , adday.class);
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
        String url = "http://jeepcard.net/gportal/ay.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                ayRecView = findViewById(R.id.ayRecView);
                adapter = new AyRecViewAdapter(ViewAy.this);

                ayRecView.setAdapter(adapter);
                ayRecView.setLayoutManager(new LinearLayoutManager(ViewAy.this));

                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        //receive JSON response to variables
                        String ayid=JA.getJSONObject(i).get("ayid").toString();
                        String ayear1=JA.getJSONObject(i).get("ayear1").toString();
                        String ayear2=JA.getJSONObject(i).get("ayear2").toString();
                        String ayearlevel=JA.getJSONObject(i).get("ayearlevel").toString();
                        String aysem=JA.getJSONObject(i).get("aysem").toString();


                        //to add data in arraylist
                        ays.add(new Ay(ayid, aysem, ayear1, ayear2, ayearlevel));
                    }
                    adapter.setAys(ays);
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