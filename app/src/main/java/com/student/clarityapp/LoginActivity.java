package com.student.clarityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> googleSignInLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btn = findViewById(R.id.signuplogin);
        btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        Button loginBtn = findViewById(R.id.btnlogin);
        loginBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, dashboard.class)));

        // Google SignIn Initialization
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("803931409850-rf3dhkgshc45mevass9c7ct7t1idjept.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize the GoogleSignInLauncher
        googleSignInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            // Extracting user details
                            String personName = account.getDisplayName();
                            String personEmail = account.getEmail();
                            String personId = account.getId();

                            // For demonstration, show a toast with user's name and email
                            Toast.makeText(LoginActivity.this, "Name: " + personName + ", Email: " + personEmail, Toast.LENGTH_SHORT).show();

                            // Proceeding to next activity
                            Intent i = new Intent(LoginActivity.this, dashboard.class);
                            startActivity(i);
                        } catch (ApiException e) {
                            // Handle error
                            Toast.makeText(LoginActivity.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Button googleSignInButton = findViewById(R.id.btnlogingoogle);
        googleSignInButton.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            googleSignInLauncher.launch(signInIntent);
        });
    }
}
