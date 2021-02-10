package com.javinezpaul.gradeportalschool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TeacherRecViewadapter extends RecyclerView.Adapter<TeacherRecViewadapter.ViewHolder>{
    private static final String  TAG = "TeacherRecViewAdapter";

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private Context mContext;

    public TeacherRecViewadapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");

        holder.teacherDesc.setText(teachers.get(position).getLname() + " , " +
                                    teachers.get(position).getFname() + "  " +
                                    teachers.get(position).getMname() + "\n" +
                                    teachers.get(position).getId() + "  |  " +
                                    teachers.get(position).getCollegeid());
         //Glide
         ///To show image from internet to ImageView
          Glide.with(mContext)
          .asBitmap()
          .load(teachers.get(position).getImage())
          .into(holder.imageTeacher); //ImageView


        //Set OnclickListener to parent(cardView)
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, teachers.get(position).getId() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView teacherDesc;
        private ImageView imageTeacher;
        private ImageButton teacherMoreButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.parent);
            teacherDesc=itemView.findViewById(R.id.teacherDesc);
            imageTeacher=itemView.findViewById(R.id.imageTeacher);
            teacherMoreButton=itemView.findViewById(R.id.teacherMoreButton);
        }
    }
}
