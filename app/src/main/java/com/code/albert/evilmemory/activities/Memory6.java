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

public class Memory6 extends AppCompatActivity implements View.OnClickListener{

    Integer[] drawables = new Integer[36];

    LoginHelper loginHelper;

    ImageView reload;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean mustWait=false;

    int intents = 0,card1,card2,pairs=0;

    ImageView[] iv = new ImageView[36];

    boolean isFirst = true;
    boolean[] isVisible = new boolean[36];

    CoolImageFlipper flipper;

    View view0, view1;

    ProgressBar progressbar;

    ButterKnife butterKnife;

    @BindDrawable(R.drawable.ic_fast_forward_black_24dp) Drawable backside;
    @BindView(R.id.intents) TextView attempts;

    int setLevel,max;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory6);
        butterKnife.bind(this);

        flipper = new CoolImageFlipper(this);

        loginHelper = new LoginHelper(getApplicationContext());

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Boolean premium=sharedPreferences.getBoolean("Premium package", false);
        setLevel=sharedPreferences.getInt("Level", 1);
        if(setLevel==1) max=80;
        if(setLevel==2) max=70;
        if(setLevel==3) max=60;

        progressbar = (ProgressBar) findViewById(R.id.progressBarIntents);
        progressbar.setMax(60);
        progressbar.setProgress(intents);

        setImagesView();

        if(!premium) {
            setCards();
        }else{
            setPremiumCards();
        }
    }

    public void setImagesView(){
        iv[0]  = (ImageView) findViewById(R.id.imageView0);
        iv[1]  = (ImageView) findViewById(R.id.imageView1);
        iv[2]  = (ImageView) findViewById(R.id.imageView2);
        iv[3]  = (ImageView) findViewById(R.id.imageView3);
        iv[4]  = (ImageView) findViewById(R.id.imageView4);
        iv[5]  = (ImageView) findViewById(R.id.imageView5);
        iv[6]  = (ImageView) findViewById(R.id.imageView8);
        iv[7]  = (ImageView) findViewById(R.id.imageView9);
        iv[8]  = (ImageView) findViewById(R.id.imageView10);
        iv[9]  = (ImageView) findViewById(R.id.imageView11);
        iv[10] = (ImageView) findViewById(R.id.imageView12);
        iv[11] = (ImageView) findViewById(R.id.imageView13);
        iv[12] = (ImageView) findViewById(R.id.imageView16);
        iv[13] = (ImageView) findViewById(R.id.imageView17);
        iv[14] = (ImageView) findViewById(R.id.imageView18);
        iv[15] = (ImageView) findViewById(R.id.imageView19);
        iv[16] = (ImageView) findViewById(R.id.imageView20);
        iv[17] = (ImageView) findViewById(R.id.imageView21);
        iv[18] = (ImageView) findViewById(R.id.imageView24);
        iv[19] = (ImageView) findViewById(R.id.imageView25);
        iv[20] = (ImageView) findViewById(R.id.imageView26);
        iv[21] = (ImageView) findViewById(R.id.imageView27);
        iv[22] = (ImageView) findViewById(R.id.imageView28);
        iv[23] = (ImageView) findViewById(R.id.imageView29);
        iv[24] = (ImageView) findViewById(R.id.imageView32);
        iv[25] = (ImageView) findViewById(R.id.imageView33);
        iv[26] = (ImageView) findViewById(R.id.imageView34);
        iv[27] = (ImageView) findViewById(R.id.imageView35);
        iv[28] = (ImageView) findViewById(R.id.imageView36);
        iv[29] = (ImageView) findViewById(R.id.imageView37);
        iv[30] = (ImageView) findViewById(R.id.imageView40);
        iv[31] = (ImageView) findViewById(R.id.imageView41);
        iv[32] = (ImageView) findViewById(R.id.imageView42);
        iv[33] = (ImageView) findViewById(R.id.imageView43);
        iv[34] = (ImageView) findViewById(R.id.imageView44);
        iv[35] = (ImageView) findViewById(R.id.imageView45);
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
        iv[16].setOnClickListener(this);
        iv[17].setOnClickListener(this);
        iv[18].setOnClickListener(this);
        iv[19].setOnClickListener(this);
        iv[20].setOnClickListener(this);
        iv[21].setOnClickListener(this);
        iv[22].setOnClickListener(this);
        iv[23].setOnClickListener(this);
        iv[24].setOnClickListener(this);
        iv[25].setOnClickListener(this);
        iv[26].setOnClickListener(this);
        iv[27].setOnClickListener(this);
        iv[28].setOnClickListener(this);
        iv[29].setOnClickListener(this);
        iv[30].setOnClickListener(this);
        iv[31].setOnClickListener(this);
        iv[32].setOnClickListener(this);
        iv[33].setOnClickListener(this);
        iv[34].setOnClickListener(this);
        iv[35].setOnClickListener(this);
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
        drawables[8] = R.drawable.ic_linkedin;
        drawables[9] = R.drawable.ic_twitter;
        drawables[10] = R.drawable.ic_amazon;
        drawables[11] = R.drawable.ic_drive;
        drawables[12] = R.drawable.ic_evernote;
        drawables[13] = R.drawable.ic_google_hangouts_logo;
        drawables[14] = R.drawable.ic_lastfm;
        drawables[15] = R.drawable.ic_messenger;
        drawables[16] = R.drawable.ic_periscope;
        drawables[17] = R.drawable.ic_pinterest;
        drawables[18] = R.drawable.ic_android;
        drawables[19] = R.drawable.ic_instagram;
        drawables[20] = R.drawable.ic_facebook;
        drawables[21] = R.drawable.ic_skype;
        drawables[22] = R.drawable.ic_snapchat;
        drawables[23] = R.drawable.ic_telegram;
        drawables[24] = R.drawable.ic_github;
        drawables[25] = R.drawable.ic_flickr;
        drawables[26] = R.drawable.ic_linkedin;
        drawables[27] = R.drawable.ic_twitter;
        drawables[28] = R.drawable.ic_amazon;
        drawables[29] = R.drawable.ic_drive;
        drawables[30] = R.drawable.ic_evernote;
        drawables[31] = R.drawable.ic_google_hangouts_logo;
        drawables[32] = R.drawable.ic_lastfm;
        drawables[33] = R.drawable.ic_messenger;
        drawables[34] = R.drawable.ic_periscope;
        drawables[35] = R.drawable.ic_pinterest;

        shuffle();
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
    protected void onRestoreInstanceState(Bundle outstate) {
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

                    Log.d("c1", "action: " + drawables[card1]);
                    Log.d("c2", "action: " + drawables[card2]);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory6.this);
        String username = sharedPreferences.getString("username", "unknown");
        Log.d("username", "win: "+username);

        int better = loginHelper.getBetter6(username);

        if (intents < better || better == 0) {
            loginHelper.setScore6(username, intents);
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

            case R.id.imageView4:
                action(view,4);
                break;

            case R.id.imageView5:
                action(view,5);
                break;

            case R.id.imageView8:
                action(view,6);
                break;

            case R.id.imageView9:
                action(view,7);
                break;

            case R.id.imageView10:
                action(view,8);
                break;

            case R.id.imageView11:
                action(view,9);
                break;

            case R.id.imageView12:
                action(view,10);
                break;

            case R.id.imageView13:
                action(view,11);
                break;

            case R.id.imageView16:
                action(view,12);
                break;

            case R.id.imageView17:
                action(view,13);
                break;

            case R.id.imageView18:
                action(view,14);
                break;

            case R.id.imageView19:
                action(view,15);
                break;

            case R.id.imageView20:
                action(view,16);
                break;

            case R.id.imageView21:
                action(view,17);
                break;

            case R.id.imageView24:
                action(view,18);
                break;

            case R.id.imageView25:
                action(view,19);
                break;

            case R.id.imageView26:
                action(view,20);
                break;

            case R.id.imageView27:
                action(view,21);
                break;

            case R.id.imageView28:
                action(view,22);
                break;

            case R.id.imageView29:
                action(view,23);
                break;

            case R.id.imageView32:
                action(view,24);
                break;

            case R.id.imageView33:
                action(view,25);
                break;

            case R.id.imageView34:
                action(view,26);
                break;

            case R.id.imageView35:
                action(view,27);
                break;

            case R.id.imageView36:
                action(view,28);
                break;

            case R.id.imageView37:
                action(view,29);
                break;

            case R.id.imageView40:
                action(view,30);
                break;

            case R.id.imageView41:
                action(view,31);
                break;

            case R.id.imageView42:
                action(view,32);
                break;

            case R.id.imageView43:
                action(view,33);
                break;

            case R.id.imageView44:
                action(view,34);
                break;

            case R.id.imageView45:
                action(view,35);
                break;

            case R.id.reload:
                reload(iv);
                break;

        }
        if (progressbar.getProgress() == progressbar.getMax()){
            looser();
        }
    }

    public void looser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory6.this);

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
        drawables[8] = R.drawable.villareal;
        drawables[9] = R.drawable.santcu;
        drawables[10] = R.drawable.galatasaray;
        drawables[11] = R.drawable.celtic;
        drawables[12] = R.drawable.ajax;
        drawables[13] = R.drawable.psg;
        drawables[14] = R.drawable.monaco;
        drawables[15] = R.drawable.bayernmun;
        drawables[16] = R.drawable.borussia;
        drawables[17] = R.drawable.everton;
        drawables[18] = R.drawable.barca;
        drawables[19] = R.drawable.atmadrid;
        drawables[20] = R.drawable.madrid;
        drawables[21] = R.drawable.arsenal;
        drawables[22] = R.drawable.liverpool;
        drawables[23] = R.drawable.chelsea;
        drawables[24] = R.drawable.juve;
        drawables[25] = R.drawable.tits;
        drawables[26] = R.drawable.villareal;
        drawables[27] = R.drawable.santcu;
        drawables[28] = R.drawable.galatasaray;
        drawables[29] = R.drawable.celtic;
        drawables[30] = R.drawable.ajax;
        drawables[31] = R.drawable.psg;
        drawables[32] = R.drawable.monaco;
        drawables[33] = R.drawable.bayernmun;
        drawables[34] = R.drawable.borussia;
        drawables[35] = R.drawable.everton;
        shuffle();
    }

}

