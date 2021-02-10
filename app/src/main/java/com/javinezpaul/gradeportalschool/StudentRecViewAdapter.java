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

public class StudentRecViewAdapter extends RecyclerView.Adapter<StudentRecViewAdapter.ViewHolder> {
    private static final String  TAG = "StudentRecViewAdapter";

    private ArrayList<Student> students = new ArrayList<>();
    private Context mContext;

    public StudentRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_list_item, parent, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");

        //Dela Cruz, Juan Miranda\nIIT | BSIT | IT4A\nV-657489EF
        holder.studentDesc.setText(students.get(position).getId() + "\n" +
                students.get(position).getLname() + " , " +
                students.get(position).getFname() + "  " +
                students.get(position).getMname() + "\n" +
                students.get(position).getProgramid() + " | " +
                students.get(position).getSectionid() + "\nView Code: " +
                students.get(position).getViewcode());

        //Glide
        ///To show image from internet to ImageView
        Glide.with(mContext)
                .asBitmap()
                .load(students.get(position).getImage())
                .into(holder.imageStudent); //ImageView


        //Set OnclickListener to parent(cardView)
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, students.get(position).getId() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView studentDesc;
        private ImageView imageStudent;
        private ImageButton studentMoreButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            studentDesc=itemView.findViewById(R.id.studentDesc);
            imageStudent=itemView.findViewById(R.id.imageStudent);
            studentMoreButton=itemView.findViewById(R.id.studentMoreButton);

        }
    }
}
