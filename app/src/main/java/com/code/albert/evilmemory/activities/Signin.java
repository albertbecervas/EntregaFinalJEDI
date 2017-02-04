package com.code.albert.evilmemory.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
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

import java.io.IOException;

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

    @BindView(R.id.user) EditText name;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.completeuser) EditText completename;
    @BindView(R.id.imageProfile) ImageView profile;

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

    public void startAnimation(){
        container = (LinearLayout) findViewById(R.id.activity_login);
        anim= (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }


    public void Signin(View v) {
        if (name.length() > 5 && password.length() > 5 && completename.length() > 5) {
            if (!Robot) {
                ContentValues valuesToStore = new ContentValues();
                valuesToStore.put("name", String.valueOf(name.getText()));

                valuesToStore.put("password", String.valueOf(password.getText()));
                valuesToStore.put("completename", String.valueOf(completename.getText()));

                editor.putBoolean("UserLoggedIn", true);
                editor.putString("username", name.getText().toString());
                editor.apply();

                loginHelper.createUser(valuesToStore, "Users");

                Toast.makeText(getApplicationContext(), "Welcome " + name.getText(), Toast.LENGTH_LONG).show();
                name.setText("");
                password.setText("");

                Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "get the fuck out MrRobot!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (name.length() < 5) name.setError("5 characters at least!");
            if (password.length() < 5) password.setError("5 characters at least!");
            if (completename.length() < 5) completename.setError("5 characters at least!");
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
        //Intent changeImage = new Intent(Intent.ACTION_GET_CONTENT, null);
        //changeImage.setType("image/*");

        //Este Intent define para el ACTION_PICK, la URI de donde cogerá los datos
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        //Usamos el Intent anterior para filtrar únicamente los que queremos que usen
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
                //data.getData();
                selectedImage = data.getData();
                /*final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                // Check for the freshest data.
                //Signin.grantUriPermission(getCallingActivity().getPackageName(), selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                getApplicationContext().getContentResolver().takePersistableUriPermission(selectedImage, takeFlags);*/
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


//TODO añadir permisos persistentes para coger las imágenes
