package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class ViewSections extends AppCompatActivity {

    private RecyclerView sectionsRecView;
    private SectionRecViewAdapter adapter;
    private String schoolcode;

    TextView toolbarName;
    TextView toolbarAddButton;
    ImageView left_icon;

    ArrayList<Section> sections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sections);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Sections");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("schoolcode")){
            schoolcode= (sp.getString("schoolcode",""));
        }
//        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        toolbarAddButton=findViewById(R.id.toolbarAddButton);
        left_icon=findViewById(R.id.left_icon);

        toolbarAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbarAddButton = new Intent(ViewSections.this , addsection.class);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        getFunctionValley();
    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/sections.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                sectionsRecView = findViewById(R.id.sectionsRecView);
                adapter = new SectionRecViewAdapter(ViewSections.this);

                sectionsRecView.setAdapter(adapter);
                sectionsRecView.setLayoutManager(new LinearLayoutManager(ViewSections.this));
                sections.clear();
                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        //receive JSON response to variables
                        String sectionid=JA.getJSONObject(i).get("sectionid").toString();
                        String sectioncode=JA.getJSONObject(i).get("sectioncode").toString();
                        String programid=JA.getJSONObject(i).get("programid").toString();
                        String programcode=JA.getJSONObject(i).get("programcode").toString();

                        //to add data in arraylist
                        sections.add(new Section(sectionid, sectioncode, programcode));
                    }
                    adapter.setSections(sections);
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