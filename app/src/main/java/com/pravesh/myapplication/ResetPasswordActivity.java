package com.pravesh.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class ResetPasswordActivity extends AppCompatActivity  {

    Button btn_back, btn_reset;
    EditText re_email;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

// create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
// enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
// enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
// set the transparent color of the status bar, 20% darker
        tintManager.setTintColor(Color.parseColor("#20000000"));

        btn_back = findViewById(R.id.btn_back);
        btn_reset = findViewById(R.id.btn_reset_password);
        re_email = findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, SignupActivity.class));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = re_email.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(ResetPasswordActivity.this,SignupActivity.class));
                                    finish();
                                    Toast.makeText(ResetPasswordActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(ResetPasswordActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}