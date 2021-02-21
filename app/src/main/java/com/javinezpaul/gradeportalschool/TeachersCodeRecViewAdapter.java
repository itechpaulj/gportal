package com.javinezpaul.gradeportalschool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

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
                builder.setTitle("Copy or Delete?");

                // Set up the buttons
                builder.setNeutralButton("Copy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: please write copy to clipboard function here
                        Toast.makeText(mContext, teachersCodes.get(position).getTeacherscode() + " Copied", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: please write delete function here
                        Toast.makeText(mContext, teachersCodes.get(position).getTeacherscode() + " Deleted", Toast.LENGTH_SHORT).show();
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
