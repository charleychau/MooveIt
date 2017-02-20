package com.example.charleychau.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Random;

public class Game extends AppCompatActivity {

    private TextView mTextField;
    private TextView score;
    private TextView gestureInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextField = (TextView) findViewById(R.id.playTimer);
        score = (TextView) findViewById(R.id.scoreView);
        gestureInstruction = (TextView) findViewById(R.id.gestureInstruction);
        gestureInstruction.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText(millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                mTextField.setText("Mooove IT!");
                RelativeLayout layout =(RelativeLayout)findViewById(R.id.activity_game);
                layout.setBackgroundResource(R.drawable.game_background);
                mTextField.postDelayed(new Runnable(){
                    public void run(){
                        mTextField.setVisibility(View.INVISIBLE);
                        gestureInstruction.setVisibility(View.VISIBLE);
                        score.setText("0");
                        score.setVisibility(View.VISIBLE);

                    }
                }, 1000);

            }
        }.start();

        initializeGesture(randomGenerator());


    }

    private int randomGenerator(){
        return (1 + (int)(Math.random() * ((3 - 1) + 1)));
    }

    private void initializeGesture(int gestureNumber){
        switch(gestureNumber){
            case 1:
                gestureInstruction.setText("Draw a circle around the sheep!");
                break;
            case 2:
                gestureInstruction.setText("Swipe the chicken");
                break;
            case 3:
                gestureInstruction.setText("do anything!");
                break;

        }
    }
}
