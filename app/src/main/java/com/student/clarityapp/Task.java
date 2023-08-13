package com.student.clarityapp;

import com.google.firebase.firestore.DocumentId;

import java.io.Serializable;

public class Task implements Serializable {

    @DocumentId
    private String taskId; // Use this to store the unique task ID

    private String description;
    private boolean isCompleted;

    public Task() {
        taskId = new TaskID().getId(); // Generate a new unique ID for each task
    }

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskId = new TaskID().getId();
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
