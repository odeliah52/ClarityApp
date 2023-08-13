package com.student.clarityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

        Bundle b = getIntent().getExtras();
        Task task = (Task) b.getSerializable("Task");

        CheckBox checkBox = findViewById(R.id.checkBtn);
        TextView taskName = findViewById(R.id.taskName);

        taskName.setText(task.getDescription());
        // Assuming you have a boolean status in your Task class indicating if the task is done or not.
        // checkBox.setChecked(task.isDone());
    }
}
