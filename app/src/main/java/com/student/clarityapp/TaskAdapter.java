package com.student.clarityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Task> tasks = new ArrayList<>();
    private CollectionReference tasksCollection = FirebaseFirestore.getInstance().collection("tasks");

    public TaskAdapter() {
        tasksCollection.orderBy("Description").addSnapshotListener((QuerySnapshot snapshots, FirebaseFirestoreException e) -> {
            if (e != null) {
                // Handle the error
                return;
            }

            for (DocumentChange dc : snapshots.getDocumentChanges()) {
                switch (dc.getType()) {
                    case ADDED:
                        tasks.add(dc.getDocument().toObject(Task.class));
                        notifyItemInserted(tasks.size() - 1);
                        break;
                    case MODIFIED:
                        // Handle modifications if necessary
                        break;
                    case REMOVED:
                        // Handle removals if necessary
                        break;
                }
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_row, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.description.setText(task.getDescription());
        // Other binding logic
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyItemInserted(tasks.size() - 1);
    }

    public void deleteTask(int position) {
        if (position >= 0 && position < tasks.size()) {
            tasks.remove(position);
            notifyItemRemoved(position);
        }
    }
}
