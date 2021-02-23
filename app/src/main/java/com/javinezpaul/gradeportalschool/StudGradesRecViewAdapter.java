package com.javinezpaul.gradeportalschool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class StudGradesRecViewAdapter extends RecyclerView.Adapter<StudGradesRecViewAdapter.ViewHolder>{
    private static final String  TAG = "StudGradesRecViewAdap";

    private String  m_grade, m_note;

    private ArrayList<StudentsGrade> studentsGrades = new ArrayList<>();
    private Context mContext;

    public StudGradesRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherscodestudents_list_item, parent, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        holder.studentName.setText(studentsGrades.get(position).getStudentname());
        holder.grade.setText(studentsGrades.get(position).getGrade());
        /** Glide
         * To show image from internet to ImageView
         * Glide.with(mContext)
         * .asBitmap()
         * .load(colleges.get(position).getImageUrl)
         * .into(holder.imgCollege) //just an example
         */

        //Set OnclickListener to parent(cardView)
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentsGrades.get(position).getGrade().equals(":(")){
                    Toast.makeText(mContext, "Nothing's here", Toast.LENGTH_SHORT).show();
                }else{
                    // Toast.makeText(mContext, studentsGrades.get(position).getGradeid() + " Selected", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle(studentsGrades.get(position).getSubjecttitle());
                    builder.setMessage(studentsGrades.get(position).getStudentname());

                    // Set up the edittext and layout
                    LinearLayout layout = new LinearLayout(mContext);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    final EditText input = new EditText(mContext);
                    layout.addView(input);

                    final EditText input2 = new EditText(mContext);
                    layout.addView(input2);

                    builder.setView(layout);

                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    input2.setInputType(InputType.TYPE_CLASS_TEXT);

                    input.setText(studentsGrades.get(position).getGrade());
                    input2.setText(studentsGrades.get(position).getNote());

                    // Set up the buttons
                    builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(mContext, "Processing...", Toast.LENGTH_LONG).show();
                            m_grade = input.getText().toString();
                            m_note = input2.getText().toString();
                            //TODO: PHP volley request here to update grade of student
//                        Toast.makeText(mContext, m_grade + " \n" + m_note + " \ngradeid: " +studentsGrades.get(position).getGradeid(), Toast.LENGTH_LONG).show();
                            Double g=0.00;
                            try{
                                g=Double.parseDouble(String.valueOf(m_grade));
                                if(Double.parseDouble(String.valueOf(m_grade))>5.00 || Double.parseDouble(String.valueOf(m_grade))<0.00 ){
                                    Toast.makeText(mContext, "Please input grade from 0.00 to 5.00", Toast.LENGTH_LONG).show();
                                }else{
                                    //HTTP request
                                    RequestQueue queueu = Volley.newRequestQueue(mContext);
                                    String url = "http://jeepcard.net/gportal/updateGrade.php?gradeid="+studentsGrades.get(position).getGradeid()
                                            +"&grade="+m_grade+"&note="+m_note;
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
//                                    Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                            builder.setTitle("Message");
                                            builder.setMessage(response);

                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(mContext , TeachersCodeStudents.class);
                                                    intent.putExtra("teacherscode", studentsGrades.get(position).getTeachescode() );
                                                    intent.putExtra("toolbarName", studentsGrades.get(position).getSubjecttitle() );
                                                    mContext.startActivity(intent);
                                                    dialog.cancel();
                                                }
                                            });
                                            builder.show();
                                        } //onResponse
                                    }, new Response.ErrorListener(){
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(mContext, "Network unstable, please retry\n" + error.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    });//Stringrequest
                                    queueu.add(stringRequest);
                                }
                            }catch (NumberFormatException numberFormatException){
                                Toast.makeText(mContext, "Please input grade from 0.00 to 5.00\nwithout containing letter", Toast.LENGTH_LONG).show();
                            }


                        }
                    });

                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentsGrades.size();
    }

    public void setStudentsGrades(ArrayList<StudentsGrade> studentsGrades) {
        this.studentsGrades = studentsGrades;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView studentName, grade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            studentName = itemView.findViewById(R.id.studentName);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
