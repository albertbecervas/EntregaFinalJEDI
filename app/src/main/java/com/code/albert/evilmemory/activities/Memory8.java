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

public class Memory8 extends AppCompatActivity {


    Integer[] drawables = new Integer[64];

    LoginHelper loginHelper;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean mustWait=false;

    int intents = 0,card1,card2,pairs=0;

    boolean isFirst = true;
    boolean[] isVisible = new boolean[64];

    CoolImageFlipper flipper;

    View view0, view1;

    ButterKnife butterKnife;

    @BindDrawable(R.drawable.ic_fast_forward_black_24dp)
    Drawable backside;
    @BindView(R.id.intents)
    TextView attempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory8);
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
        drawables[16] = R.drawable.ic_angel;
        drawables[17] = R.drawable.ic_evil;
        drawables[18] = R.drawable.ic_angel_and_demon_;
        drawables[19] = R.drawable.ic_call_black_24dp;
        drawables[20] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[21] = R.drawable.ic_dialpad_black_24dp;
        drawables[22] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[23] = R.drawable.ic_explore_black_24dp;
        drawables[24] = R.drawable.ic_angel;
        drawables[25] = R.drawable.ic_evil;
        drawables[26] = R.drawable.ic_angel_and_demon_;
        drawables[27] = R.drawable.ic_call_black_24dp;
        drawables[28] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[29] = R.drawable.ic_dialpad_black_24dp;
        drawables[30] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[31] = R.drawable.ic_explore_black_24dp;
        drawables[32] = R.drawable.ic_explore_black_24dp;
        drawables[33] = R.drawable.ic_explore_black_24dp;
        drawables[34] = R.drawable.ic_explore_black_24dp;
        drawables[35] = R.drawable.ic_explore_black_24dp;
        drawables[36] = R.drawable.ic_angel;
        drawables[37] = R.drawable.ic_evil;
        drawables[38] = R.drawable.ic_angel_and_demon_;
        drawables[39] = R.drawable.ic_call_black_24dp;
        drawables[40] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[41] = R.drawable.ic_dialpad_black_24dp;
        drawables[42] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[43] = R.drawable.ic_explore_black_24dp;
        drawables[44] = R.drawable.ic_angel;
        drawables[45] = R.drawable.ic_evil;
        drawables[46] = R.drawable.ic_angel_and_demon_;
        drawables[47] = R.drawable.ic_call_black_24dp;
        drawables[48] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[49] = R.drawable.ic_dialpad_black_24dp;
        drawables[50] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[51] = R.drawable.ic_explore_black_24dp;
        drawables[52] = R.drawable.ic_angel;
        drawables[53] = R.drawable.ic_evil;
        drawables[54] = R.drawable.ic_angel_and_demon_;
        drawables[55] = R.drawable.ic_call_black_24dp;
        drawables[56] = R.drawable.ic_camera_enhance_black_24dp;
        drawables[57] = R.drawable.ic_dialpad_black_24dp;
        drawables[58] = R.drawable.ic_exit_to_app_black_24dp;
        drawables[59] = R.drawable.ic_explore_black_24dp;
        drawables[60] = R.drawable.ic_angel;
        drawables[61] = R.drawable.ic_evil;
        drawables[62] = R.drawable.ic_angel_and_demon_;
        drawables[63] = R.drawable.ic_call_black_24dp;

        shuffle();;
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

    @OnClick({R.id.imageView0,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9
            ,R.id.imageView10,R.id.imageView11,R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19
            ,R.id.imageView20,R.id.imageView21,R.id.imageView22,R.id.imageView23,R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27,R.id.imageView28,R.id.imageView29,
            R.id.imageView32,R.id.imageView33,R.id.imageView34,R.id.imageView35,R.id.imageView36,R.id.imageView37,R.id.imageView38,R.id.imageView39,R.id.imageView40,R.id.imageView41,R.id.imageView42,
            R.id.imageView43,R.id.imageView44,R.id.imageView45,R.id.imageView46,R.id.imageView47,R.id.imageView48,R.id.imageView49,R.id.imageView50,R.id.imageView51,R.id.imageView52,R.id.imageView53,
            R.id.imageView54,R.id.imageView55,R.id.imageView56,R.id.imageView57,R.id.imageView58,R.id.imageView59,R.id.imageView60,R.id.imageView61,R.id.imageView62,R.id.imageView63})
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

            case R.id.imageView4:
                action(view,4);
                break;

            case R.id.imageView5:
                action(view,5);
                break;

            case R.id.imageView6:
                action(view,6);
                break;

            case R.id.imageView7:
                action(view,7);
                break;

            case R.id.imageView8:
                action(view,8);
                break;

            case R.id.imageView9:
                action(view,9);
                break;

            case R.id.imageView10:
                action(view,10);
                break;

            case R.id.imageView11:
                action(view,11);
                break;

            case R.id.imageView12:
                action(view,12);
                break;

            case R.id.imageView13:
                action(view,13);
                break;

            case R.id.imageView14:
                action(view,14);
                break;

            case R.id.imageView15:
                action(view,15);
                break;

            case R.id.imageView16:
                action(view,16);
                break;

            case R.id.imageView17:
                action(view,17);
                break;

            case R.id.imageView18:
                action(view,18);
                break;

            case R.id.imageView19:
                action(view,19);
                break;

            case R.id.imageView20:
                action(view,20);
                break;

            case R.id.imageView21:
                action(view,21);
                break;

            case R.id.imageView22:
                action(view,22);
                break;

            case R.id.imageView23:
                action(view,23);
                break;

            case R.id.imageView24:
                action(view,24);
                break;

            case R.id.imageView25:
                action(view,25);
                break;

            case R.id.imageView26:
                action(view,26);
                break;

            case R.id.imageView27:
                action(view,27);
                break;

            case R.id.imageView28:
                action(view,28);
                break;

            case R.id.imageView29:
                action(view,29);
                break;

            case R.id.imageView30:
                action(view,30);
                break;

            case R.id.imageView31:
                action(view,31);
                break;

            case R.id.imageView32:
                action(view,32);
                break;

            case R.id.imageView33:
                action(view,33);
                break;

            case R.id.imageView34:
                action(view,34);
                break;

            case R.id.imageView35:
                action(view,35);
                break;

            case R.id.imageView36:
                action(view,36);
                break;

            case R.id.imageView37:
                action(view,37);
                break;

            case R.id.imageView38:
                action(view,38);
                break;

            case R.id.imageView39:
                action(view,39);
                break;

            case R.id.imageView40:
                action(view,40);
                break;

            case R.id.imageView41:
                action(view,41);
                break;

            case R.id.imageView42:
                action(view,42);
                break;

            case R.id.imageView43:
                action(view,43);
                break;

            case R.id.imageView44:
                action(view,44);
                break;

            case R.id.imageView45:
                action(view,45);
                break;

            case R.id.imageView46:
                action(view,46);
                break;

            case R.id.imageView47:
                action(view,47);
                break;

            case R.id.imageView48:
                action(view,48);
                break;

            case R.id.imageView49:
                action(view,49);
                break;

            case R.id.imageView50:
                action(view,50);
                break;

            case R.id.imageView51:
                action(view,51);
                break;

            case R.id.imageView52:
                action(view,52);
                break;

            case R.id.imageView53:
                action(view,53);
                break;

            case R.id.imageView54:
                action(view,54);
                break;

            case R.id.imageView55:
                action(view,55);
                break;

            case R.id.imageView56:
                action(view,56);
                break;

            case R.id.imageView57:
                action(view,57);
                break;

            case R.id.imageView58:
                action(view,58);
                break;

            case R.id.imageView59:
                action(view,59);
                break;

            case R.id.imageView60:
                action(view,60);
                break;

            case R.id.imageView61:
                action(view,61);
                break;

            case R.id.imageView62:
                action(view,62);
                break;

            case R.id.imageView63:
                action(view,63);
                break;
        }
    }

}
