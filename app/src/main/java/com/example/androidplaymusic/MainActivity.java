package com.example.androidplaymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    ImageButton one;
    ImageButton two;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String someValue = intent.getStringExtra("music_name");
            TextView textView = findViewById(R.id.text);
            textView.setText(someValue);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        one = (ImageButton) findViewById(R.id.one);
        img = (ImageView) findViewById(R.id.imageButton);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                startService(new Intent(getApplicationContext(), Communicator.class));
                img.setImageResource(R.mipmap.csk);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setImageResource(R.drawable.ic_baseline_pause_presentation_24);
                Intent i = new Intent(("com.communicate.service"));
                i.putExtra("two", "two");
                LocalBroadcastManager.getInstance(MainActivity.this, sendBroadcast(i);
            }
        });
        }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.communicate.main");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
}