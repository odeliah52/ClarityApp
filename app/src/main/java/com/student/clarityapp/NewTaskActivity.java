package com.student.clarityapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// ... other imports ...

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        Button btn = findViewById(R.id.save_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText taskTitle = findViewById(R.id.taskTitle);
                Task newTask = new Task(taskTitle.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("task", newTask);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
