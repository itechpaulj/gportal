package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView logoutText ;
     ToggleButton showpassword;
     TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText hasinputeachuser,hasinputpassword;
        Button hasbtnloggedin;
        hasinputeachuser = (EditText) findViewById(R.id.hasinputeachuser);
        hasinputpassword = (EditText) findViewById(R.id.hasinputpassword);
        hasbtnloggedin = (Button) findViewById(R.id.hasbtnloggedin);
        showpassword = findViewById(R.id.showpassword);

        registerBtn=findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, MainActivity.class);
                startActivity(register);
            }
        });


        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpassword.isChecked()){
                    Toast.makeText(getApplicationContext(),"Show Password",Toast.LENGTH_SHORT).show();
                    hasinputpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hide Password",Toast.LENGTH_SHORT).show();
                    hasinputpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });



        checklogout(logoutText);



        hasbtnloggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasprocessloggedin();
            }

            private void hasprocessloggedin() {
               // Toast.makeText(getApplicationContext(),"Verified!",Toast.LENGTH_LONG).show();

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                String url = "http://jeepcard.net/gportal/loggedin.php";
                StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if(response.equals("School")){
                            //Toast.makeText(getApplicationContext(),"This is a School",Toast.LENGTH_LONG).show();
                            //note session build in ANDROID STUDIO
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",hasinputeachuser.getText().toString());
                            editor.commit();
                            Intent school = new Intent(Login.this,SchoolMainScreen.class);
                            startActivity(school);
                            finish();
                            //note session build in ANDROID STUDIO
                        }
                        else if(response.equals("Teacher")){
                            Intent teacher = new Intent(Login.this,TeacherMainScreen.class);
                            startActivity(teacher);
                            //Toast.makeText(getApplicationContext(),"This is a Teacher",Toast.LENGTH_LONG).show();
                            //note session build in ANDROID STUDIO
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",hasinputeachuser.getText().toString());
                            editor.commit();
                            finish();
                            //note session build in ANDROID STUDIO
                        }
                        else if(response.equals("Student")){
                            Intent student = new Intent(Login.this,StudentMianScreen.class);
                            startActivity(student);
                            //note session build in ANDROID STUDIO
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",hasinputeachuser.getText().toString());
                            editor.commit();
                            finish();
                            //note session build in ANDROID STUDIO
                            //Toast.makeText(getApplicationContext(),"This is a Student",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params =  new HashMap<>();
                        params.put("user",hasinputeachuser.getText().toString());
                        params.put("pass",hasinputpassword.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }

    public void checklogout(View view){
        logoutText = (TextView) findViewById(R.id.logout);

        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);

        if(sp.contains("msg")) {
            logoutText.setText(sp.getString("msg",""));
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("msg");
            editor.commit();
        }
    }
}