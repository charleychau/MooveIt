package com.example.charleychau.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndGame extends AppCompatActivity {

    private TextView finalScore;
    private Button playAgainButton;
    private Button mainmenuButton;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        player = MediaPlayer.create(EndGame.this, R.raw.moo);
        player.setLooping(false);
        player.start();


        finalScore = (TextView) findViewById(R.id.score);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        mainmenuButton = (Button) findViewById(R.id.mainmenuButton);

        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("finalScore");
        finalScore.setText("Your Score: " + "\n" + score);

        playAgainButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent playGame = new Intent(EndGame.this, Game.class);
                startActivity(playGame);
            }
        });
        mainmenuButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent mainmenu = new Intent(EndGame.this, MainActivity.class);
                startActivity(mainmenu);
            }
        });
    }
}
