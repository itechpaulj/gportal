package com.javinezpaul.gradeportalschool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectRecViewAdapter extends RecyclerView.Adapter<SubjectRecViewAdapter.ViewHolder> {

    private static final String  TAG = "SubjectRecViewAdapter";

    ArrayList<Subject> subjects = new ArrayList<>();
    private Context mContext;

    public SubjectRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");

        holder.code.setText(subjects.get(position).getCode());
        holder.desc.setText(subjects.get(position).getProgramid() + "\n" +
                subjects.get(position).getAyid() +"\n"+
                subjects.get(position).getTitle()
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
                Toast.makeText(mContext, subjects.get(position).getCode() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView code, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            code = itemView.findViewById(R.id.subjectCode);
            desc = itemView.findViewById(R.id.subjectDesc);
        }
    }
}
