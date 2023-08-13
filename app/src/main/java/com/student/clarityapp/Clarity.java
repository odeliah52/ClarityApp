package com.student.clarityapp;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Clarity extends Application {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase here
        FirebaseApp.initializeApp(this);

        // Set up database reference
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tasks");
    }

    // Method to write a new task
    public void writeNewTask(String taskId, String description) {
        Task task = new Task(description);
        myRef.child(taskId).setValue(task);
    }
}
