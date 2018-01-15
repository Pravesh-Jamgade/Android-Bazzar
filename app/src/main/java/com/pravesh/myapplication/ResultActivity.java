package com.pravesh.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashMap;

public  class ResultActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    SessionManagement session;
    Button b;
    TextView t;

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    Activity a;


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);



            /*-------------------------------------*/

            firebaseAuth = FirebaseAuth.getInstance();
            session = new SessionManagement(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            String email = user.get(SessionManagement.KEY_EMAIL);

            /*-------------------------------------*/


          /*

            b = findViewById(R.id.logout);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    session.logoutUser();
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            });

          */

            mToolBar = findViewById(R.id.nav_action);
            setSupportActionBar(mToolBar);

            mDrawerLayout = findViewById(R.id.drawer);
            mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

            mDrawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        // Retrieve the AppCompact Toolbar
        Toolbar toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

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

            /*Fragment*/

            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId())
                    {
                        case R.id.nav_item1:
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container,new OffersFragment());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Offers");

                            item.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;

                        case R.id.nav_item2:
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container,new ElectronicsFragment());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Electronics");
                            item.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;

                        case R.id.nav_item3:
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container,new LifeStyleFragment());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Life style");
                            item.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            break;

                        case R.id.logout:
                            item.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            SessionManagement s=new SessionManagement(ResultActivity.this);
                            s.logoutUser();
                            s.checkLogin();
                            break;
                    }
                    return true;
                }
            });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
