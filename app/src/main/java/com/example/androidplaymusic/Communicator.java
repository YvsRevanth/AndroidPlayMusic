package com.example.androidplaymusic;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;

public class Communicator extends IntentService {
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String someValue = intent.getStringExtra("STOP");
            if (someValue == "STOP") {
                myMusic.stop();
            }
        }
    };
}
    MediaPlayer myMusic;

    public Communicator() {
        super(Communicator.class.getSimpleName());
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.communicate.service");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        myMusic = MediaPlayer.create(this, R.raw.csk);
        myMusic.start();
        Intent i = new Intent("com.communicate.main");
        i.putExtra("music_name", "csk whistlepodu");
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);

    }
}
