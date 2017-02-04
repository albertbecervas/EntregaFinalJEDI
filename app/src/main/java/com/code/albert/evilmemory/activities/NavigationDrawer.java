package com.code.albert.evilmemory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.fragments.Calculator;
import com.code.albert.evilmemory.fragments.Profile;
import com.code.albert.evilmemory.interfaces.OnFragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ActionBarDrawerToggle toggle;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ImageView profile;

    FrameLayout container;
    AnimationDrawable anim;

    View layout;

    ButterKnife butterKnife;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @BindView(R.id.nav_view) NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.setContentView(R.layout.nav_header_base);
        super.setContentView(R.layout.activity_navigation_drawer);
        butterKnife.bind(this);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = getSharedPreferences("myApp", 0).edit();

        //realm
        //Realm.init(this);

        Fragment f = new Profile();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_base, f, "FRAGMENt")
                .commit();


        layout = findViewById(R.id.drawer_layout);

        setView();


    }

    public void startAnimation(){
        container = (FrameLayout) findViewById(R.id.fragment_background);
        anim= (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }

    protected void setView() {

        TextView hello;
        String username= sharedPreferences.getString("username","human");

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);

        /*String s= sharedPreferences.getString("s", "jvkbn");
        Uri myUri= Uri.parse(s);
        try {
            profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), myUri));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        hello = (TextView) navigationView.getHeaderView(0).findViewById(R.id.hello);
        hello.setText("Hi " + username + "!");

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.profile:
                Fragment f= new Profile();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_base,f, "MEMORY_FRAGMENT").commit();
                finish();
                break;
            case R.id.evilMemory:
                //startActivity(new Intent(getApplicationContext(), EvilMemory.class));
                break;
            case R.id.calculator:
                Fragment c= new Calculator();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_base,c, "CALCULATOR_FRAGMENT").commit();
                finish();

                break;
            case R.id.musicplayer:
                //startActivity(new Intent(getApplicationContext(),MusicPlayer.class));
                break;
            case R.id.ranking:
                //startActivity(new Intent(getApplicationContext(),Ranking.class));
                break;
            case R.id.logout:
                editor.putBoolean("UserLoggedIn", false);
                editor.putBoolean("keepin", false);
                editor.apply();
                Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(loginIntent);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {

        DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_navigation_drawer, null);
        FrameLayout frameLayout = (FrameLayout) fullLayout.findViewById(R.id.frame_layout_base);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(fullLayout);
        setView();
    }
}
