package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class ViewPrograms extends AppCompatActivity {

    private RecyclerView programsRecView;
    private ProgramRecViewAdapter adapter;

    TextView toolbarName;

    private String schoolcode;

    //    declare College arraylist
    public ArrayList<Program> programs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_programs);
        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;
        Toast.makeText(getApplicationContext(), "Schoolcode: " + schoolcode + " is from SchoolMainScreen", Toast.LENGTH_LONG).show();

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Programs");
        getFunctionValley();
    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/programs.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                programsRecView = findViewById(R.id.programsRecView);
                adapter = new ProgramRecViewAdapter(ViewPrograms.this);

                programsRecView.setAdapter(adapter);
                programsRecView.setLayoutManager(new LinearLayoutManager(ViewPrograms.this));

                try {
                    JSONArray JA= new JSONArray(response);
                    for(int i=0;i<JA.length();i++) {
                        //receive JSON response to variables
                        String programid=JA.getJSONObject(i).get("programid").toString();
                        String programcode=JA.getJSONObject(i).get("programcode").toString();
                        String programname=JA.getJSONObject(i).get("programname").toString();
                        String major=JA.getJSONObject(i).get("major").toString();
                        String collegeid=JA.getJSONObject(i).get("collegeid").toString();
                        String collegecode=JA.getJSONObject(i).get("collegecode").toString();
                        String collegename=JA.getJSONObject(i).get("collegename").toString();
                        String pyear1=JA.getJSONObject(i).get("pyear1").toString();
                        String pyear2=JA.getJSONObject(i).get("pyear2").toString();
                        String schoolcode=JA.getJSONObject(i).get("schoolcode").toString();


                        //to add data in arraylist
                        programs.add(new Program(programid, programcode, programname, major, collegename, pyear1, pyear2));
                    }
                    adapter.setPrograms(programs);
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