package com.student.clarityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaskActivity1 extends AppCompatActivity {

    private TaskAdapter adapter;
    private FirebaseFirestore db;

    // Define the contract and callback for the activity result
    private final ActivityResultLauncher<Intent> newTaskResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Task task = (Task) result.getData().getSerializableExtra("task");
                    if (task != null) {
                        db.collection("tasks").add(task); // Add the task to Firestore directly
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_row);

        db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaskAdapter(); // Initialize with Firestore inside
        recyclerView.setAdapter(adapter);

        // Use the launcher to start your activity
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddModifyTask.class);
            newTaskResultLauncher.launch(intent);
        });

        // Back button functionality to return to the dashboard
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Retrieve tasks from Firestore and add them to the adapter
        retrieveTasksFromFirestore();
    }

    // Retrieve tasks from Firestore and add them to the adapter
    private void retrieveTasksFromFirestore() {
        db.collection("tasks").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot doc : task.getResult()) {
                    Task taskData = doc.toObject(Task.class);
                    if (taskData != null) {
                        adapter.addTask(taskData);
                    }
                }
            } else {
                String errorMessage = "Error fetching tasks from Firestore";
                Toast.makeText(TaskActivity1.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
