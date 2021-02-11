package com.javinezpaul.gradeportalschool;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class School extends AppCompatActivity {

/*
Create all id element like EditText ,button
make Toast for debugging result if success
*/
    //--
    //setGlobal default URL

    //setGlobal default URL


    /*{start}Image Viewdeclation for variable*/
    ImageView imageview;
    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;
    Button hasUploadSchoolLogo;
    /*{end}ImageVIew*/

    //---

    /*{start}EditText declaration variable*/
    EditText hasinputSchoolName,hasinputAddress, hasinputEmail,hasinputPassword;
    /*{end}EditText declaration variable*/

    //---

    /*{start}Register Declaration button*/
    Button hasRegisterSchool;
    /*{end}Register Declaration button*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        hasUploadSchoolLogo = (Button) findViewById(R.id.hasUploadSchoolLogo);
        imageview = (ImageView) findViewById(R.id.imageView);
        hasRegisterSchool = (Button) findViewById(R.id.hasRegisterSchool);
        hasinputSchoolName = (EditText) findViewById(R.id.hasinputSchoolName);
        hasinputAddress = (EditText) findViewById(R.id.hasinputAddress);
        hasinputEmail = (EditText) findViewById(R.id.hasinputEmail);
        hasinputPassword = (EditText) findViewById(R.id.hasinputPassword);
        //{start}hasUploadSchoolLogo Button
        hasUploadSchoolLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        School.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });
        //{end}hasUploadSchoolLogo Button
        //{start}Register Button setOnclick
        hasRegisterSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hasProcessRegisterSchool();
            }

            private void hasProcessRegisterSchool() {
                String regExpression = "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$";
                String post_input_school_name = (String) hasinputSchoolName.getText().toString();
                String post_input_school_address = (String) hasinputAddress.getText().toString();
                String post_input_school_email = (String) hasinputEmail.getText().toString();
                String post_input_school_password = (String) hasinputPassword.getText().toString();
                if(post_input_school_name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input School Name",Toast.LENGTH_LONG).show();
                }
                else if(post_input_school_address.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Address",Toast.LENGTH_LONG).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(post_input_school_email).matches() && !post_input_school_email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Email",Toast.LENGTH_LONG).show();
                }
                else if(post_input_school_password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Your Password",Toast.LENGTH_LONG).show();
                }
                else{
                    RequestQueue requestQueue = Volley.newRequestQueue(School.this);
                    String localUrl = "http://jeepcard.net/gportal/addSchool.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, localUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                          // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            Intent login2 = new Intent(School.this , Login2.class);
                            login2.putExtra("name", post_input_school_name);
                            login2.putExtra("cardid", post_input_school_email);
                            login2.putExtra("image", response);
                            login2.putExtra("access", "School");
                            startActivity(login2);
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
                            //note $_POST Request params.put($_POST,string)
                            String imageData = imageToString(bitmap);

                            params.put("schoolname",post_input_school_name);
                            params.put("schooladdress",post_input_school_address);
                            params.put("schoolemail",post_input_school_email);
                            params.put("schoolpassword",post_input_school_password);
                            params.put("image",imageData);
                            return params;

                        }
                    };
                    requestQueue.add(stringRequest);
                }

            }//hasProcessRegisterSchool
        });
        //{end}Register Button setOnclick

    }

    //{start} worked load for image and button Upload Gallery
    //---------
    //{start}request perminsion for gallery
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
    //{end}request perminsion for gallery

    //{start} imageView result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageview.setImageBitmap(bitmap);

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

    //-----------
    //{end}worked load for image and button Upload Gallery

    //-----------

}
