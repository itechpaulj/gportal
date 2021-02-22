package com.javinezpaul.gradeportalschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class SchoolMainScreen extends AppCompatActivity{

    TextView collegeCounterTextView, instructorCounterTextView,
              studentsCounterTextView, programCounterTextView,
            subjectsCounterTextView, sectionCounterTextView,
            ayCounterTextView , schooluser;

    CardView cardviewColleges, cardviewTeachers,
            cardviewStudents, cardviewPrograms,
            cardviewSubjects, cardviewSections,
            cardviewAy;

    Button logout;
    public String schoolcode="66527c19b9";
//    public String schoolcode="c86d491fad";

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

        cardviewColleges=findViewById(R.id.cardviewColleges);
        cardviewTeachers=findViewById(R.id.cardviewTeachers);
        cardviewStudents=findViewById(R.id.cardviewStudents);
        cardviewPrograms=findViewById(R.id.cardviewPrograms);
        cardviewSubjects=findViewById(R.id.cardviewSubjects);
        cardviewSections=findViewById(R.id.cardviewSections);
        cardviewAy=findViewById(R.id.cardviewAy);

        logout = (Button) findViewById(R.id.logout);
        schooluser = (TextView) findViewById(R.id.schooluser);

        //note session build in ANDROID STUDIO
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);


            schooluser.setText(sp.getString("user",""));
            // inside session
            RequestQueue requestQueue = Volley.newRequestQueue(SchoolMainScreen.this);
            String url = "http://jeepcard.net/gportal/school_session.php?cardid="+sp.getString("user","");
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        // cardid,name,access,image
                   // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


                        for(int i=0;i<jsonArray.length();i++) {
                            String cardid = (String) jsonArray.getJSONObject(i).get("cardid").toString();
                            String name = (String) jsonArray.getJSONObject(i).get("lname").toString();
                            String access = (String) jsonArray.getJSONObject(i).get("access").toString();
                            String image = (String) jsonArray.getJSONObject(i).get("photo").toString();
                            //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                            editor.putString("cardid", cardid);
//                            editor.putString("name", name);
//                            editor.putString("access", access);
//                            editor.putString("image", image);
//
                            if(sp.contains("user")) {
                                SharedPreferences.Editor editor = sp.edit();
                                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(), sp.getString("user", "") + "\n"
//                                                + cardid + "\n"
//                                                + name + "\n"
//                                                + access + "\n"
//                                                + image + "\n"
//                                        , Toast.LENGTH_LONG).show();
                                editor.putString("name",name);
                                editor.putString("cardid",cardid);
                                editor.putString("image",image);
                                editor.putString("access",access);
                                editor.commit();
                            }


                            //editor.commit();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Reconnect..",Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(stringRequest);
            //inside session



        //note session build in ANDROID STUDIO
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user");
                    editor.remove("cardid");
                    editor.remove("name");
                    editor.remove("image");
                    editor.remove("access");
                    editor.putString("msg","Logged Out Successfully");
                    editor.commit();
                    Intent hasloggedout = new Intent(SchoolMainScreen.this , Login.class);
                    startActivity(hasloggedout);
                    finish();
                }

            }
        });


        cardviewAy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardviewAy = new Intent(SchoolMainScreen.this , ViewAy.class);
                startActivity(cardviewAy);
            }
        });

        cardviewSections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardviewSections = new Intent(SchoolMainScreen.this , ViewSections.class);
                startActivity(cardviewSections);
            }
        });

        cardviewSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardviewSubjects = new Intent(SchoolMainScreen.this , ViewSubjects.class);
                startActivity(cardviewSubjects);
            }
        });

        cardviewPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardviewPrograms = new Intent(SchoolMainScreen.this , ViewPrograms.class);
                startActivity(cardviewPrograms);
            }
        });

        cardviewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Not yet available", Toast.LENGTH_LONG).show();
                Intent cardviewStudents = new Intent(SchoolMainScreen.this , ViewStudents.class);
                startActivity(cardviewStudents);
            }
        });

        cardviewTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Not yet available", Toast.LENGTH_LONG).show();
                Intent cardviewTeachers = new Intent(SchoolMainScreen.this , ViewTeachers.class);
                startActivity(cardviewTeachers);
            }
        });

        cardviewColleges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardviewColleges = new Intent(SchoolMainScreen.this , ViewCollege.class);
//                cardviewColleges.putExtra("schoolcode", schoolcode);
                startActivity(cardviewColleges);
            }
        });

        getFunctionValley();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.school_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Profile:
                Toast.makeText(this,"VIEW CODE SELECTED",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this,"About Page",Toast.LENGTH_SHORT).show();
                Intent about = new Intent(SchoolMainScreen.this,About.class);
                startActivity(about);
                return true;
            case R.id.Logout:
                Toast.makeText(this,"VIEW CODE SELECTED",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getFunctionValley();
    }

    private void getFunctionValley() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/schoolmainscreen.php?schoolcode="+schoolcode;
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

    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button",Toast.LENGTH_LONG).show();
        return;
    }


}
