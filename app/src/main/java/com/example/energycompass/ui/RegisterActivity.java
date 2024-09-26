package com.example.energycompass.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.energycompass.MainActivity;
import com.example.energycompass.R;
import com.example.energycompass.databinding.ActivityLoginBinding;
import com.example.energycompass.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.singUpDtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailEd.getText().toString().isEmpty()||binding.passwordEd.getText().toString().isEmpty()||
                        binding.usernameEd.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();}
                    else{
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEd.getText().toString(), binding.passwordEd.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            HashMap<String, String> userInfo = new HashMap<>();
                                            userInfo.put("email", binding.emailEd.getText().toString());
                                            userInfo.put("username", binding.usernameEd.getText().toString());
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(userInfo);

                                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        }
                                    }
                                });
                    }
            }
        });
    }
}