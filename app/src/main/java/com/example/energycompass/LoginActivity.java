package com.example.energycompass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.energycompass.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.energycompass.databinding.ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_login);

        binding.loginDtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailEd.getText().toString().isEmpty()|| binding.passwordEd.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Faileds cannot be empty", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEd.getText().toString(), binding.passwordEd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }
                                }
                            });
                }
            }
        });
    }
}