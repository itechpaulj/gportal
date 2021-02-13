package com.javinezpaul.gradeportalschool;

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
    //defaultspinner

    //EditText input your section
    EditText sectioninput;
    //EditText input your section

    //textview for input section
    TextView textsecinput;
    //textview for input section

    //---
    //textview for school code
    TextView textschoolcode;
    //textview for school code

    /*NO VALUE SPINNER*/
    Spinner spinnerSection1;
    ArrayAdapter <String> adapter1;
    ArrayList<String> spinnerList1 = new ArrayList<>();
    /*NO VALUE SPINNER*/

    //make generate the code first
    Button generateCode;
//-------------

    /*HAS DISPLAY OUTPUT*/
    Spinner spinnerSection;
    ArrayAdapter <String> adapter;
    ArrayList<String> spinnerList = new ArrayList<>();
    /*HAS DISPLAY OUTPUT*/

    //show {ADD} button
    Button add;
    //show {ADD} button

    //cardview has no value
    CardView cardviewdefault;
    //

    //--
    //get a list school code
    CardView cardviewSchoolcode;
    //get a list school code

    private String programid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsection);

        //---
        cardviewdefault = (CardView) findViewById(R.id.cardviewdefault);
        cardviewSchoolcode = (CardView) findViewById(R.id.cardviewSchoolcode);
        //---------

        //{ADD} button
        add = (Button) findViewById(R.id.add);
        //{ADD} button

        //-------
        //EditText for school code
        EditText sectioncode = (EditText) findViewById(R.id.sectioncode);
        //EditText for school code
        //-------------

        //----
        sectioncode.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        //----

        // has show legit school code
        Spinner spinnerSection =  (Spinner)findViewById(R.id.spinnerSection);
        ///-----------------------------

        //has shown has no school code yet
        Spinner defaultspinner = (Spinner) findViewById(R.id.defaultspinner);
        Button generateCode = (Button) findViewById(R.id.generateCode);
        //

        //-----------

        // show text result section code
        textschoolcode = (TextView) findViewById(R.id.textschoolcode);

        //----
        //input your section sample IT4a
        textsecinput = (TextView) findViewById(R.id.textsecinput);
        // input your section sample IT4a

        //---
        sectioninput = (EditText) findViewById(R.id.sectioninput);
        //



//        String[] sec = {"Section Code","Section Code","Section Code"};
//        ArrayList<String> Sec = new ArrayList<>(Arrays.asList(sec));
//        ArrayAdapter<String> arrayAdaptersec = new ArrayAdapter<>(this,R.layout.style_spinner,Sec);
//        spinnerSection.setAdapter(arrayAdaptersec);

        //has no value is default
        spinnerList1.add("[GENERATE SCHOOL CODE]");
        adapter1 = new ArrayAdapter<>(addsection.this, android.R.layout.simple_spinner_item,spinnerList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        defaultspinner.setAdapter(adapter1);
        //has no value is default

        //----------------------------
        //get the value in SCHOOL CODE
        //spinnerList.add("[SCHOOL CODE]");
       //// spinnerList.add("This a school code1");
        //spinnerList.add("This a school code2");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_selectioncode = (String) ""+spinnerSection.getSelectedItem().toString();

                if(input_selectioncode.equals("[PROGRAM CODE]")){
                    Toast.makeText(getApplicationContext(),"Please select Program Code",Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(getApplicationContext(),input_selectioncode,Toast.LENGTH_LONG).show();
                    String[] getFirstIndex = input_selectioncode.split("-");
                   // Toast.makeText(getApplicationContext(),getFirstIndex[0],Toast.LENGTH_LONG).show();

                    RequestQueue requestQueue = Volley.newRequestQueue(addsection.this);
                    String url = "http://jeepcard.net/gportal/addSection.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
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
                            params.put("programid",getFirstIndex[0]);
                            params.put("inputSection",sectioninput.getText().toString());
                            params.put("schoolCode",sectioncode.getText().toString());
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
            }
        });


        // generate the school code
        generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input_sectioncode = (String) sectioncode.getText().toString().trim();

                if(input_sectioncode.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter First School code",Toast.LENGTH_LONG).show();
                }
                else{
                   //Toast.makeText(getApplicationContext(),"Success FROM ANDROID STUDIO",Toast.LENGTH_LONG).show();
                    cardviewdefault.setVisibility(View.GONE);
                    generateCode.setVisibility(View.GONE);
                    defaultspinner.setVisibility(View.GONE);

                    add.setVisibility(View.VISIBLE);
                    spinnerSection.setVisibility(View.VISIBLE);
                    cardviewSchoolcode.setVisibility(View.VISIBLE);
                    textsecinput.setVisibility(View.VISIBLE);
                    sectioninput.setVisibility(View.VISIBLE);

                    //--
                    RequestQueue requestQueue = Volley.newRequestQueue(addsection.this);
                    String url = "http://jeepcard.net/gportal/spinnerSection.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                          // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                           // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            if(response.equals("Failed")){
                                cardviewdefault.setVisibility(View.VISIBLE);
                                generateCode.setVisibility(View.VISIBLE);
                                defaultspinner.setVisibility(View.VISIBLE);

                                add.setVisibility(View.GONE);
                                spinnerSection.setVisibility(View.GONE);
                                cardviewSchoolcode.setVisibility(View.GONE);
                                textsecinput.setVisibility(View.GONE);
                                sectioninput.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Invalid Section Code",Toast.LENGTH_LONG).show();
                            }

                            try {
                                JSONObject jsonObject =  new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                spinnerList.add("[PROGRAM CODE]");
                                if(success==1){
                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject data = jsonArray.getJSONObject(i);
                                        String proggramid = data.getString("progcodeid");
                                        String scline = proggramid;
                                        spinnerList.add(scline);
                                        adapter = new ArrayAdapter<>(addsection.this, android.R.layout.simple_spinner_item,spinnerList);
                                        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                        spinnerSection.setAdapter(adapter);
                                        sectioncode.setVisibility(View.GONE);
                                        textschoolcode.setText("SECTION CODE: "+sectioncode.getText().toString());
                                    }
                                }

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            AlertDialog alertDialog = new AlertDialog.Builder(addsection.this).create();
                            alertDialog.setTitle("Message");
                            alertDialog.setMessage("ERROR!!");//Message to be shown
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            cardviewdefault.setVisibility(View.VISIBLE);
                            generateCode.setVisibility(View.VISIBLE);
                            defaultspinner.setVisibility(View.VISIBLE);
                            add.setVisibility(View.GONE);
                            spinnerSection.setVisibility(View.GONE);
                            cardviewSchoolcode.setVisibility(View.GONE);
                           // Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map <String,String> params = new HashMap<>();
                            params.put("schoolcode",input_sectioncode);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);


                }
            }
        });
        // generate the school code first










    }
}