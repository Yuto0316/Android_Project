package com.example.unitrade;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout emailInputLayout;
    private TextInputLayout passwordInputLayout;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;
    private MaterialButton signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle edge-to-edge system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        initializeViews();

        // Set up click listeners
        setupListeners();
    }

    private void initializeViews() {
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpLink = findViewById(R.id.signUpLink);
    }

    private void setupListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp();
            }
        });
    }

    private void handleLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateInputs(email, password)) {
            // Placeholder for Firebase Authentication logic
            performFirebaseLogin(email, password);
        }
    }

    private boolean validateInputs(String email, String password) {
        boolean isValid = true;

        // Reset errors
        emailInputLayout.setError(null);
        passwordInputLayout.setError(null);

        // Email validation (Campus Security: emphasize .edu)
        if (email.isEmpty()) {
            emailInputLayout.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.setError("Please enter a valid email");
            isValid = false;
        } else if (!email.toLowerCase().endsWith(".edu")) {
            emailInputLayout.setError("University email (.edu) is required for campus security");
            isValid = false;
        }

        // Password validation
        if (password.isEmpty()) {
            passwordInputLayout.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            passwordInputLayout.setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }

    private void performFirebaseLogin(String email, String password) {
        // TODO: Integrate Firebase Authentication
        // Example:
        // mAuth.signInWithEmailAndPassword(email, password)
        //     .addOnCompleteListener(this, task -> { ... });
        
        Toast.makeText(this, "Attempting login for: " + email, Toast.LENGTH_SHORT).show();
    }

    private void handleSignUp() {
        // TODO: Navigate to Sign Up screen or Activity
        Toast.makeText(this, "Redirecting to Sign Up...", Toast.LENGTH_SHORT).show();
    }
}
