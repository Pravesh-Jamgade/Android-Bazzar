package com.pravesh.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity implements Animation.AnimationListener {

    Animation animFadeIn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


// create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
// enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
// enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
// set the transparent color of the status bar, 20% darker
        tintManager.setTintColor(Color.parseColor("#20000000"));

        setContentView(R.layout.activity_splash_screen);
        final SessionManagement s = new SessionManagement(SplashScreen.this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(s.isLoggedIn())
                {
                    startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                }
                else{
                    s.checkLogin();
                }
            }
        },3000);



        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
        }
        // load the animation
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        // set animation listener
        animFadeIn.setAnimationListener(this);
        // animation for image
        linearLayout = (LinearLayout) findViewById(R.id.layout_linear);
        // start the animation
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        //under Implementation
    }

    public void onAnimationEnd(Animation animation) {
        // Start Main Screen
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        //under Implementation
    }


}
