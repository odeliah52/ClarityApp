package com.student.clarityapp;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public CheckBox checkBox;
    public TextView description;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkBtn);
        description = itemView.findViewById(R.id.taskName);
    }
}
