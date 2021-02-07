package com.javinezpaul.gradeportalschool;

import android.content.Context;
import android.media.tv.TvContract;
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

public class ProgramRecViewAdapter extends RecyclerView.Adapter<ProgramRecViewAdapter.ViewHolder> {

    private static final String TAG = "ProgramRecViewAdapter";

     private ArrayList<Program> programs = new ArrayList<>();
     private Context mContext;

    public ProgramRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programs_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");

        holder.code.setText(programs.get(position).getCode());
        holder.details.setText(programs.get(position).getYear1() + " - " +
                        programs.get(position).getYear2()  + "\n" +
                        programs.get(position).getName() + "\n" +
                        programs.get(position).getMajor()
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
                Toast.makeText(mContext, programs.get(position).getCode() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView code, details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            code = itemView.findViewById(R.id.progCode);
            details = itemView.findViewById(R.id.progDetails);
        }
    }
}
