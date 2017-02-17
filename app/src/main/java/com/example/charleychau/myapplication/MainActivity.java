package com.example.charleychau.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button tutorialButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.playButton);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent playGame = new Intent(MainActivity.this, Game.class);
                startActivity(playGame);
            }
        });
        tutorialButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent tutorial = new Intent(MainActivity.this, Tutorial.class);
                startActivity(tutorial);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);
            }
        });
    }
}
