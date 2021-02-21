package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class addsection extends AppCompatActivity{

    //global var
    private Spinner hasSpinner;
    private String hasprogramcode,schoolcode;
    private EditText hasSectioncode;
    private Button hasBtnaddsection;
    //global var

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsection);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        hasBtnaddsection = (Button) findViewById(R.id.add);
        hasSpinner = (Spinner) findViewById(R.id.spinnerprogramcode);
        hasSectioncode = (EditText) findViewById(R.id.sectioncode);

        SchoolMainScreen mainClass = new SchoolMainScreen();
        schoolcode=mainClass.schoolcode;


        RequestQueue requestQueue = Volley.newRequestQueue(addsection.this);
        String url = "http://jeepcard.net/gportal/spinnerSection.php?schoolcode="+schoolcode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                   // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                arrayList.add("[Program Code]");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");

                        if(success==1){
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String hasgetprogramcode = data.getString("progcodeid");
                                String getprogramcodeid = hasgetprogramcode;
                                arrayList.add(getprogramcodeid);
                                arrayAdapter = new ArrayAdapter<>(addsection.this, android.R.layout.simple_spinner_item,arrayList);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                hasSpinner.setAdapter(arrayAdapter);
                            }
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong\n"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);


//        String[] sec = {"Section Code","Section Code","Section Code"};
//        ArrayList<String> Sec = new ArrayList<>(Arrays.asList(sec));
//        ArrayAdapter<String> arrayAdaptersec = new ArrayAdapter<>(this,R.layout.style_spinner,Sec);
//        spinnerSection.setAdapter(arrayAdaptersec);

        //has no value is default
      //  spinnerList1.add("[GENERATE SCHOOL CODE]");
        //adapter1 = new ArrayAdapter<>(addsection.this, android.R.layout.simple_spinner_item,spinnerList1);
       // adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
       // defaultspinner.setAdapter(adapter1);
        //has no value is default


        hasBtnaddsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),schoolcode,Toast.LENGTH_LONG).show();
                String get_input_spinner = ""+hasSpinner.getSelectedItem();
                String[] getFirstIndex = get_input_spinner.split("-");

                if(hasSectioncode.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Input Section Code",Toast.LENGTH_LONG).show();
                }
                else if(get_input_spinner.equals("[Program Code]")){
                    Toast.makeText(getApplicationContext(),"Please Select program code",Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(getApplicationContext(),getFirstIndex[0],Toast.LENGTH_LONG).show();
                    RequestQueue requestQueue1 = Volley.newRequestQueue(addsection.this);
                    String url1 = "http://jeepcard.net/gportal/addSection.php?schoolcode="+schoolcode+"&inputSection="+hasSectioncode.getText().toString()+"&programid="+getFirstIndex[0];
                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                if(response.equals("Success")) {
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Something went wrong\n"+error.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                    requestQueue1.add(stringRequest1);
                }
            }
        });










    }
}