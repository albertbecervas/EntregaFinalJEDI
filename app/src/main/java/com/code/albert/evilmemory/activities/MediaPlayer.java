package com.code.albert.evilmemory.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.service.BoundService;

public class MediaPlayer extends AppCompatActivity {

    ImageView play, stop, next, previous;
    Button selectSong;
    SeekBar volume;
    AudioManager audioManager;
    Boolean set;

    TextView song;

    android.media.MediaPlayer mediaPlayer;

    BoundService bService;
    boolean bound = false;
    Intent intent;

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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        intent = new Intent(MediaPlayer.this, BoundService.class);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        play = (ImageView) findViewById(R.id.imageButton);
        play.setOnClickListener(listener);

        stop = (ImageView) findViewById(R.id.imageButtonStop);
        stop.setOnClickListener(listener);

        next = (ImageView) findViewById(R.id.next);
        next.setOnClickListener(listener);

        selectSong = (Button) findViewById(R.id.selectSong);
        selectSong.setOnClickListener(listener);

        song = (TextView) findViewById(R.id.song);

        mediaPlayer = new android.media.MediaPlayer();
        mediaPlayer.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(android.media.MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        set=true;


        play.setImageResource(R.drawable.ic_playstation_logo);

        Volume();


    }


    private void Volume() {

        try {
            volume = (SeekBar)findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override            public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageButton:
                    if(!set){
                        bService.pauseSong();
                        play.setImageResource(R.drawable.ic_playstation_logo);
                        play.invalidate();
                        set=true;

                    }else{
                        bService.playSong();
                        song.setText(bService.s);
                        play.setImageResource(R.drawable.ic_stop);
                        set=false;

                    }
                    break;
                case R.id.selectSong:

                    if (ContextCompat.checkSelfPermission(MediaPlayer.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MediaPlayer.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            // Show an expanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.
                        } else {
                            // No explanation needed, we can request the permission.
                            ActivityCompat.requestPermissions(MediaPlayer.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    0);
                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }



                    if(ContextCompat.checkSelfPermission(MediaPlayer.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {

                        Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent1, 1);}
                    break;

                case R.id.imageButtonStop:
                    bService.stopSong();
                    play.setImageResource(R.drawable.ic_playstation_logo);
                    set=true;
                    break;
                case R.id.next:
                    bService.playNext();
                    song.setText(bService.s);
                    break;


            }

        }

    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bService.on_destroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                bService.playSongFromMemory(data);
                play.setImageResource(R.drawable.ic_playstation_logo);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
