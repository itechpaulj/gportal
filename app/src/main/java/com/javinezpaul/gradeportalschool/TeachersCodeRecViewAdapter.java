package com.javinezpaul.gradeportalschool;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import static android.content.Context.MODE_PRIVATE;

public class TeachersCodeRecViewAdapter extends RecyclerView.Adapter<TeachersCodeRecViewAdapter.ViewHolder>{

    private static final String TAG = "TeachersCodeRecViewAdap";

    private ArrayList<TeachersCode> teachersCodes = new ArrayList<>();
    private Context mContext;

    public TeachersCodeRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachermainscreen_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        holder.teachersCode.setText(teachersCodes.get(position).getTeacherscode());
        holder.codeDesc.setText(teachersCodes.get(position).getAyear1()+"-"+teachersCodes.get(position).getAyear2()+" "
                        +teachersCodes.get(position).getAyearlevel()+" year " + teachersCodes.get(position).getAysem()+" sem \n"
                        + teachersCodes.get(position).getSubjecttitle() + "\n"
                        + teachersCodes.get(position).getSectioncode()
                );
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
//                Toast.makeText(mContext, teachersCodes.get(position).getTeacherscode() + " Selected", Toast.LENGTH_SHORT).show();
                //TODO: Please show an alertdialog with 2 buttons, copy and delete code. to gete the code: teachersCodes.get(position).getTeacherscode()
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(teachersCodes.get(position).getTeacherscode());
                builder.setMessage("Actions");

                // Set up the buttons
                builder.setNeutralButton("Copy to Clipboard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: please write copy to clipboard function here
                        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("TextView",teachersCodes.get(position).getTeacherscode());
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(mContext, teachersCodes.get(position).getTeacherscode() + " Copied", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Are you sure you want to delete the code?");
                        builder.setMessage("Enrolled in this code may not retain its records.");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: please write delete function here
                                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                                SharedPreferences sp = mContext.getSharedPreferences("credentials",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                String userid = sp.getString("cardid","");

                                String url = "http://jeepcard.net/gportal/deactivateTeachercode.php?teacherscodeid="+teachersCodes.get(position).getTeacherscodeid();
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Intent redirect = new Intent(mContext , TeacherMainScreen.class);
                                        mContext.startActivity(redirect);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Intent redirect = new Intent(mContext , TeacherMainScreen.class);
                                        mContext.startActivity(redirect);
                                        Toast.makeText(mContext,"Network unstable, please retry\n"+error.toString(),Toast.LENGTH_LONG).show();
                                    }
                                });
                                requestQueue.add(stringRequest);
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();

                    }
                });

                builder.setNegativeButton("Students", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: Intent to TeachersCodeStudents.class
                        Intent redirect = new Intent(mContext , TeachersCodeStudents.class);
                        redirect.putExtra("toolbarName", teachersCodes.get(position).getSubjecttitle() + " Students");
                        redirect.putExtra("teacherscode", teachersCodes.get(position).getTeacherscode());
                        mContext.startActivity(redirect);
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachersCodes.size();
    }

    public void setTeachersCodes(ArrayList<TeachersCode> teachersCodes) {
        this.teachersCodes = teachersCodes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView teachersCode, codeDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            teachersCode = itemView.findViewById(R.id.teachersCode);
            codeDesc = itemView.findViewById(R.id.codeDesc);

        }
    }
}
