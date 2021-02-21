package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login2 extends AppCompatActivity {

    private ImageView image;
    private TextView name,cardid,access;
    private EditText password;
    private Button loggedin;
    private ToggleButton showpassword;
    private String schoolcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        cardid = (TextView) findViewById(R.id.cardid);
        access = (TextView) findViewById(R.id.access);

        password = (EditText) findViewById(R.id.password);

        loggedin = (Button) findViewById(R.id.loggedin);

        Intent intent = getIntent();
        String nameLogin = intent.getStringExtra("name");
        String cardidLogin = intent.getStringExtra("cardid");
        String urlimgweb = intent.getStringExtra("image");
        String accessSchool = intent.getStringExtra("access");
        cardid.setText(cardidLogin);
        name.setText(nameLogin);
        access.setText(accessSchool);
        showpassword = findViewById(R.id.showpassword);

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;

        // log in show passwword toggle button
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpassword.isChecked()){
                    Toast.makeText(getApplicationContext(),"Show Password",Toast.LENGTH_SHORT).show();
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hide Password",Toast.LENGTH_SHORT).show();
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });



        String addressView = "http://jeepcard.net/gportal/"+urlimgweb;
        imageResult(addressView);// public string class


        //btnlogin
        //---------------

        loggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(Login2.this);
                String url ="http://jeepcard.net/gportal/loggedin2.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    /// image from online
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if(response.equals("School")){
                            //note session build in ANDROID STUDIO
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",cardidLogin);
                            editor.putString("schoolcode",schoolcode);
                            editor.commit();
                            Intent school = new Intent(Login2.this,SchoolMainScreen.class);
                            startActivity(school);
                            finish();
                            //note session build in ANDROID STUDIO
                        }
                        else if(response.equals("Teacher")){
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",cardidLogin);
                            editor.putString("schoolcode",schoolcode);
                            editor.commit();
                            Intent school = new Intent(Login2.this,TeacherMainScreen.class);
                            startActivity(school);
                            finish();
                        }
                        else if(response.equals("Student")){
                            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user",cardidLogin);
                            editor.putString("schoolcode",schoolcode);
                            editor.commit();
                            Intent school = new Intent(Login2.this,StudentMianScreen.class);
                            startActivity(school);
                            finish();
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
                        Map<String,String> params = new HashMap<>();
                        //params.put("name",nameLogin);
                        params.put("cardid",cardidLogin);
                        params.put("password",password.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);


            }
        });
    }

    public String imageResult(String string){
        // image from online
        RequestQueue requestQueueImage = Volley.newRequestQueue(Login2.this);
        //String addressView = "http://192.168.43.177/gportal/upload/logoschool/2115752840_1613053725.jpeg";
        ImageRequest imageRequest = new ImageRequest(string, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueueImage.add(imageRequest);
        // image from online




        //
        return  string;
    }
}