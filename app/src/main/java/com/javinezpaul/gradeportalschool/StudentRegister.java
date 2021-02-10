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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StudentRegister extends AppCompatActivity {

    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;


    EditText schoolcode,
            fname,lname,mname,schoolid,
            password;
    Button btnschoolcode,btnUploadStudent;
    ImageView imageStudent;
    Spinner collegecode,programcode,sectioncode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        schoolcode = (EditText) findViewById(R.id.schoolcode);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        mname = (EditText) findViewById(R.id.mname);
        schoolid = (EditText) findViewById(R.id.schoolid);

        btnschoolcode = (Button) findViewById(R.id.btnschoolcode);
        btnUploadStudent = (Button) findViewById(R.id.btnUploadStudent);

        imageStudent = (ImageView) findViewById(R.id.imageStudent);

        collegecode = (Spinner) findViewById(R.id.collegecode);
        programcode = (Spinner) findViewById(R.id.programcode);
        sectioncode = (Spinner) findViewById(R.id.sectioncode);

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

}