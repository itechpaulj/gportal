package com.javinezpaul.gradeportalschool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
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
    TextView setCollegeName;
    ImageView imageTeacher;
    RadioButton radioButton;
    RadioGroup radioGroup;
    LinearLayout min_gender;
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
        address = (EditText) findViewById(R.id.address);
        password = (EditText) findViewById(R.id.password);
        spinnercollegename = (Spinner) findViewById(R.id.spinnercollegename);
        radioGroup = (RadioGroup) findViewById(R.id.gender);
        min_gender = (LinearLayout) findViewById(R.id.min_gender);




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
                //{post_input_fname} {post_input_lname} {post_input_mname} {post_input_empid} {post_input_spinner} {post_input_schoolcode} {post_input_address} {post_input_password}

                String post_input_fname,post_input_lname,post_input_mname,
                        post_input_empid, post_input_spinner,post_input_schoolcode,post_input_address,post_input_password;
                post_input_fname = (String) fnameteacher.getText().toString().trim();
                post_input_lname = (String) lnameteacher.getText().toString().trim();
                post_input_mname = (String) mnameteacher.getText().toString().trim();
                post_input_empid = (String) empteacher.getText().toString().trim();
                post_input_schoolcode = (String) schoolcode.getText().toString().trim();
                post_input_spinner = (String) ""+spinnercollegename.getSelectedItem();
                post_input_address = (String) address.getText().toString().trim();
                post_input_password = (String) password.getText().toString().trim();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String gender = (String)  ""+radioButton.getText();
                //setCollegeName.setText(post_input_spinner);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterTeacher.this);
                String url = "http://jeepcard.net/gportal/addTeacher.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Please Fill in the Form",Toast.LENGTH_LONG).show();
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
                        params.put("address",post_input_address);
                        params.put("schoolcode",post_input_schoolcode);
                        params.put("empTeacher",post_input_empid);
                        params.put("password",post_input_password);
                        params.put("spinner",post_input_spinner);
                        params.put("image",imageData);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        //school code
        btnschoolcode = (Button) findViewById(R.id.btnschoolcode);
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
                address.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
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
                       if(response.equals("FAILED")){
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
                           address.setVisibility(View.GONE);
                           password.setVisibility(View.GONE);
                       }

                            try {
                                Toast.makeText(getApplicationContext(),"Verified!",Toast.LENGTH_LONG).show();
                                jsonResult = new JSONObject(response);
                                int success = jsonResult.getInt("success");
                                spinnerList.add("[College Name]");
                                if(success==1){
                                    JSONArray datas = jsonResult.getJSONArray("data");
                                    for(int i=0;i<datas.length();i++){
                                        JSONObject data = datas.getJSONObject(i);
                                        //int id = data.getInt("id");
                                        String collegename = data.getString("collegename");
                                        String line = collegename;

                                        spinnerList.add(line);
                                        adapter1 = new ArrayAdapter<>(RegisterTeacher.this, android.R.layout.simple_spinner_item,spinnerList);
                                        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                        spinnercollegename.setAdapter(adapter1);
                                        spinnercollegename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                //Toast.makeText(RegisterTeacher.this,""+spinnercollegename.getSelectedItem(),Toast.LENGTH_LONG).show();

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }


                                }
                                else{
                                    spinnerList.add("[ERROR]");
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
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
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
        String encodeImage = Base64.encodeToString(imagebytes,Base64.DEFAULT);
        return encodeImage;
    }


    //{end} imageView result
}