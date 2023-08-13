package com.student.clarityapp;

import java.util.UUID;

public class TaskID {
    private String id;

    public TaskID() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
