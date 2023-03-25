package com.student.clarityapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;


public class TaskActivity extends AppCompatActivity {


    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_row);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> startActivity(new Intent(TaskActivity.this,dashboard.class)));
    }
}
