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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


public class RegisterTeacher extends AppCompatActivity {
    Button btnschoolcode,
            uploadBtn,nextBtn;
    ImageButton backBtn;
    TextView setCollegeName,TeacherLoginbtn;
    ImageView imageTeacher;
    RadioButton radioButton;
    RadioGroup radioGroup;
    LinearLayout min_gender,linear_spinnercollegename;
    EditText schoolcode,fnameteacher,lnameteacher,mnameteacher,empteacher,address,password;
    Spinner spinnercollegename;
    ArrayList<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> adapter1;

    //image  View
    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;

    // image View
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        schoolcode = (EditText) findViewById(R.id.schoolcode);
        imageTeacher = (ImageView) findViewById(R.id.imageTeacher);
        fnameteacher = (EditText) findViewById(R.id.fnameteacher);
        lnameteacher = (EditText) findViewById(R.id.lnameteacher);
        mnameteacher = (EditText) findViewById(R.id.mnameteacher);
        empteacher = (EditText) findViewById(R.id.empteacher);
        //address = (EditText) findViewById(R.id.address);
        password = (EditText) findViewById(R.id.password);
        spinnercollegename = (Spinner) findViewById(R.id.spinnercollegename);
        radioGroup = (RadioGroup) findViewById(R.id.gender);
        min_gender = (LinearLayout) findViewById(R.id.min_gender);
        linear_spinnercollegename = (LinearLayout) findViewById(R.id.linear_spinnercollegename);
        backBtn = findViewById(R.id.backBtn);
        TeacherLoginbtn = findViewById(R.id.TeacherLoginbtn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //LOGIN BTN
        TeacherLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterTeacher.this,Login.class);
                startActivity(login);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //school code
        btnschoolcode = (Button) findViewById(R.id.btnschoolcode);

