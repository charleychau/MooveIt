package com.example.charleychau.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button tutorialButton;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(MainActivity.this, R.raw.oldmacdonald);
        player.setLooping(true);
        player.start();

        playButton = (Button) findViewById(R.id.playButton);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent playGame = new Intent(MainActivity.this, Game.class);
                player.pause();
                startActivity(playGame);
            }
        });
        tutorialButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent tutorial = new Intent(MainActivity.this, Tutorial.class);
                startActivity(tutorial);
            }
        });
    }
}
