package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class TeacherMainScreen extends AppCompatActivity {
    private Button logout,newcode,clipboard_copy;
    private TextView teacheruser;
    private TextView ay,section,subject,programcode,teachercode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);

        teacheruser = (TextView) findViewById(R.id.teacheruser);
        logout = (Button) findViewById(R.id.logout);

        // details teacher
        ay = (TextView) findViewById(R.id.ay);
        section = (TextView) findViewById(R.id.section);
        subject = (TextView) findViewById(R.id.subject);
        programcode= (TextView) findViewById(R.id.programcode);
        teachercode = (TextView) findViewById(R.id.teachercode);
        newcode = (Button) findViewById(R.id.newcode);
        clipboard_copy = (Button) findViewById(R.id.clipboard_copy);
        // details teacher

        // session teacher user
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("user")){
            SharedPreferences.Editor editor = sp.edit();
            teacheruser.setText(sp.getString("user",""));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                if(sp.contains("user")){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user");
                    editor.putString("msg","Logged Out Successfully");
                    editor.commit();
                    Intent hasloggedout = new Intent(TeacherMainScreen.this , Login.class);
                    startActivity(hasloggedout);
                    finish();
                }

            }
        });
        // session teacher user

        newcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeacherMainScreen.this,newcode.class);
                startActivity(i);
            }
        });



        clipboard_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_LONG).show();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView",teachercode.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"Copied.",Toast.LENGTH_LONG).show();
            }
        });


        // has shown data teacher main screen

        RequestQueue requestQueue = Volley.newRequestQueue(TeacherMainScreen.this);
        String url = "http://jeepcard.net/gportal/teachermainscreen.php?valid=true";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        String teacherscodeget = jsonArray.getJSONObject(i).get("teacherscode").toString();
                        String ayget = jsonArray.getJSONObject(i).get("ay").toString();
                        String subjectcodeget = jsonArray.getJSONObject(i).get("subjectcode").toString();
                        String sectioncodeget = jsonArray.getJSONObject(i).get("sectioncode").toString();
                        String programcodeget = jsonArray.getJSONObject(i).get("programcode").toString();


                        ay.setText(ayget);
                        subject.setText(subjectcodeget);
                        section.setText(sectioncodeget);
                        programcode.setText(programcodeget);
                        teachercode.setText(teacherscodeget);
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


        // has shown data teacher main screen

    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Press logout button", Toast.LENGTH_LONG).show();
        return;

    }
}