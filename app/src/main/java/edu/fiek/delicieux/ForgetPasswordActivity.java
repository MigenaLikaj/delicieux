package edu.fiek.delicieux;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Objects;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView resetState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();
        final MaterialEditText emailAddress = findViewById(R.id.email);
        resetState = findViewById(R.id.resetText);
        Button resetPasswordBtn = findViewById(R.id.sendMessage);
        progressBar = findViewById(R.id.progressBar);
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.fetchSignInMethodsForEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.getResult().getSignInMethods().isEmpty()){
                            progressBar.setVisibility(View.GONE);
                            resetState.setText("This is not an registered email, you can create new account");
                        }else{
                            mAuth.sendPasswordResetEmail(emailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        resetState.setText("An email to reset password has been sent to your email address");
                                    }else{
                                        resetState.setText(task.getException().getMessage());
                                    }
                                }
                            });
                        }

                    }
                });
            }
        });


    }

   }