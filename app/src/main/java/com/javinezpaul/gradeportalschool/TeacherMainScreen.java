package com.javinezpaul.gradeportalschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class TeacherMainScreen extends AppCompatActivity {
    private Button logout,newcode;
    private TextView teacheruser;

    private RecyclerView teachersCodeRecView;
    private TeachersCodeRecViewAdapter adapter;

    private String schoolcode, userid;

    ArrayList<TeachersCode> teachersCodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        schoolcode= (sp.getString("schoolcode",""));
        userid= (sp.getString("user",""));
        if(sp.contains("schoolcode")){
            schoolcode= (sp.getString("schoolcode",""));
        }
        if(sp.contains("user")){
            userid= (sp.getString("user",""));
        }

        Toast.makeText(getApplicationContext(),"schoolcode: " + schoolcode,Toast.LENGTH_LONG).show();

        teacheruser = (TextView) findViewById(R.id.teacheruser);
        logout = (Button) findViewById(R.id.logout);
        newcode = (Button) findViewById(R.id.newcode);

        //TODO: insert lines 48 - 67 in volley request function.
        // RecView Adapter and initialization
        teachersCodeRecView = findViewById(R.id.teachersCodeRecView);
        adapter = new TeachersCodeRecViewAdapter(TeacherMainScreen.this);
        teachersCodeRecView.setAdapter(adapter);
        teachersCodeRecView.setLayoutManager(new LinearLayoutManager(TeacherMainScreen.this));

        teachersCodes.clear();

        showTeacherscode();

        //adding data in recyclerview
//        teachersCodes.clear();
//        teachersCodes.add(new TeachersCode("1", "TE1234", "1", "1", "1", "1",
//                        "1", "2021-02-20", "IIT", "Industrial and Information Technology", "ACT",
//                "Associate in Computer Technology", "IT1A", "GENSCI", "General Science 101",
//                "2020", "2021", "1st", "1st"));
//        teachersCodes.add(new TeachersCode("1", "TE4321", "1", "1", "1", "1",
//                "1", "2021-02-20", "IIT", "Industrial and Information Technology", "ACT",
//                "Associate in Computer Technology", "IT1A", "GENSCI", "Physical Science",
//                "2020", "2021", "2nd", "2nd"));
//
//        adapter.setTeachersCodes(teachersCodes);









//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
//                if(sp.contains("user")){
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.remove("user");
//                    editor.remove("cardid");
//                    editor.remove("name");
//                    editor.remove("image");
//                    editor.remove("access");
//                    editor.putString("msg","Logged Out Successfully");
//                    editor.commit();
//                    Intent hasloggedout = new Intent(TeacherMainScreen.this , Login.class);
//                    startActivity(hasloggedout);
//                    finish();
//                }
//
//            }
//        });
        // session teacher user

        newcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherMainScreen.this,newcode.class);
                startActivity(i);
            }
        });




        // has shown data teacher main screen

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showTeacherscode();
    }

    //3 dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teacher_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Profile:
                getProfile();
                return true;
