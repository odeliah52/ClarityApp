package com.student.clarityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

public class dashboard extends Activity implements View.OnClickListener {


    public CardView cardTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        cardTask = findViewById(R.id.taskCard);
        cardTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.taskCard) {
            Intent i = new Intent(this, TaskActivity1.class);
            startActivity(i);
        }
    }
}
