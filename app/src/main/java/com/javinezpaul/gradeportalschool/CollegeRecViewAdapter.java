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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Creating a RecyclerViewAdapter Pseudo Code by Dan Astillero
 * 1. Create an activity containing RecyclerView with id = collegesRecView, use RelativeLayout as Layout (for now) with an  id = parent
 * 2. At activityClass,  declare private RecyclerView  collegesRecView;
 *      at activityClass' onCreate method, write collegesRecView = findViewById(R.id.collegesRecView);
 * 3. Create a layout at res>>new resource layout>> Filename: colleges_list_item; Root Element: RelativeLayout
 * 4. At colleges_list_item.xml, design how item will be displayed.
 * 5. Create a model named College by package>>new class>>File name: College
 * 6. At College.java, declare properties id, code, desc
 *      generate POJO by
 *      Alt + Insert >> constructors >> select all >> ok
 *      Alt + Insert >> getters and setter >> select all >> ok
 *      Alt + Insert >> toString() >> select all >> ok
 * 7. Create RecycleViewAdapter  by package>>new Class>>File name: CollegeRecViewAdapter
 *      write, public class ViewHolder extends RecyclerView.ViewHolder { }
 *          inside ViewHolder, generate constructor containing super by Alt + Insert>>Constructors
 *      declare all of the Views from colleges_list_item.xml inside ViewHolder class
 *          ex. private CardView parent;
 *              private TextView code, desc;
 *      inside constructor of ViewHolder after super statement, initialize UI elements
 *          ex. parent = itemView.findViewById(R.id.parent);
 *             code = itemView.findViewById(R.id.collegeCode);
 *             desc = itemView.findViewById(R.id.collegeDesc);
 *      extend CollegeRecViewAdapter to RecyclerView.Adapter and pass ViewHolder as Data type
 *          write, public class CollegeRecViewAdapter extends RecyclerView.Adapter<CollegeRecViewAdapter.ViewHolder>
 *      inside class CollegeRecViewAdapter, generate methods by Ctrl + I>> select all>>ok
 *
 *      define an ArrayList of different college inside class CollegeRecViewAdapter,
 *          write, private ArrayList<College> colleges = new ArrayList<>();
 *      define Context to be used in Glide Library (when getting image online) inside class CollegeRecViewAdapter,
 *         write, private Context mContext;
 *      generate constructor for Context by Alt + insert>>select Context>>ok
 *      generate setter method for arraylist of college after the getItemCount() method
 *          inside the newly created setter method for arraylist adapter, write, notifyDataSetChanged(); to refresh the data in recyclerView
 *      let getItemCount() method return colleges.size(); to return the arraylist size
 *      inside onCreateViewHolder(), create an instance of ViewHolder and return that
 *          write, View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colleges_list_item, parent, false);
 *                  return new ViewHolder(view);
 *       declare, private static final String  TAG = "CollegeRecViewAdapter" inside CollegeRecViewAdapter
 *      inside onBindViewHolder(), write, Log.d(TAG, "onBindViewHolder: Called "); to check that ViewHolder has been called.
 *          after Log.d, set the value for collegeCode, collegeDesc by settext to layout
 *              write, holder.code.setText(colleges.get(position).getCode());
 *                     holder.desc.setText(colleges.get(position).getDesc());
 *              then  Set OnclickListener to parent(cardView)
 * 8. Initialize and Set as adapter to collegesRecView in activityClass,which is ViewColllege.
 *      To initialize, inside ViewCollege class, write, private CollegeRecViewAdapter adapter.
 *                      inside onCreate method, write,  adapter = new CollegeRecViewAdapter(this);
 *      To set adapter to RecyclerView, inside onCreate method, write, collegesRecView.setAdapter(adapter);
 *      To set Layout Manager, inside onCreate method, write, collegesRecView.setLayoutManager(new GridLayoutManager(this, 2);
 *
 *      //to add data in arraylist
 *         ArrayList<College> colleges = new ArrayList<>();
 *         colleges.add(new College("1","IIT","Industrial and Information Technology"));
 *         colleges.add(new College("2", "BA", "Business Administration"));
 *         colleges.add(new College("3","GATE", "General Academic Tertiary Education"));
 *         colleges.add(new College("4", "COE", "College of Engineering"));
 *
 *         adapter.setColleges(colleges);
 */

public class CollegeRecViewAdapter extends RecyclerView.Adapter<CollegeRecViewAdapter.ViewHolder> {

    private static final String TAG = "CollegeRecViewAdapter";

    //this ArrayList is all of the Colleges that will be shown in the activity.
    private ArrayList<College> colleges = new ArrayList<>();
    private Context mContext;

    //constructor for Context
    public CollegeRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //methods
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colleges_list_item, parent, false);
        //ViewHolder Class
//        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        holder.code.setText(colleges.get(position).getCode());
        holder.desc.setText(colleges.get(position).getDesc());
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
                Toast.makeText(mContext, colleges.get(position).getCode() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return colleges.size();
    }

    public void setColleges(ArrayList<College> colleges) {
        this.colleges = colleges;
        notifyDataSetChanged();
    }

    //innerClass
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView code, desc;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            code = itemView.findViewById(R.id.collegeCode);
            desc = itemView.findViewById(R.id.collegeDesc);
        }
    }
}
