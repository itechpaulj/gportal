package com.javinezpaul.gradeportalschool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentRegister extends AppCompatActivity {

    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;
    TextView studloginbtn;
    LinearLayout min_gender,linear_collegcode,linear_programcode,linear_sectioncode;
    EditText schoolcode,
            fname,lname,mname,schoolid,
            password;
    Button btnschoolcode,btnUploadStudent,studentregister;
    ImageView imageStudent,backBtn;
    Spinner collegecode,programcode,sectioncode;

    RadioGroup grpgender;
    RadioButton rgender;

    // spinner
    ArrayList<String> spinnerList1 = new ArrayList<>();
    ArrayAdapter<String> adapter1;

    ArrayList<String> spinnerList2 = new ArrayList<>();
    ArrayAdapter<String> adapter2;

    ArrayList<String> spinnerList3 = new ArrayList<>();
    ArrayAdapter<String> adapter3;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        schoolcode = (EditText) findViewById(R.id.schoolcode);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        mname = (EditText) findViewById(R.id.mname);
        schoolid = (EditText) findViewById(R.id.schoolid);
        password = (EditText) findViewById(R.id.password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        min_gender = (LinearLayout) findViewById(R.id.min_gender);
        linear_collegcode = (LinearLayout) findViewById(R.id.linear_collegcode);
        linear_programcode = (LinearLayout) findViewById(R.id.linear_programcode);
        linear_sectioncode = (LinearLayout) findViewById(R.id.linear_sectioncode);
        btnschoolcode = (Button) findViewById(R.id.btnschoolcode);
        btnUploadStudent = (Button) findViewById(R.id.btnUploadStudent);
        backBtn = findViewById(R.id.backBtn);
        studloginbtn = findViewById(R.id.studloginbtn);

        imageStudent = (ImageView) findViewById(R.id.imageStudent);

        collegecode = (Spinner) findViewById(R.id.collegecode);
        programcode = (Spinner) findViewById(R.id.programcode);
        sectioncode = (Spinner) findViewById(R.id.sectioncode);
        studentregister = (Button) findViewById(R.id.studentregister);

        grpgender = (RadioGroup) findViewById(R.id.gender);


        // AUTO ALL CAPS CODE
        schoolcode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        //LOGINBTN
        studloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(StudentRegister.this,Login.class);
                startActivity(login);
            }
        });

        //BACK BTN
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        studentregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Processing...",Toast.LENGTH_LONG).show();
                hasRegisteredStudent();
            }

            private void hasRegisteredStudent() {

                String post_input_fname = (String) fname.getText().toString().trim();
                String post_input_lname = (String) lname.getText().toString().trim();
                String post_input_mname = (String) mname.getText().toString().trim();
                String post_input_schoolid = (String) schoolid.getText().toString().trim();
                String post_input_password = (String) password.getText().toString().trim();
                String post_input_collegecode = (String) ""+collegecode.getSelectedItem();
                String post_input_programcode = (String) ""+programcode.getSelectedItem();
                String post_input_sectioncode = (String) ""+sectioncode.getSelectedItem();
                String randonString = RandomStringUtils.randomAlphanumeric(20);
                String verifyImage = "upload/profilestudent/"+randonString+".jpeg";

                if(imageStudent.getDrawable()==null){
                    Toast.makeText(getApplicationContext(),"Please upload profile picture",Toast.LENGTH_LONG).show();
                }
                else if(post_input_fname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input First Name",Toast.LENGTH_LONG).show();
                }
                else if(post_input_lname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Last Name",Toast.LENGTH_LONG).show();
                }
                else if(post_input_mname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Middle Name",Toast.LENGTH_LONG).show();
                }
                else if(post_input_schoolid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input School Id",Toast.LENGTH_LONG).show();
                }
                else if(post_input_password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Password",Toast.LENGTH_LONG).show();
                }else if(post_input_collegecode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Select College code",Toast.LENGTH_LONG).show();

                }else if(post_input_programcode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Select Program code",Toast.LENGTH_LONG).show();
                }
                else if(post_input_sectioncode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Select Section code",Toast.LENGTH_LONG).show();
                }
                else {
                    if(grpgender.getCheckedRadioButtonId() != -1){
                        int radioId = grpgender.getCheckedRadioButtonId();
                        rgender = findViewById(radioId);
                        String gender = (String)  ""+rgender.getText();

                        RequestQueue requestQueue = Volley.newRequestQueue(StudentRegister.this);
                        String url = "http://jeepcard.net/gportal/addStudent.php";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals(verifyImage)){
                                    Intent login2 = new Intent(StudentRegister.this , Login2.class);
                                    login2.putExtra("name",post_input_lname+", "+post_input_fname+" "+post_input_mname);
                                    login2.putExtra("cardid", post_input_schoolid);
                                    login2.putExtra("image", response);
                                    login2.putExtra("access", "Student");
                                    startActivity(login2);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Network unstable, please retry\n"+error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<>();
                                String imageData = imageToString(bitmap);
                                params.put("fname",post_input_fname);
                                params.put("lname",post_input_lname);
                                params.put("mname",post_input_mname);
                                params.put("schoolid",post_input_schoolid);
                                params.put("password",post_input_password);
                                params.put("gender",gender);
                                params.put("schoolcoded",schoolcode.getText().toString());
                                params.put("collegecode",post_input_collegecode);
                                params.put("programcode",post_input_programcode);
                                params.put("sectioncode",post_input_sectioncode);
                                params.put("image",imageData);
                                params.put("random",verifyImage); // note generate random character for image
                                return params;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Choose Your Gender", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getApplicationContext(),"good",Toast.LENGTH_LONG).show();
                }



            }
        });

        btnUploadStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        StudentRegister.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });

        btnschoolcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolcode.setVisibility(View.GONE);
                btnschoolcode.setVisibility(View.GONE);


                imageStudent.setVisibility(View.VISIBLE);
                btnUploadStudent.setVisibility(View.VISIBLE);
                fname.setVisibility(View.VISIBLE);
                lname.setVisibility(View.VISIBLE);
                mname.setVisibility(View.VISIBLE);
                schoolid.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                collegecode.setVisibility(View.VISIBLE);
                programcode.setVisibility(View.VISIBLE);
                sectioncode.setVisibility(View.VISIBLE);
                studentregister.setVisibility(View.VISIBLE);
                min_gender.setVisibility(View.VISIBLE);
                linear_collegcode.setVisibility(View.VISIBLE);
                linear_sectioncode.setVisibility(View.VISIBLE);
                linear_programcode.setVisibility(View.VISIBLE);


                hasSchoolcode();

            }

            private void hasSchoolcode() {
                collegetbl();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CODE_GALLERY_REQUEST){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);

            }
            else{
                Toast.makeText(getApplicationContext(),"You do not have permission",Toast.LENGTH_LONG).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageStudent.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imagebytes = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imagebytes,Base64.DEFAULT);
        return encodeImage;
    }

    private void collegetbl(){
        // college tbl

        spinnerList1.add("[College Code]");
        adapter1 = new ArrayAdapter<>(StudentRegister.this, android.R.layout.simple_spinner_item,spinnerList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        RequestQueue requestQueue = Volley.newRequestQueue(StudentRegister.this);
        String url = "http://jeepcard.net/gportal/spinnercollegecodestudent.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    if(response.equals("FAILED")){
                        Toast.makeText(getApplicationContext(),"School has no college yet",Toast.LENGTH_LONG).show();
                        setVisibilityGone();
                    }else{
                        JSONObject jsonResult = null;
                        jsonResult = new JSONObject(response);
                        int success = jsonResult.getInt("success");
                        if(success==1){
                            spinnerList1.clear(); spinnerList1.add("[College Code]");
                            JSONArray datas = jsonResult.getJSONArray("data");
                            for(int i=0;i<datas.length();i++){
                                JSONObject data = datas.getJSONObject(i);
                                //int id = data.getInt("id");
                                String collegetbl = data.getString("collegecode");
                                String cline = collegetbl;
                                spinnerList1.add(cline);
                            }
                        }
                        programtbl();
                    }

                }catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Network unstable, please retry",Toast.LENGTH_LONG).show();
                setVisibilityGone();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("schoolcoded",schoolcode.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
        collegecode.setAdapter(adapter1);
        // college tbl
    }

    private void programtbl(){
        //-----------------

        //programtbl
        spinnerList2.add("[Program Code]");
        adapter2 = new ArrayAdapter<>(StudentRegister.this, android.R.layout.simple_spinner_item,spinnerList2);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        RequestQueue requestQueue1 = Volley.newRequestQueue(StudentRegister.this);
        String url1 = "http://jeepcard.net/gportal/spinnerprogramcodestudent.php";
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    if(response.equals("FAILED")){
                        Toast.makeText(getApplicationContext(),"School has no program yet",Toast.LENGTH_LONG).show();
                        setVisibilityGone();
                    }else{
                        // Toast.makeText(getApplicationContext(),"Verified!",Toast.LENGTH_LONG).show();
                        JSONObject jsonResult = null;
                        jsonResult = new JSONObject(response);
                        int success = jsonResult.getInt("success");
                        if(success==1){
                            spinnerList2.clear(); spinnerList2.add("[Program Code]");
                            JSONArray datas = jsonResult.getJSONArray("data");
                            for(int i=0;i<datas.length();i++){
                                JSONObject data = datas.getJSONObject(i);
                                //int id = data.getInt("id");
                                String programtbl = data.getString("programcode");
                                String pline = programtbl;
                                spinnerList2.add(pline);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"An error occured while loading school info",Toast.LENGTH_LONG).show();
                        }
                        sectiontbl();
                    }


                }catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Network unstable, please retry",Toast.LENGTH_LONG).show();
                setVisibilityGone();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("schoolcoded",schoolcode.getText().toString());
                return params;
            }
        };
        requestQueue1.add(stringRequest1);
        programcode.setAdapter(adapter2);
        //programtbl
    }

    private void sectiontbl(){
        //--------------
        // sectiontbl

        spinnerList3.add("[Section Code]");
        adapter3 = new ArrayAdapter<>(StudentRegister.this, android.R.layout.simple_spinner_item,spinnerList3);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        RequestQueue requestQueue2 = Volley.newRequestQueue(StudentRegister.this);
        String url2 = "http://jeepcard.net/gportal/spinnersectioncodestudent.php";
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {

                    if(response.equals("FAILED")){
                        Toast.makeText(getApplicationContext(),"School has no section yet",Toast.LENGTH_LONG).show();
                        setVisibilityGone();
                    }else{
                        // Toast.makeText(getApplicationContext(),"Verified!",Toast.LENGTH_LONG).show();
                        JSONObject jsonResult = null;
                        jsonResult = new JSONObject(response);
                        int success = jsonResult.getInt("success");
                        if(success==1){
                            spinnerList3.clear(); spinnerList3.add("[Section Code]");
                            JSONArray datas = jsonResult.getJSONArray("data");
                            for(int i=0;i<datas.length();i++){
                                JSONObject data = datas.getJSONObject(i);
                                //int id = data.getInt("id");
                                String programtbl = data.getString("sectioncode");
                                String pline = programtbl;
                                spinnerList3.add(pline);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"An error occured while loading school info",Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Network unstable, please retry",Toast.LENGTH_LONG).show();
                setVisibilityGone();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("schoolcoded",schoolcode.getText().toString());
                return params;
            }
        };
        requestQueue2.add(stringRequest2);
        sectioncode.setAdapter(adapter3);
        //sectiontbl
    }

    private void setVisibilityGone(){
        schoolcode.setVisibility(View.VISIBLE);
        btnschoolcode.setVisibility(View.VISIBLE);


        imageStudent.setVisibility(View.GONE);
        btnUploadStudent.setVisibility(View.GONE);
        fname.setVisibility(View.GONE);
        lname.setVisibility(View.GONE);
        mname.setVisibility(View.GONE);
        schoolid.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        collegecode.setVisibility(View.GONE);
        programcode.setVisibility(View.GONE);
        sectioncode.setVisibility(View.GONE);
        studentregister.setVisibility(View.GONE);
        min_gender.setVisibility(View.GONE);
        linear_collegcode.setVisibility(View.GONE);
        linear_sectioncode.setVisibility(View.GONE);
        linear_programcode.setVisibility(View.GONE);
    }

}