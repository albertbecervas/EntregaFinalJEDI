package com.code.albert.evilmemory.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.activities.EditProfile;
import com.code.albert.evilmemory.data.LoginHelper;
import com.code.albert.evilmemory.interfaces.OnFragmentInteractionListener;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Profile extends Fragment {

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    LoginHelper loginHelper;

    ButterKnife butterKnife;

    @BindView(R.id.imageViewProfile)
    ImageView profile;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.username)
    TextView uname;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.best4x4)
    TextView maxscore4;
    @BindView(R.id.best6x6)
    TextView maxscore6;
    @BindView(R.id.best8x8)
    TextView maxscore8;

    private Unbinder unbinder;

    AnimationDrawable anim;

    //OnFragmentInteractionListener mListener;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = butterKnife.bind(this, view);


        settings = getActivity().getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = settings.edit();

        loginHelper = new LoginHelper(getActivity().getApplicationContext());

        String username = settings.getString("username", "error");

        Cursor c = loginHelper.getUserData(username);
        String completename = null;
        String location = null;
        String score4 = null;
        String score6 = null;
        String score8 = null;


        if (c.moveToFirst()) {
            completename = c.getString(c.getColumnIndex("completename"));
            location = c.getString(c.getColumnIndex("address"));
            score4 = c.getString(c.getColumnIndex("score4"));
            score6 = c.getString(c.getColumnIndex("score6"));
            score8 = c.getString(c.getColumnIndex("score8"));
        }

        name.setText(completename);
        uname.setText(username);
        address.setText(location);
        maxscore4.setText(score4);
        maxscore6.setText(score6);
        maxscore8.setText(score8);

        anim= (AnimationDrawable) view.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.start();

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);

        }*/

        String s = settings.getString("s", "jvkbn");
        Log.d("URI", "onCreate: " + s);
        Uri myUri = Uri.parse(s);
        try {
            Log.d("URI", "onCreate: " + s);
            profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), myUri));
        } catch (IOException e) {
            Log.d("error", "onCreate: " + s);
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.edit_profile)
    public void editProfile(){
        startActivity(new Intent(getActivity().getApplicationContext(),EditProfile.class));
        getActivity().finish();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

       // mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
