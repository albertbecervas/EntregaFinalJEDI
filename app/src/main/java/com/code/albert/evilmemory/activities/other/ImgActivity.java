package com.code.albert.evilmemory.activities.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.activities.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImgActivity extends AppCompatActivity {


    ButterKnife butterKnife;
    @BindView(R.id.intruso) ImageView intruso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        butterKnife.bind(this);
    }

    @OnClick(R.id.retry)
    public void retry(){
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
