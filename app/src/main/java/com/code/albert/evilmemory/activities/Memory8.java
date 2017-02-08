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

public class Memory8 extends AppCompatActivity implements View.OnClickListener{


    Integer[] drawables = new Integer[64];

    LoginHelper loginHelper;

    ImageView[] iv = new ImageView[64];

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean mustWait=false;

    int intents = 0,card1,card2,pairs=0;

    boolean isFirst = true;
    boolean[] isVisible = new boolean[64];

    CoolImageFlipper flipper;

    View view0, view1;

    ButterKnife butterKnife;

    ProgressBar  progressbar;

    @BindDrawable(R.drawable.ic_fast_forward_black_24dp)
    Drawable backside;
    @BindView(R.id.intents)
    TextView attempts;

    int setLevel,max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory8);
        butterKnife.bind(this);

        flipper = new CoolImageFlipper(this);

        loginHelper = new LoginHelper(getApplicationContext());

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Boolean premium=sharedPreferences.getBoolean("Premium package", false);
        setLevel=sharedPreferences.getInt("Level", 1);
        if(setLevel==1) max=150;
        if(setLevel==2) max=140;
        if(setLevel==3) max=130;

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
        iv[6]  = (ImageView) findViewById(R.id.imageView6);
        iv[7]  = (ImageView) findViewById(R.id.imageView7);
        iv[8]  = (ImageView) findViewById(R.id.imageView8);
        iv[9]  = (ImageView) findViewById(R.id.imageView9);
        iv[10] = (ImageView) findViewById(R.id.imageView10);
        iv[11] = (ImageView) findViewById(R.id.imageView11);
        iv[12] = (ImageView) findViewById(R.id.imageView12);
        iv[13] = (ImageView) findViewById(R.id.imageView13);
        iv[14] = (ImageView) findViewById(R.id.imageView14);
        iv[15] = (ImageView) findViewById(R.id.imageView15);
        iv[16] = (ImageView) findViewById(R.id.imageView16);
        iv[17] = (ImageView) findViewById(R.id.imageView17);
        iv[18] = (ImageView) findViewById(R.id.imageView18);
        iv[19] = (ImageView) findViewById(R.id.imageView19);
        iv[20] = (ImageView) findViewById(R.id.imageView20);
        iv[21] = (ImageView) findViewById(R.id.imageView21);
        iv[22] = (ImageView) findViewById(R.id.imageView22);
        iv[23] = (ImageView) findViewById(R.id.imageView23);
        iv[24] = (ImageView) findViewById(R.id.imageView24);
        iv[25] = (ImageView) findViewById(R.id.imageView25);
        iv[26] = (ImageView) findViewById(R.id.imageView26);
        iv[27] = (ImageView) findViewById(R.id.imageView27);
        iv[28] = (ImageView) findViewById(R.id.imageView28);
        iv[29] = (ImageView) findViewById(R.id.imageView29);
        iv[30] = (ImageView) findViewById(R.id.imageView30);
        iv[31] = (ImageView) findViewById(R.id.imageView31);
        iv[32] = (ImageView) findViewById(R.id.imageView32);
        iv[33] = (ImageView) findViewById(R.id.imageView33);
        iv[34] = (ImageView) findViewById(R.id.imageView34);
        iv[35] = (ImageView) findViewById(R.id.imageView35);
        iv[36] = (ImageView) findViewById(R.id.imageView36);
        iv[37] = (ImageView) findViewById(R.id.imageView37);
        iv[38] = (ImageView) findViewById(R.id.imageView38);
        iv[39] = (ImageView) findViewById(R.id.imageView39);
        iv[40] = (ImageView) findViewById(R.id.imageView40);
        iv[41] = (ImageView) findViewById(R.id.imageView41);
        iv[42] = (ImageView) findViewById(R.id.imageView42);
        iv[43] = (ImageView) findViewById(R.id.imageView43);
        iv[44] = (ImageView) findViewById(R.id.imageView44);
        iv[45] = (ImageView) findViewById(R.id.imageView45);
        iv[46] = (ImageView) findViewById(R.id.imageView46);
        iv[47] = (ImageView) findViewById(R.id.imageView47);
        iv[48] = (ImageView) findViewById(R.id.imageView48);
        iv[49] = (ImageView) findViewById(R.id.imageView49);
        iv[50] = (ImageView) findViewById(R.id.imageView50);
        iv[51] = (ImageView) findViewById(R.id.imageView51);
        iv[52] = (ImageView) findViewById(R.id.imageView52);
        iv[53] = (ImageView) findViewById(R.id.imageView53);
        iv[54] = (ImageView) findViewById(R.id.imageView54);
        iv[55] = (ImageView) findViewById(R.id.imageView55);
        iv[56] = (ImageView) findViewById(R.id.imageView56);
        iv[57] = (ImageView) findViewById(R.id.imageView57);
        iv[58] = (ImageView) findViewById(R.id.imageView58);
        iv[59] = (ImageView) findViewById(R.id.imageView59);
        iv[60] = (ImageView) findViewById(R.id.imageView60);
        iv[61] = (ImageView) findViewById(R.id.imageView61);
        iv[62] = (ImageView) findViewById(R.id.imageView62);
        iv[63] = (ImageView) findViewById(R.id.imageView63);

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
        iv[36].setOnClickListener(this);
        iv[37].setOnClickListener(this);
        iv[38].setOnClickListener(this);
        iv[39].setOnClickListener(this);
        iv[40].setOnClickListener(this);
        iv[41].setOnClickListener(this);
        iv[42].setOnClickListener(this);
        iv[43].setOnClickListener(this);
        iv[44].setOnClickListener(this);
        iv[45].setOnClickListener(this);
        iv[46].setOnClickListener(this);
        iv[47].setOnClickListener(this);
        iv[48].setOnClickListener(this);
        iv[49].setOnClickListener(this);
        iv[50].setOnClickListener(this);
        iv[51].setOnClickListener(this);
        iv[52].setOnClickListener(this);
        iv[53].setOnClickListener(this);
        iv[54].setOnClickListener(this);
        iv[55].setOnClickListener(this);
        iv[56].setOnClickListener(this);
        iv[57].setOnClickListener(this);
        iv[58].setOnClickListener(this);
        iv[59].setOnClickListener(this);
        iv[60].setOnClickListener(this);
        iv[61].setOnClickListener(this);
        iv[62].setOnClickListener(this);
        iv[63].setOnClickListener(this);
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
        drawables[18] = R.drawable.ic_google;
        drawables[19] = R.drawable.ic_chrome;
        drawables[20] = R.drawable.ic_dropbox;
        drawables[21] = R.drawable.ic_firefox;
        drawables[22] = R.drawable.ic_microsoft;
        drawables[23] = R.drawable.ic_opera;
        drawables[24] = R.drawable.ic_photoshop;
        drawables[25] = R.drawable.ic_playstore;
        drawables[26] = R.drawable.ic_soundcloud;
        drawables[27] = R.drawable.ic_spotify;
        drawables[28] = R.drawable.ic_tuenti_social_logo;
        drawables[29] = R.drawable.ic_tumblr;
        drawables[30] = R.drawable.ic_vimeo;
        drawables[31] = R.drawable.ic_youtube;
        drawables[32] = R.drawable.ic_android;
        drawables[33] = R.drawable.ic_instagram;
        drawables[34] = R.drawable.ic_facebook;
        drawables[35] = R.drawable.ic_skype;
        drawables[36] = R.drawable.ic_snapchat;
        drawables[37] = R.drawable.ic_telegram;
        drawables[38] = R.drawable.ic_github;
        drawables[39] = R.drawable.ic_flickr;
        drawables[40] = R.drawable.ic_linkedin;
        drawables[41] = R.drawable.ic_twitter;
        drawables[42] = R.drawable.ic_amazon;
        drawables[43] = R.drawable.ic_drive;
        drawables[44] = R.drawable.ic_evernote;
        drawables[45] = R.drawable.ic_google_hangouts_logo;
        drawables[46] = R.drawable.ic_lastfm;
        drawables[47] = R.drawable.ic_messenger;
        drawables[48] = R.drawable.ic_periscope;
        drawables[49] = R.drawable.ic_pinterest;
        drawables[50] = R.drawable.ic_google;
        drawables[51] = R.drawable.ic_chrome;
        drawables[52] = R.drawable.ic_dropbox;
        drawables[53] = R.drawable.ic_firefox;
        drawables[54] = R.drawable.ic_microsoft;
        drawables[55] = R.drawable.ic_opera;
        drawables[56] = R.drawable.ic_photoshop;
        drawables[57] = R.drawable.ic_playstore;
        drawables[58] = R.drawable.ic_soundcloud;
        drawables[59] = R.drawable.ic_spotify;
        drawables[60] = R.drawable.ic_tuenti_social_logo;
        drawables[61] = R.drawable.ic_tumblr;
        drawables[62] = R.drawable.ic_vimeo;
        drawables[63] = R.drawable.ic_youtube;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory8.this);
        String username = sharedPreferences.getString("username", "unknown");
        Log.d("username", "win: "+username);

        int better = loginHelper.getBetter8(username);

        if (intents < better || better == 0) {
            loginHelper.setScore8(username, intents);
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

                case R.id.imageView4:
                    action(view, 4);
                    break;

                case R.id.imageView5:
                    action(view, 5);
                    break;

                case R.id.imageView6:
                    action(view, 6);
                    break;

                case R.id.imageView7:
                    action(view, 7);
                    break;

                case R.id.imageView8:
                    action(view, 8);
                    break;

                case R.id.imageView9:
                    action(view, 9);
                    break;

                case R.id.imageView10:
                    action(view, 10);
                    break;

                case R.id.imageView11:
                    action(view, 11);
                    break;

                case R.id.imageView12:
                    action(view, 12);
                    break;

                case R.id.imageView13:
                    action(view, 13);
                    break;

                case R.id.imageView14:
                    action(view, 14);
                    break;

                case R.id.imageView15:
                    action(view, 15);
                    break;

                case R.id.imageView16:
                    action(view, 16);
                    break;

                case R.id.imageView17:
                    action(view, 17);
                    break;

                case R.id.imageView18:
                    action(view, 18);
                    break;

                case R.id.imageView19:
                    action(view, 19);
                    break;

                case R.id.imageView20:
                    action(view, 20);
                    break;

                case R.id.imageView21:
                    action(view, 21);
                    break;

                case R.id.imageView22:
                    action(view, 22);
                    break;

                case R.id.imageView23:
                    action(view, 23);
                    break;

                case R.id.imageView24:
                    action(view, 24);
                    break;

                case R.id.imageView25:
                    action(view, 25);
                    break;

                case R.id.imageView26:
                    action(view, 26);
                    break;

                case R.id.imageView27:
                    action(view, 27);
                    break;

                case R.id.imageView28:
                    action(view, 28);
                    break;

                case R.id.imageView29:
                    action(view, 29);
                    break;

                case R.id.imageView30:
                    action(view, 30);
                    break;

                case R.id.imageView31:
                    action(view, 31);
                    break;

                case R.id.imageView32:
                    action(view, 32);
                    break;

                case R.id.imageView33:
                    action(view, 33);
                    break;

                case R.id.imageView34:
                    action(view, 34);
                    break;

                case R.id.imageView35:
                    action(view, 35);
                    break;

                case R.id.imageView36:
                    action(view, 36);
                    break;

                case R.id.imageView37:
                    action(view, 37);
                    break;

                case R.id.imageView38:
                    action(view, 38);
                    break;

                case R.id.imageView39:
                    action(view, 39);
                    break;

                case R.id.imageView40:
                    action(view, 40);
                    break;

                case R.id.imageView41:
                    action(view, 41);
                    break;

                case R.id.imageView42:
                    action(view, 42);
                    break;

                case R.id.imageView43:
                    action(view, 43);
                    break;

                case R.id.imageView44:
                    action(view, 44);
                    break;

                case R.id.imageView45:
                    action(view, 45);
                    break;

                case R.id.imageView46:
                    action(view, 46);
                    break;

                case R.id.imageView47:
                    action(view, 47);
                    break;

                case R.id.imageView48:
                    action(view, 48);
                    break;

                case R.id.imageView49:
                    action(view, 49);
                    break;

                case R.id.imageView50:
                    action(view, 50);
                    break;

                case R.id.imageView51:
                    action(view, 51);
                    break;

                case R.id.imageView52:
                    action(view, 52);
                    break;

                case R.id.imageView53:
                    action(view, 53);
                    break;

                case R.id.imageView54:
                    action(view, 54);
                    break;

                case R.id.imageView55:
                    action(view, 55);
                    break;

                case R.id.imageView56:
                    action(view, 56);
                    break;

                case R.id.imageView57:
                    action(view, 57);
                    break;

                case R.id.imageView58:
                    action(view, 58);
                    break;

                case R.id.imageView59:
                    action(view, 59);
                    break;

                case R.id.imageView60:
                    action(view, 60);
                    break;

                case R.id.imageView61:
                    action(view, 61);
                    break;

                case R.id.imageView62:
                    action(view, 62);
                    break;

                case R.id.imageView63:
                    action(view, 63);
                    break;
            }
        if (progressbar.getProgress() == progressbar.getMax()){
            looser();
        }
        }
    public void looser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory8.this);

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
        drawables[18] = R.drawable.tottenham;
        drawables[19] = R.drawable.realsociedad;
        drawables[20] = R.drawable.fiore;
        drawables[21] = R.drawable.atlethic;
        drawables[22] = R.drawable.betis;
        drawables[23] = R.drawable.inter;
        drawables[24] = R.drawable.milan;
        drawables[25] = R.drawable.manu;
        drawables[26] = R.drawable.mancity;
        drawables[27] = R.drawable.porto;
        drawables[28] = R.drawable.rbsalz;
        drawables[29] = R.drawable.roma;
        drawables[30] = R.drawable.westham;
        drawables[31] = R.drawable.zenit;
        drawables[32] = R.drawable.barca;
        drawables[33] = R.drawable.atmadrid;
        drawables[34] = R.drawable.madrid;
        drawables[35] = R.drawable.arsenal;
        drawables[36] = R.drawable.liverpool;
        drawables[37] = R.drawable.chelsea;
        drawables[38] = R.drawable.juve;
        drawables[39] = R.drawable.tits;
        drawables[40] = R.drawable.villareal;
        drawables[41] = R.drawable.santcu;
        drawables[42] = R.drawable.galatasaray;
        drawables[43] = R.drawable.celtic;
        drawables[44] = R.drawable.ajax;
        drawables[45] = R.drawable.psg;
        drawables[46] = R.drawable.monaco;
        drawables[47] = R.drawable.bayernmun;
        drawables[48] = R.drawable.borussia;
        drawables[49] = R.drawable.everton;
        drawables[50] = R.drawable.tottenham;
        drawables[51] = R.drawable.realsociedad;
        drawables[52] = R.drawable.fiore;
        drawables[53] = R.drawable.atlethic;
        drawables[54] = R.drawable.betis;
        drawables[55] = R.drawable.inter;
        drawables[56] = R.drawable.milan;
        drawables[57] = R.drawable.manu;
        drawables[58] = R.drawable.mancity;
        drawables[59] = R.drawable.porto;
        drawables[60] = R.drawable.rbsalz;
        drawables[61] = R.drawable.roma;
        drawables[62] = R.drawable.westham;
        drawables[63] = R.drawable.zenit;

    }


}