//            case R.id.Students:
//                Toast.makeText(this,"VIEW CODE SELECTED",Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.about:
                Toast.makeText(this,"About Page",Toast.LENGTH_SHORT).show();
                Intent about = new Intent(TeacherMainScreen.this,About.class);
                startActivity(about);
                return true;
            case R.id.Logout:
               // Toast.makeText(this,"VIEW CODE SELECTED",Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user");
                    editor.remove("cardid");
                    editor.remove("name");
                    editor.remove("image");
                    editor.remove("access");
                    editor.remove("userid");
                    editor.clear();
                    editor.putString("msg","Logged Out Successfully");
                    editor.commit();
                    Intent hasloggedout = new Intent(TeacherMainScreen.this , Login.class);
                    startActivity(hasloggedout);
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed(){
        //Toast.makeText(getApplicationContext(),"Press logout button", Toast.LENGTH_LONG).show();
        return;

    }

    private void getProfile() {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/profile.php?cardid="+userid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray JA= new JSONArray(response);

                    AlertDialog.Builder builder = new AlertDialog.Builder(TeacherMainScreen.this);
                    builder.setTitle("Profile");
                    builder.setMessage("\nCollege: " + JA.getJSONObject(0).get("collegecode").toString() + "\n" +
                            "Employee ID: " + JA.getJSONObject(0).get("cardid").toString() + "\n\n" +
                            JA.getJSONObject(0).get("lname").toString() + ", " +
                            JA.getJSONObject(0).get("fname").toString() + " " +
                            JA.getJSONObject(0).get("mname").toString() + "\n" +
                            JA.getJSONObject(0).get("gender").toString()
                    );
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Network unstable, please retry", Toast.LENGTH_LONG).show();
            }
        });//Stringrequest
        queueu.add(stringRequest);
    }

    public void showTeacherscode(){
        // session teacher user
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        // volley data
        String getcardidTeacher = (String) sp.getString("user","");
        RequestQueue requestQueue = Volley.newRequestQueue(TeacherMainScreen.this);
        String url = "http://jeepcard.net/gportal/teachermainscreen.php?cardid="+getcardidTeacher;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                teachersCodes.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        String teacherscodeid=jsonArray.getJSONObject(i).get("teacherscodeid").toString();
                        String teacherscode=jsonArray.getJSONObject(i).get("teacherscode").toString();
                        String collegeid=jsonArray.getJSONObject(i).get("collegeid").toString();
                        String programid=jsonArray.getJSONObject(i).get("programid").toString();
                        String sectionid=jsonArray.getJSONObject(i).get("sectionid").toString();
                        String subjectid=jsonArray.getJSONObject(i).get("subjectid").toString();
                        String ayid=jsonArray.getJSONObject(i).get("ayid").toString();
                        String userid=jsonArray.getJSONObject(i).get("userid").toString();
                        String date=jsonArray.getJSONObject(i).get("date").toString();
                        String status=jsonArray.getJSONObject(i).get("status").toString();
                        String collegecode=jsonArray.getJSONObject(i).get("collegecode").toString();
                        String programcode=jsonArray.getJSONObject(i).get("programcode").toString();
                        String sectioncode=jsonArray.getJSONObject(i).get("sectioncode").toString();
                        String subjectcode=jsonArray.getJSONObject(i).get("subjectcode").toString();
                        String collegename=jsonArray.getJSONObject(i).get("collegename").toString();
                        String programname=jsonArray.getJSONObject(i).get("programname").toString();
                        String subjecttitle=jsonArray.getJSONObject(i).get("subjecttitle").toString();
                        String ayearlevel=jsonArray.getJSONObject(i).get("ayearlevel").toString();
                        String aysem=jsonArray.getJSONObject(i).get("aysem").toString();
                        String ayear1=jsonArray.getJSONObject(i).get("ayear1").toString();
                        String ayear2=jsonArray.getJSONObject(i).get("ayear2").toString();
                        String fullname=jsonArray.getJSONObject(i).get("fullname").toString();
                        String access=jsonArray.getJSONObject(i).get("access").toString();
                        String photo=jsonArray.getJSONObject(i).get("photo").toString();
                        String cardid=jsonArray.getJSONObject(i).get("cardid").toString();

                        teachersCodes.add(new TeachersCode(teacherscodeid, teacherscode, collegeid, programid, sectionid, subjectid,
                                ayid, date, collegecode, collegename, programcode,
                                programname, sectioncode, subjectcode, subjecttitle,
                                ayear1, ayear2, ayearlevel, aysem));
                        adapter.setTeachersCodes(teachersCodes);

                        if(sp.contains("user")){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name",fullname);
                            editor.putString("cardid",cardid);
                            editor.putString("image",photo);
                            editor.putString("access",access);
                            editor.putString("userid",userid);
                            editor.commit();
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



        //volley data
    }
}