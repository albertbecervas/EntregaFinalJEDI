package com.code.albert.evilmemory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.data.LoginHelper;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Memory4 extends AppCompatActivity{

    Integer[] drawables = new Integer[16];

    LoginHelper loginHelper;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean mustWait=false;

    int intents = 0,card1,card2,pairs=0;

    boolean isFirst = true;
    boolean[] isVisible = new boolean[16];

    CoolImageFlipper flipper;

    View view0, view1;

    ButterKnife butterKnife;

    @BindDrawable(R.drawable.ic_fast_forward_black_24dp) Drawable backside;
    @BindView(R.id.intents) TextView attempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memory4);
        butterKnife.bind(this);

        flipper = new CoolImageFlipper(this);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setCards();
    }

    public void setCards() {

        drawables[0] = R.drawable.ic_angel;
        drawables[1] = R.drawable.ic_evil;
        drawables[2] = R.drawable.ic_angel_and_demon_;
        drawables[3] = R.drawable.ic_call_black_24dp;
        drawables[4] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[5] = R.drawable.ic_dialpad_black_24dp;
        drawables[6] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[7] = R.drawable.ic_explore_black_24dp;
        drawables[8] = R.drawable.ic_angel;
        drawables[9] = R.drawable.ic_evil;
        drawables[10] = R.drawable.ic_angel_and_demon_;
        drawables[11] = R.drawable.ic_call_black_24dp;
        drawables[12] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[13] = R.drawable.ic_dialpad_black_24dp;
        drawables[14] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[15] = R.drawable.ic_explore_black_24dp;
        shuffle();
    }

    public void shuffle(){
        List<Integer> cards =  Arrays.asList(drawables);
        Collections.shuffle(cards);
        cards.toArray(drawables);
    }

    public void action(View view, int i) {
        if (!mustWait) {
            if (!isVisible[i]) {
                if (isFirst) {
                    view0 = view;
                    card1 = i;
                    flipper(view0, card1);
                    isFirst = false;
                } else {
                    view1 = view;
                    card2 = i;
                    flipper(view1, card2);
                    intents++;
                    attempts.setText("" + intents);
                    mustWait = true;

                    Log.d("c1", "action: " + drawables[card1]);
                    Log.d("c2", "action: " + drawables[card2]);

                    if (drawables[card1].intValue() == drawables[card2].intValue()) {
                        Log.d("YEAH", "action: OHYESSS");
                        if ((pairs += 1) == drawables.length / 2) {
                            win(intents);
                        }
                        mustWait = false;
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flipper(view0, card1);
                                flipper(view1, card2);
                                Log.d("YEAH", "action: OHNOOO");
                                mustWait = false;
                            }
                        }, 2000);

                    }
                    isFirst = true;
                }
            }
        }
    }


    public void flipper(View view, int i) {
        if (!isVisible[i]) {
            flipper.flipImage(getResources().getDrawable(drawables[i]), ((ImageView) view));

        } else {
            flipper.flipImage(backside, ((ImageView) view));
        }
        isVisible[i] = !isVisible[i];

    }

    public void win(int intents) {
        loginHelper = new LoginHelper(this);
        String username = sharedPreferences.getString("username", "pepito");
        /*int score = loginHelper.getScore4(username);
        if(intents>score){

        }*/
        loginHelper.setScore4(username, intents);
        Toast.makeText(getApplicationContext(), "you won", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));

    }

    @OnClick({R.id.imageView0,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView8,R.id.imageView9
            ,R.id.imageView10,R.id.imageView11,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19
            ,R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27})
    public void on_click(View view){
        switch (view.getId()){
            case R.id.imageView0:
                action(view,0);
                break;

            case R.id.imageView1:
                action(view,1);
                break;

            case R.id.imageView2:
                action(view,2);
                break;

            case R.id.imageView3:
                action(view,3);
                break;

            case R.id.imageView8:
                action(view,4);
                break;

            case R.id.imageView9:
                action(view,5);
                break;

            case R.id.imageView10:
                action(view,6);
                break;

            case R.id.imageView11:
                action(view,7);
                break;

            case R.id.imageView16:
                action(view,8);
                break;

            case R.id.imageView17:
                action(view,9);
                break;

            case R.id.imageView18:
                action(view,10);
                break;

            case R.id.imageView19:
                action(view,11);
                break;

            case R.id.imageView24:
                action(view,12);
                break;

            case R.id.imageView25:
                action(view,13);
                break;

            case R.id.imageView26:
                action(view,14);
                break;

            case R.id.imageView27:
                action(view,15);
                break;
        }
    }

    // public void action(View view, ViewGroup linearLayout) {
    //  String tag=linearLayout.getTag().toString();
    // int i= Integer.parseInt(tag);

        /*Funcion toloca huss
    private void setListeners(ViewGroup linearLayout) {

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Log.d("hijos", "setListeners: "+ linearLayout.getChildCount());
            if (linearLayout.getChildAt(i) instanceof ViewGroup && linearLayout.getChildAt(i).getId()!= R.id.ignoreClick) {
                setListeners(((ViewGroup) linearLayout.getChildAt(i)));
                linearLayout.getChildAt(i).setOnClickListener(this);
                Log.d("click", "setListeners: "+i);
            }else if(linearLayout.getChildAt(i).getId() == R.id.ignoreClick){
                linearLayout.getChildAt(i).setOnClickListener(this);
                Log.d("clickNO", "setListeners: "+i);

            }
        }
    }*/




}

