package com.pravesh.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the AppCompact Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Bazzar");

        // Set the padding to match the Status Bar height
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
// create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
// enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
// enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
// set the transparent color of the status bar, 20% darker
        tintManager.setTintColor(Color.parseColor("#20000000"));

        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        if(view == bt1)
        {
            startActivity(new Intent(MainActivity.this,SignupActivity.class));
        }

        if(view == bt2)
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
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