        //AUTO CAPS CODE
        schoolcode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RegisterTeacher.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );


            }
        });



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextBtn.setVisibility(View.INVISIBLE);

                //{post_input_fname} {post_input_lname} {post_input_mname} {post_input_empid} {post_input_spinner} {post_input_schoolcode} {post_input_address} {post_input_password}
                Toast.makeText(getApplicationContext(),"Processing...",Toast.LENGTH_LONG).show();
                String post_input_fname,post_input_lname,post_input_mname,
                        post_input_empid, post_input_spinner,post_input_schoolcode,post_input_address,post_input_password;
                post_input_fname = (String) fnameteacher.getText().toString().trim();
                post_input_lname = (String) lnameteacher.getText().toString().trim();
                post_input_mname = (String) mnameteacher.getText().toString().trim();
                post_input_empid = (String) empteacher.getText().toString().trim();
                post_input_schoolcode = (String) schoolcode.getText().toString().trim();
                post_input_spinner = (String) ""+spinnercollegename.getSelectedItem();
                //post_input_address = (String) address.getText().toString().trim();
                post_input_password = (String) password.getText().toString().trim();
                String randonString = RandomStringUtils.randomAlphanumeric(20);
                String verifyImage = "upload/profileteacher/"+randonString+".jpeg";
                if(post_input_fname.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Enter First Name",Toast.LENGTH_LONG).show();
                }else if(imageTeacher.getDrawable() == null){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please select a Profile picture",Toast.LENGTH_LONG).show();
                }else if(post_input_lname.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Enter Last Name",Toast.LENGTH_LONG).show();
                }
                else if(post_input_mname.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Enter Middle Name",Toast.LENGTH_LONG).show();
                }
                //else if(post_input_address.isEmpty()){
                    //Toast.makeText(getApplicationContext(),"Please Enter Your Address",Toast.LENGTH_LONG).show();
                //}
                else if(post_input_empid.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Enter Employee ID",Toast.LENGTH_LONG).show();
                }
                else if(post_input_spinner.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Select College Code",Toast.LENGTH_LONG).show();
                }
                else if(post_input_password.isEmpty()){
                    nextBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please Enter Your Password",Toast.LENGTH_LONG).show();
                }
                else{
                    if(radioGroup.getCheckedRadioButtonId() != -1){
                            int radioId = radioGroup.getCheckedRadioButtonId();
                            radioButton = findViewById(radioId);
                            String gender = (String)  ""+radioButton.getText();
                        RequestQueue requestQueue = Volley.newRequestQueue(RegisterTeacher.this);
                        String url = "http://jeepcard.net/gportal/addTeacher.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                    if(response.equals(verifyImage)){
                                        Intent login2 = new Intent(RegisterTeacher.this , Login2.class);
                                        login2.putExtra("name",post_input_lname+", "+post_input_fname+" "+post_input_mname);
                                        login2.putExtra("cardid", post_input_empid);
                                        login2.putExtra("image", response);
                                        login2.putExtra("access", "Teacher");
                                        startActivity(login2);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                    }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                nextBtn.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"Network unstable, please retry",Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<>();

                            String imageData = imageToString(bitmap);

                            params.put("fname",post_input_fname);
                            params.put("lname",post_input_lname);
                            params.put("mname",post_input_mname);
                            params.put("gender",gender);
                            //params.put("address",post_input_address);
                            params.put("schoolcode",post_input_schoolcode);
                            params.put("empTeacher",post_input_empid);
                            params.put("password",post_input_password);
                            params.put("spinner",post_input_spinner);
                            params.put("image",imageData);
                            params.put("random",verifyImage); // note generate random character for image
                                return params;
                            }
                        };
                        requestQueue.add(stringRequest);

                    }else{
                        Toast.makeText(getApplicationContext(), "Choose Your Gender", Toast.LENGTH_SHORT).show();
                    }

                }






            }
        });

        btnschoolcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    hide {btnschoolcode} and {btnschoolcode}
                 */
                schoolcode.setVisibility(View.GONE);
                btnschoolcode.setVisibility(View.GONE);
                /*
                  hide {btnschoolcode} and {btnschoolcode}
                */

                //--------------

                uploadBtn.setVisibility(View.VISIBLE);
                imageTeacher.setVisibility(View.VISIBLE);
                fnameteacher.setVisibility(View.VISIBLE);
                lnameteacher.setVisibility(View.VISIBLE);
                lnameteacher.setVisibility(View.VISIBLE);
                mnameteacher.setVisibility(View.VISIBLE);
                empteacher.setVisibility(View.VISIBLE);
                spinnercollegename.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                min_gender.setVisibility(View.VISIBLE);
               // address.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                linear_spinnercollegename.setVisibility(View.VISIBLE);
                /*
                * Executed the code
                *
                * */
                    hasSppinerShowData();
                /*
                 * Executed the code
                 *
                 * */

            }

            private void hasSppinerShowData() {
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterTeacher.this);
                String url = "http://jeepcard.net/gportal/spinnerTeacher.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       JSONObject jsonResult = null;
                       if(response.equals("School doesn't have college yet")){
                           Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                           schoolcode.setVisibility(View.VISIBLE);
                           btnschoolcode.setVisibility(View.VISIBLE);
                           uploadBtn.setVisibility(View.GONE);
                           imageTeacher.setVisibility(View.GONE);
                           fnameteacher.setVisibility(View.GONE);
                           lnameteacher.setVisibility(View.GONE);
                           mnameteacher.setVisibility(View.GONE);
                           empteacher.setVisibility(View.GONE);
                           spinnercollegename.setVisibility(View.GONE);
                           nextBtn.setVisibility(View.GONE);
                           min_gender.setVisibility(View.GONE);
                           //address.setVisibility(View.GONE);
                           password.setVisibility(View.GONE);
                           linear_spinnercollegename.setVisibility(View.GONE);

                       }


                            try {
                               // Toast.makeText(getApplicationContext(),"Verified!",Toast.LENGTH_LONG).show();
                                jsonResult = new JSONObject(response);
                                int success = jsonResult.getInt("success");
                                spinnerList.add("[College Code]");
                                if(success==1){
                                    JSONArray datas = jsonResult.getJSONArray("data");
                                    for(int i=0;i<datas.length();i++){
                                        JSONObject data = datas.getJSONObject(i);
                                        //int id = data.getInt("id");
                                        String collegename = data.getString("collegecode");
                                        String line = collegename;
                                        spinnerList.add(line);
                                        adapter1 = new ArrayAdapter<>(RegisterTeacher.this, android.R.layout.simple_spinner_item,spinnerList);
                                        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                        spinnercollegename.setAdapter(adapter1);
                                    }

                                }
                                else{
                                    spinnerList.add("[No data found]");
                                }

                            }catch (JSONException e) {

                                e.printStackTrace();
                            }

                            //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                       // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        schoolcode.setVisibility(View.VISIBLE);
                        btnschoolcode.setVisibility(View.VISIBLE);
                        uploadBtn.setVisibility(View.GONE);
                        imageTeacher.setVisibility(View.GONE);
                        fnameteacher.setVisibility(View.GONE);
                        lnameteacher.setVisibility(View.GONE);
                        mnameteacher.setVisibility(View.GONE);
                        empteacher.setVisibility(View.GONE);
                        spinnercollegename.setVisibility(View.GONE);
                        nextBtn.setVisibility(View.GONE);
                        min_gender.setVisibility(View.GONE);
                        //address.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        linear_spinnercollegename.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Something went wrong!\n"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();


                       // String imageData = imageToString(bitmap);
                        params.put("spinnerTeacher",schoolcode.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        //school code

        //-----------


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
                imageTeacher.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // request imageString
    public String imageToString(Bitmap bitmap){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imagebytes = outputStream.toByteArray();
        String encodeImage = (String) Base64.encodeToString(imagebytes,Base64.DEFAULT);
        return encodeImage;

    }


    //{end} imageView result
}