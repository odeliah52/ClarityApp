package com.student.clarityapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddModifyTask extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        etTask = findViewById(R.id.taskTitle);
        btnAdd = findViewById(R.id.save_btn);

        btnAdd.setOnClickListener(v -> {
            String name = etTask.getText().toString();
            if (!name.isEmpty()) {
                Task task = new Task(name);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("task", task);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } else {
                Toast.makeText(AddModifyTask.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

