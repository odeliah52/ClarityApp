package com.student.clarityapp;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {

    private static final String FILE_NAME = "com.student.clarityapp.preferences";

    public static void storeData(Context context, List<Task> tasks) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(tasks);
        editor.putString("tasks", json);
        editor.apply();
    }

    public static List<Task> loadData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String json = sp.getString("tasks", null);
        if (json != null) {
            Type type = new TypeToken<List<Task>>(){}.getType();
            return new Gson().fromJson(json, type);
        } else {
            // Add default tasks here if you want, or leave it as an empty list.
            return new ArrayList<>();
        }
    }
}
