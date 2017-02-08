package com.code.albert.evilmemory.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.activities.other.ImgActivity;
import com.code.albert.evilmemory.data.LoginHelper;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class Login extends AppCompatActivity{
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "MY7esAS3XVDRZ7QDiONPfuCyS";
    private static final String TWITTER_SECRET = "8eOF4WHY9pyXuP0coZIMyrvhkpEtvNQL3pLD4JYqC1eyn5vGHU";
    private TwitterLoginButton loginButton;

    LoginHelper loginHelper;
    EditText name, password;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    int fails;

    Boolean UserLoggedIn,keepin, StateIn;

    LinearLayout container;
    AnimationDrawable anim;

    ButterKnife butterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);
        butterKnife.bind(this);

        startAnimation();

        settings= getSharedPreferences("myApp", Context.MODE_PRIVATE);
        checkLogin();
        editor= settings.edit();

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;
                String name= session.getUserName();

                Log.d("username", "success: "+session.getUserName());
                String username = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                ContentValues valuesToStore = new ContentValues();
                valuesToStore.put("name", String.valueOf(name));
                valuesToStore.put("completename", String.valueOf(name));

                editor.putBoolean("UserLoggedIn", true);
                editor.putBoolean("keepin",true);
                editor.putString("username", session.getUserName());
                editor.apply();

                loginHelper.createUser(valuesToStore, "Users");
                startActivity(new Intent(getApplicationContext(),EvilMemory.class));
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });



        loginHelper = new LoginHelper(getApplicationContext());
        name = (EditText)findViewById(R.id.user);
        password =  (EditText) findViewById(R.id.password);

        fails=0;
    }

    public void startAnimation(){
        container = (LinearLayout) findViewById(R.id.activity_login);
        anim= (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void checkLogin(){
        UserLoggedIn = settings.getBoolean("UserLoggedIn", false);
        StateIn = settings.getBoolean("keepin", false);

        if(UserLoggedIn && StateIn){
            Intent intent = new Intent(this, EvilMemory.class);
            startActivity(intent);
            finish();
        }
    }

    public void Login(View v){
        Cursor cursor= loginHelper.getUserPassName(String.valueOf(name.getText().toString()));
        String enteredPassword = String.valueOf(password.getText().toString());
        String dbPassword = null;
        Boolean emptyFields=false;

        if(cursor.moveToFirst()){
            dbPassword = cursor.getString(cursor.getColumnIndex("password"));
        }

        if(name.getText().toString().matches("")) {
            name.setError("Empty username");
            emptyFields=true;
        }
        if(password.getText().toString().matches("")){
            password.setError("Empty password");
            emptyFields=true;
        }

        if(!emptyFields){
            if(enteredPassword.equals(dbPassword)){
                editor.putBoolean("UserLoggedIn", true);
                editor.putString("username",name.getText().toString());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), EvilMemory.class);
                startActivity(intent);
                finish();
            }else{

                ImageView image = new ImageView(this);
                image.setImageResource(R.mipmap.ic_intruso);

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this).
                                setMessage("Loggin Failure").
                                setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        fails++;
                                    }
                                });
                if(fails>1) {builder.setView(image);
                builder.setMessage("You are NOT welcome").
                    setPositiveButton("Please forgive me!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    fails++;
                                }});
                builder.create().show();
            }
        }
    }
    }

    public void goToSignin(View v){

        Intent intent = new Intent(getApplicationContext(), Signin.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.checkBox)
    public void checkKeep(CheckBox checkKeep){
        if(checkKeep.isChecked()){
            Toast.makeText(getApplicationContext(),"in", Toast.LENGTH_SHORT).show();
            keepin=true;
            editor.putBoolean("keepin", true);
        }else{
            Toast.makeText(getApplicationContext(),"out", Toast.LENGTH_SHORT).show();
            keepin=false;
            editor.putBoolean("keepin",false);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(anim != null && anim.isRunning()){
            anim.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
