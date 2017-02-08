package com.code.albert.evilmemory.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import static com.code.albert.evilmemory.R.id.imageView0;

public class Memory4 extends AppCompatActivity implements View.OnClickListener{

    Integer[] drawables = new Integer[16];

    LoginHelper loginHelper;

    ImageView[] iv = new ImageView[16];

    ImageView reload;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean mustWait=false;

    int intents = 0,card1,card2,pairs=0;

    boolean isFirst = true;
    boolean[] isVisible = new boolean[16];

    CoolImageFlipper flipper;

    View view0, view1;

    ButterKnife butterKnife;

    ProgressBar progressbar;

    int setLevel,max;

    @BindDrawable(R.drawable.ic_fast_forward_black_24dp) Drawable backside;
    @BindView(R.id.intents) TextView attempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memory4);
        butterKnife.bind(this);

        flipper = new CoolImageFlipper(this);

        loginHelper = new LoginHelper(getApplicationContext());

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Boolean premium=sharedPreferences.getBoolean("Premium package", false);
        setLevel=sharedPreferences.getInt("Level", 1);
        if(setLevel==1) max=40;
        if(setLevel==2) max=30;
        if(setLevel==3) max=20;





        progressbar = (ProgressBar) findViewById(R.id.progressBarIntents);
        progressbar.setMax(max);
        progressbar.setProgress(intents);
        setImagesView();
        if(!premium) {
            setCards();
        }else{
            setPremiumCards();
        }
    }

    public void setImagesView(){
        iv[0] = (ImageView) findViewById(R.id.imageView0);
        iv[1] = (ImageView) findViewById(R.id.imageView1);
        iv[2] = (ImageView) findViewById(R.id.imageView2);
        iv[3] = (ImageView) findViewById(R.id.imageView3);
        iv[4] = (ImageView) findViewById(R.id.imageView8);
        iv[5] = (ImageView) findViewById(R.id.imageView9);
        iv[6] = (ImageView) findViewById(R.id.imageView10);
        iv[7] = (ImageView) findViewById(R.id.imageView11);
        iv[8] = (ImageView) findViewById(R.id.imageView16);
        iv[9] = (ImageView) findViewById(R.id.imageView17);
        iv[10] = (ImageView) findViewById(R.id.imageView18);
        iv[11] = (ImageView) findViewById(R.id.imageView19);
        iv[12] = (ImageView) findViewById(R.id.imageView24);
        iv[13] = (ImageView) findViewById(R.id.imageView25);
        iv[14] = (ImageView) findViewById(R.id.imageView26);
        iv[15] = (ImageView) findViewById(R.id.imageView27);
        reload=(ImageView) findViewById(R.id.reload);

        iv[0] .setOnClickListener(this);
        iv[1] .setOnClickListener(this);
        iv[2] .setOnClickListener(this);
        iv[3] .setOnClickListener(this);
        iv[4] .setOnClickListener(this);
        iv[5] .setOnClickListener(this);
        iv[6] .setOnClickListener(this);
        iv[7] .setOnClickListener(this);
        iv[8] .setOnClickListener(this);
        iv[9] .setOnClickListener(this);
        iv[10].setOnClickListener(this);
        iv[11].setOnClickListener(this);
        iv[12].setOnClickListener(this);
        iv[13].setOnClickListener(this);
        iv[14].setOnClickListener(this);
        iv[15].setOnClickListener(this);
        reload.setOnClickListener(this);
    }

    public void setCards() {
        drawables[0] = R.drawable.ic_android;
        drawables[1] = R.drawable.ic_instagram;
        drawables[2] = R.drawable.ic_facebook;
        drawables[3] = R.drawable.ic_skype;
        drawables[4] = R.drawable.ic_snapchat;
        drawables[5] = R.drawable.ic_telegram;
        drawables[6] = R.drawable.ic_github;
        drawables[7] = R.drawable.ic_flickr;
        drawables[8] = R.drawable.ic_android;
        drawables[9] = R.drawable.ic_instagram;
        drawables[10] = R.drawable.ic_facebook;
        drawables[11] = R.drawable.ic_skype;
        drawables[12] = R.drawable.ic_snapchat;
        drawables[13] = R.drawable.ic_telegram;
        drawables[14] = R.drawable.ic_github;
        drawables[15] = R.drawable.ic_flickr;
        //shuffle();
    }

    public void shuffle(){
        List<Integer> cards =  Arrays.asList(drawables);
        Collections.shuffle(cards);
        cards.toArray(drawables);
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putBoolean("isFirst", isFirst);
        outstate.putBooleanArray("isvisible", isVisible);
        outstate.putInt("card1", card1);
        outstate.putInt("card2", card2);
        outstate.putInt("attempts", intents);
        outstate.putInt("pairs",pairs);
        outstate.putString("intents", attempts.getText().toString());
        outstate.putInt("progressbar", progressbar.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        isFirst = outstate.getBoolean("isFirst");
        isVisible = outstate.getBooleanArray("isvisible");
        intents = outstate.getInt("attempts");
        card1 = outstate.getInt("card1");
        card2 = outstate.getInt("card2");
        pairs = outstate.getInt("pairs");
        attempts.setText(outstate.getString("intents"));
        progressbar.setProgress(outstate.getInt("progressbar"));

        for (int i = 0; i < drawables.length; ++i) {
            if (isVisible[i]) iv[i].setImageDrawable(getResources().getDrawable(drawables[i]));
        }
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

                    if (drawables[card1].intValue() == drawables[card2].intValue()) {
                        Log.d("YEAH", "action: OHYESSS");
                        if ((pairs += 1) == drawables.length / 2) {
                            win(intents);
                        }
                        progressbar.setProgress(intents);
                        mustWait = false;
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flipper(view0, card1);
                                flipper(view1, card2);
                                Log.d("YEAH", "action: OHNOOO");
                                mustWait = false;
                                progressbar.setProgress(intents);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory4.this);
        String username = sharedPreferences.getString("username", "unknown");
        Log.d("username", "win: "+username);

        int better = loginHelper.getBetter4(username);

        if (intents < better || better == 0) {
            loginHelper.setScore4(username, intents);
            builder.setMessage("Can you do ir better?");
            builder.setTitle("OH MY GAT");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    reload(iv);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    startActivity(new Intent(getApplicationContext(), EvilMemory.class));
                    finish();
                }
            });

        }else{
            builder.setMessage("I've seen better...");
            builder.setTitle("Are you handsome?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    reload(iv);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    startActivity(new Intent(getApplicationContext(), EvilMemory.class));
                    finish();
                }
            });

        }


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void reload(View[] view){
        for (int i = 0; i < drawables.length; i++) {
            isVisible[i]=false;
        }
        for (int i = 0; i < drawables.length; i++) {
            flipper.flipImage(backside, (ImageView) view[i]);
        }
        progressbar.setProgress(0);
        isFirst=true;
        intents=0;
        attempts.setText("0");
        pairs=0;
        card1=0;
        card2=0;

        shuffle();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView0:
                action(view, 0);
                break;

            case R.id.imageView1:
                action(view, 1);
                break;

            case R.id.imageView2:
                action(view, 2);
                break;

            case R.id.imageView3:
                action(view, 3);
                break;

            case R.id.imageView8:
                action(view, 4);
                break;

            case R.id.imageView9:
                action(view, 5);
                break;

            case R.id.imageView10:
                action(view, 6);
                break;

            case R.id.imageView11:
                action(view, 7);
                break;

            case R.id.imageView16:
                action(view, 8);
                break;

            case R.id.imageView17:
                action(view, 9);
                break;

            case R.id.imageView18:
                action(view, 10);
                break;

            case R.id.imageView19:
                action(view, 11);
                break;

            case R.id.imageView24:
                action(view, 12);
                break;

            case R.id.imageView25:
                action(view, 13);
                break;

            case R.id.imageView26:
                action(view, 14);
                break;

            case R.id.imageView27:
                action(view, 15);
                break;

            case  R.id.reload:
                reload(iv);
                break;
        }

        if (progressbar.getProgress() == progressbar.getMax()){
            looser();
        }

    }

    public void looser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory4.this);

            builder.setMessage("LOOSER");
            builder.setPositiveButton("try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    reload(iv);

                }
            });
            builder.setNegativeButton("Bye", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int witch) {
                    startActivity(new Intent(getApplicationContext(), EvilMemory.class));
                    finish();
                }
            });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setPremiumCards() {

        drawables[0] = R.drawable.barca;
        drawables[1] = R.drawable.atmadrid;
        drawables[2] = R.drawable.madrid;
        drawables[3] = R.drawable.arsenal;
        drawables[4] = R.drawable.liverpool;
        drawables[5] = R.drawable.chelsea;
        drawables[6] = R.drawable.juve;
        drawables[7] = R.drawable.tits;
        drawables[8] = R.drawable.barca;
        drawables[9] = R.drawable.atmadrid;
        drawables[10] = R.drawable.madrid;
        drawables[11] = R.drawable.arsenal;
        drawables[12] = R.drawable.liverpool;
        drawables[13] = R.drawable.chelsea;
        drawables[14] = R.drawable.juve;
        drawables[15] = R.drawable.tits;
        //shuffle();
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

