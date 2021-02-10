package com.javinezpaul.gradeportalschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewTeachers extends AppCompatActivity {

    TextView toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teachers);

        toolbarName=findViewById(R.id.toolbarName);
        toolbarName.setText("Teachers");
    }
}