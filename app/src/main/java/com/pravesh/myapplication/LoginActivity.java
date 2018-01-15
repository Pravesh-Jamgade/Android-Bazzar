package com.pravesh.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public Button btn;
    private EditText etEmail;
    private EditText etPass;
    private TextView tv;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    public Button btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


// create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
// enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
// enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
// set the transparent color of the status bar, 20% darker
        tintManager.setTintColor(Color.parseColor("#20000000"));

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null )
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ResultActivity.class));
        }

        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        btn = findViewById(R.id.btn_sign_in);
        tv = findViewById(R.id.tv_sign_up);



      // clicklistener to buttons


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        progressDialog = new ProgressDialog(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        tv.setOnClickListener(this);
    }


public void onClick(View view)
{
    if(view == tv)
    {
        startActivity(new Intent(LoginActivity.this,SignupActivity.class));
    }
}
public void userLogin()
{
    final String email = etEmail.getText().toString();
    String password = etPass.getText().toString().trim();

    // check user email and password

    if(TextUtils.isEmpty(email))
    {
        Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
        return;
    }

    if(TextUtils.isEmpty(password))
    {
        Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
        return;
    }

    progressDialog.setMessage(" Please Wait...");
    progressDialog.show();
    final SessionManagement sessionManagement = new SessionManagement(this);
    firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    //if the task is successfull
                    if(task.isSuccessful()){
                        //start the profile activity
                        sessionManagement.createLoginSession(email);
                        startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Email or Password is incorrect",Toast.LENGTH_LONG).show();
                    }
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
