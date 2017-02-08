package com.code.albert.evilmemory.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.code.albert.evilmemory.data.LoginHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Signin extends AppCompatActivity {

    LoginHelper loginHelper;

    Uri selectedImage;

    Boolean Robot;

    LinearLayout container;
    AnimationDrawable anim;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    ButterKnife butterKnife;

    @BindView(R.id.user)
    EditText name;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.completeuser)
    EditText completename;
    @BindView(R.id.imageProfile)
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        butterKnife.bind(this);

        startAnimation();

        Robot = true;

        settings = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = settings.edit();

        loginHelper = new LoginHelper(getApplicationContext());
    }

    public void startAnimation() {
        container = (LinearLayout) findViewById(R.id.activity_login);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }

    public void Signin(View v) {
        Cursor cursor = loginHelper.getUserName(String.valueOf(name.getText().toString()));

        if (name.length() > 3 && password.length() > 5 && completename.length() > 2) {
            if (!Robot) {
                if (!cursor.moveToFirst()) {

                    ContentValues valuesToStore = new ContentValues();
                    valuesToStore.put("name", String.valueOf(name.getText()));

                    valuesToStore.put("password", String.valueOf(password.getText()));
                    valuesToStore.put("completename", String.valueOf(completename.getText()));

                    editor.putBoolean("UserLoggedIn", true);
                    editor.putBoolean("keepin", true);
                    editor.putString("username", name.getText().toString());
                    editor.apply();

                    loginHelper.createUser(valuesToStore, "Users");

                    Toast.makeText(getApplicationContext(), "Welcome " + name.getText(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), EvilMemory.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Entered user already exists!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "get the fuck out MrRobot!", Toast.LENGTH_SHORT).show();
            }
        } else {
            name.setError("5 characters at least!");
            password.setError("5 characters at least!");
            completename.setError("5 characters at least!");
        }
    }

    @OnClick(R.id.mrrobot)
    public void checkKeep(CheckBox checkKeep) {
        if (checkKeep.isChecked()) {
            Robot = false;
        } else {
            Robot = true;
        }
    }

    @OnClick(R.id.fab)
    public void profile_picture() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        startActivityForResult(chooserIntent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                selectedImage = data.getData();

                String s = selectedImage.toString();
                Log.d("uri", "onActivityResult: " + s);
                editor.putString("s", s);
                editor.apply();
                try {
                    profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.v("Result", "Something happened");
        }
    }
}
