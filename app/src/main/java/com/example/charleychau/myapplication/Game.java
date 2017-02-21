package com.example.charleychau.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class Game extends AppCompatActivity {
    private TextView mTextField;
    private TextView score;
    private TextView gestureInstruction;
    private TextView testGestureText;
    public static int currentScore;
    private ImageView imageview;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mTextField = (TextView) findViewById(R.id.playTimer);
        score = (TextView) findViewById(R.id.scoreView);
        testGestureText = (TextView) findViewById(R.id.textView2);
        testGestureText.setText("");
        imageview = (ImageView) findViewById(R.id.imageView);
        imageview.setVisibility(View.INVISIBLE);
        gestureInstruction = (TextView) findViewById(R.id.gestureInstruction);
        gestureInstruction.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        currentScore = 0;
        score.setText(String.valueOf(currentScore));
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText(millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                mTextField.setText("Mooove IT!");
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_game);
                layout.setBackgroundResource(R.drawable.game_background);
                mTextField.postDelayed(new Runnable() {
                    public void run() {
                        mTextField.setVisibility(View.INVISIBLE);
                        gestureInstruction.setVisibility(View.VISIBLE);
                        score.setText("0");
                        score.setVisibility(View.VISIBLE);

                    }
                }, 1000);

            }
        }.start();
        imageview.setVisibility(View.VISIBLE);
        initializeGesture(randomGenerator(0));



    }
    private int randomGenerator(int avoidGestureNumber) {
        int randomGesture = (1 + (int) (Math.random() * ((3 - 1) + 1)));
        while(randomGesture ==avoidGestureNumber){
            randomGesture = (1 + (int) (Math.random() * ((3 - 1) + 1)));
        }
        return randomGesture;
    }
    private void initializeGesture(int gestureNumber) {
        switch (gestureNumber) {
            case 1:
                gestureInstruction.setText("Swipe left on the sheep!");
                imageview.setImageResource(R.drawable.sheep_unherded);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 1));
                break;
            case 2:
                gestureInstruction.setText("Swipe right on the chicken!");
                imageview.setImageResource(R.drawable.chicken);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 2));
                break;
            case 3:
                gestureInstruction.setText("Swipe down on the egg!");
                imageview.setImageResource(R.drawable.egg);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 3));
                break;
        }
}
    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;
        private int gestureNumber;

        public OnSwipeTouchListener(Context context, int number) {
            gestureDetector = new GestureDetector(context, new GestureListener());
            gestureNumber = number;
        }

        public void onSwipeLeft() {
            if(gestureNumber ==1){
                imageview.setImageResource(R.drawable.sheep_herded);
                new CountDownTimer(500, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        initializeGesture(randomGenerator(1));
                    }
                }.start();
            }
            else {
                Toast.makeText(Game.this, "You failed!", Toast.LENGTH_SHORT).show();
                Intent exitGame = new Intent (Game.this, MainActivity.class);
                startActivity(exitGame);
            }
        }

        public void onSwipeRight() {
            if(gestureNumber ==2){
                imageview.setImageResource(R.drawable.fish);
                new CountDownTimer(500, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        initializeGesture(randomGenerator(2));
                    }
                }.start();
            }
            else {
                Toast.makeText(Game.this, "You failed!", Toast.LENGTH_SHORT).show();
                Intent exitGame = new Intent (Game.this, MainActivity.class);
                startActivity(exitGame);
            }
        }

        public void onSwipeDown() {
            if(gestureNumber ==3){
                imageview.setImageResource(R.drawable.golden_egg);
                new CountDownTimer(500, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        initializeGesture(randomGenerator(3));
                    }
                }.start();
            }
            else {
                Toast.makeText(Game.this, "You failed!", Toast.LENGTH_SHORT).show();
                Intent exitGame = new Intent (Game.this, MainActivity.class);
                startActivity(exitGame);
            }
        }

        public void onSwipeUp() {
            if(gestureNumber ==4){
                Toast.makeText(Game.this, "You swiped up!", Toast.LENGTH_SHORT).show();
                initializeGesture(randomGenerator(4));
            }
            else {
                Toast.makeText(Game.this, "You failed!", Toast.LENGTH_SHORT).show();
                Intent exitGame = new Intent (Game.this, MainActivity.class);
                startActivity(exitGame);
            }
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                //onSwipeDown();
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                float absoluteX = Math.abs(distanceX);
                float absoluteY = Math.abs(distanceY);
                if(absoluteY > SWIPE_DISTANCE_THRESHOLD  && absoluteX <absoluteY && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD ){
                    if(distanceY >0){
                        onSwipeDown();
                    }
                    else {
                        onSwipeUp();
                    }
                    return true;
                }
                if (absoluteX > absoluteY && absoluteX > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }


}


