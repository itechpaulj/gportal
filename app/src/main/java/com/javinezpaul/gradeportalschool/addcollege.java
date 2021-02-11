package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class addcollege extends AppCompatActivity {
    private String schoolcode;
    private String collegecode;
    private String collegename;

    EditText collegeCodeTxt, collegeNameTxt;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcollege);

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;

        collegeCodeTxt=findViewById(R.id.collegeCodeTxt);
        collegeNameTxt=findViewById(R.id.collegeNameTxt);
        addBtn=findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!collegeCodeTxt.getText().toString().equals("") && !collegeNameTxt.getText().toString().equals("")  ){
                    collegecode=collegeCodeTxt.getText().toString();
                    collegename=collegeNameTxt.getText().toString();
                    addCollege(collegecode, collegename, schoolcode);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(addcollege.this).create();
                    alertDialog.setTitle("Warning");
                    alertDialog.setMessage("Please supply the form");//Message to be shown
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    private void addCollege(String collegecode, String collegename, String schoolcode) {
        RequestQueue queueu = Volley.newRequestQueue(this);
        String url = "http://jeepcard.net/gportal/addcollege.php?schoolcode="+schoolcode+"&collegecode="+collegecode+"&collegename"+collegename;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                collegeCounterTextView.setText("Response Get: "+response);
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(addcollege.this).create();
                alertDialog.setTitle("Message");
                alertDialog.setMessage(response);//Message to be shown
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } //onResponse
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Reconnecting", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "An error occured\n" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });//Stringrequest
        queueu.add(stringRequest);
    }
}