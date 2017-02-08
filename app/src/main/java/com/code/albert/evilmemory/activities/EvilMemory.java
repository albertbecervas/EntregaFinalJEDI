package com.code.albert.evilmemory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.code.albert.evilmemory.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EvilMemory extends NavigationDrawer {

    ButterKnife butterKnife;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    RadioButton social;
    RadioButton level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evil_memory);
        butterKnife.bind(this);

        social = (RadioButton) findViewById(R.id.radio_pirates);
        social.setChecked(true);

        level = (RadioButton) findViewById(R.id.radio_1);
        level.setChecked(true);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("Premium package",false);
        editor.putInt("Level",1);
        editor.apply();
    }

    @OnClick({R.id.easy,R.id.medium,R.id.hard})
    public void on_click(View view){
        switch (view.getId()){
            case R.id.easy:
                startActivity(new Intent(getApplicationContext(), Memory4.class));
                break;
            case R.id.medium:
                startActivity(new Intent(getApplicationContext(), Memory6.class));
                break;
            case R.id.hard:
                startActivity(new Intent(getApplicationContext(), Memory8.class));
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    editor.putBoolean("Premium package", false);
                    editor.apply();
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    editor.putBoolean("Premium package", true);
                    editor.apply();
                    break;
        }
    }

    public void onRadioButtonClickedLevel(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1:
                if (checked){
                    editor.putInt("Level", 1);
                    editor.apply();}
                break;
            case R.id.radio_2:
                if (checked){
                    editor.putInt("Level", 2);
                    editor.apply();}
                break;
            case R.id.radio_3:
                if (checked){
                    editor.putInt("Level", 3);
                    editor.apply();}
                break;
        }
    }
}
