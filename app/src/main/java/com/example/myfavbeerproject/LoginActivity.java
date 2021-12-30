package com.example.myfavbeerproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        tietEmail = findViewById(R.id.tietEmail);
        tietPassword = findViewById(R.id.tietPassword);

        Button mLoginButton = findViewById(R.id.bLogin);
        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                doLogin(tietEmail.getText().toString(), tietPassword.getText().toString());
            }
        });

        Button mRegisterButton = findViewById(R.id.bRegister);
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createAccount(tietEmail.getText().toString(), tietPassword.getText().toString());
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
        {
            // De este modo si ya nos hemos logueado 1 vez ya no habria que pasar por esta pantalla al entrar en la App
            startMain();

            //Con esta accion estamos haciendo que si ya estamos logueados, nos vuelva a pedir los datos, si quitamos el comentario de startMain(); entraria directo a MainActivity
//            mAuth.signOut();
        }

    }

    private boolean validateForm(String email, String password)
    {
        boolean valid = true;
        if (TextUtils.isEmpty(email))
        {
            tietEmail.setError("Email is required");
            valid = false;
        } else {
            tietEmail.setError(null);
        }

        if (TextUtils.isEmpty(password))
        {
            tietPassword.setError("Password is required");
            valid = false;
        } else {
            tietPassword.setError(null);
        }
        return valid;
    }

    private void doLogin(String email, String password)
    {
        if (!validateForm(email, password))
        {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    startMain();
                } else {
                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createAccount(String email, String password)
    {
        if (!validateForm(email, password))
        {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("First User").build();
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null)
                        user.updateProfile(profileUpdates);
                    sendEmail();
                } else {
                    Toast.makeText(LoginActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null)
        {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,"A verification email has been sent to " + user.getEmail(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this,"Failed to send verification email", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void startMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}