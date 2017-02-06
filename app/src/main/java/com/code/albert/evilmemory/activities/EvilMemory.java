package com.code.albert.evilmemory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.albert.evilmemory.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EvilMemory extends NavigationDrawer {

    ButterKnife butterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evil_memory);
        butterKnife.bind(this);
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
}
