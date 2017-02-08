package com.code.albert.evilmemory.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.data.LoginHelper;
import com.code.albert.evilmemory.fragments.Profile;
import com.code.albert.evilmemory.interfaces.OnFragmentInteractionListener;
import com.code.albert.evilmemory.service.BoundService;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfile extends AppCompatActivity implements View.OnClickListener, OnFragmentInteractionListener {

    Context context=this;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LoginHelper loginHelper;

    Uri selectedImage;

    LinearLayout container;
    AnimationDrawable anim;

    BoundService bService;
    boolean bound = false;
    Intent intent;
    Boolean GPSon=false;

    private ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder binder = (BoundService.MyBinder) iBinder;
            bService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0){
            bound = false;
        }
    };

    ButterKnife butterKnife;
    @BindView(R.id.completeuser)
    EditText name;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.profile_picture)
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        butterKnife.bind(this);

        intent = new Intent(EditProfile.this, BoundService.class);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loginHelper = new LoginHelper(getApplicationContext());

        startAnimation();
    }

    public void startAnimation(){
        container = (LinearLayout) findViewById(R.id.activity_login);
        anim= (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();
    }

    @OnClick({R.id.modify, R.id.fab, R.id.fabaddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify:
                saveChanges();
                break;
            case R.id.fab:
                Intent changeImage = new Intent(Intent.ACTION_GET_CONTENT, null);
                changeImage.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(changeImage, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 1);
                break;
            case R.id.fabaddress:
                if(GPSon){
                bService.getGPS(context);
                }else{
                    Toast.makeText(getApplicationContext(),"Please, turn on GPS",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @OnClick(R.id.switch1)
    public void switcher(Switch onoff) {
        if (onoff.isChecked()) {
            GPSon=true;
            Toast.makeText(getApplicationContext(), "GPS on", Toast.LENGTH_SHORT).show();
        } else {
            GPSon=false;
            stopService(new Intent(EditProfile.this, BoundService.class));
            Toast.makeText(getApplicationContext(), "GPS off", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveChanges() {
        String cname = String.valueOf(name.getText());
        String pass = String.valueOf(password.getText());
        String add = String.valueOf(address.getText());

        if (cname != "" && cname.length() > 2) {
            loginHelper.modifyName(cname);
        }
        if (pass != "" && pass.length() > 5) {
            loginHelper.modifyPassword(pass);
        }
        if (add != "" && add.length() > 1) {
            loginHelper.modifyAddress(add);
        }

        Toast.makeText(getApplicationContext(), "User settings modified" + name.getText(), Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(), EvilMemory.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                data.getData();
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

    @Override
    public void getDirection(List<Address> addressList) {

        for (int i = 0; i < addressList.size(); ++i) {
            TextView t = (TextView) findViewById(R.id.address);

            if (i == 0) t.setText("");
            t.setText(t.getText() + "\n" + addressList.get(i).getAddressLine(0));
        }

    }

}

