package com.example.charleychau.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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

/*
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
*/

public class Game extends AppCompatActivity {
    private TextView mTextField;
    private TextView score;
    private TextView gestureInstruction;
    private TextView testGestureText;
    private ImageView imageview;
    private ImageView checkmark;
    public static int currentScore;
    public int beginConstraint = 5250;
    public static int ENDCONSTRAINT = 2000;
    public boolean responded = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mTextField = (TextView) findViewById(R.id.playTimer);
        score = (TextView) findViewById(R.id.scoreView);
        testGestureText = (TextView) findViewById(R.id.textView2);
        testGestureText.setText("");
        imageview = (ImageView) findViewById(R.id.imageView);
        checkmark = (ImageView) findViewById(R.id.checkmark);
        imageview.setVisibility(View.INVISIBLE);
        checkmark.setVisibility(View.INVISIBLE);
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
                mTextField.setText("Moove It!");
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_game);
                layout.setBackgroundResource(R.drawable.game_background);
                mTextField.postDelayed(new Runnable() {
                    public void run() {
                        mTextField.setVisibility(View.INVISIBLE);
                        gestureInstruction.setVisibility(View.VISIBLE);
                        score.setText("0");
                        score.setVisibility(View.VISIBLE);
                        imageview.setVisibility(View.VISIBLE);
                        responded = false;
                        initializeGesture(randomGenerator(0));
                    }
                }, 1000);
            }
        }.start();
    }

    private int randomGenerator(int avoidGestureNumber) {
        int randomGesture = (1 + (int) (Math.random() * ((10 - 1) + 1)));
        while(randomGesture == avoidGestureNumber){
            randomGesture = (1 + (int) (Math.random() * ((10 - 1) + 1)));
        }
        return randomGesture;
    }

    private void initializeGesture(int gestureNumber) {

        /*if (beginConstraint > 2000){
            beginConstraint = beginConstraint - 250;
        }*/

        switch (gestureNumber) {
            /*case 0: // Touch
                gestureInstruction.setText("Hatch the egg!");
                imageview.setImageResource(R.drawable.egg);
                //TODO: Listen for touch

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();

                break;*/
            case 1: // Swipe Left
                gestureInstruction.setText("Herd the sheep!");
                imageview.setImageResource(R.drawable.sheep_unherded);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 1));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.sheep_herded);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(1));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 2: // Swipe Right
                gestureInstruction.setText("Move the horse!");
                imageview.setImageResource(R.drawable.horseleft);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 2));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.horseright);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(2));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 3: // Swipe Down
                gestureInstruction.setText("Plow the fields!");
                imageview.setImageResource(R.drawable.empty_field);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 3));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.plowed_field);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(3));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 4: // Swipe Up
                gestureInstruction.setText("Feed the chicken!");
                imageview.setImageResource(R.drawable.chicken_hungry);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 4));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.chicken_eating);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(4));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 5: // Gyroscope horizontal chop
                gestureInstruction.setText("Chop the tree!");
                imageview.setImageResource(R.drawable.tree);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 5));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.tree_chopped);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(5));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 6: //
                gestureInstruction.setText("Feed the pig!");
                imageview.setImageResource(R.drawable.pig_hungry);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 6));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.pig_eating);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(6));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 7: //
                gestureInstruction.setText("Pour the milk!");
                imageview.setImageResource(R.drawable.milk_empty_glass);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 7));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.milk_pouring);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(7));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 8: // Gyroscope flick
                gestureInstruction.setText("Go fish!");
                imageview.setImageResource(R.drawable.fishing_rod);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 8));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.fish_caught);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(8));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 9: // Gyroscope shake
                gestureInstruction.setText("Ring the bell!");
                imageview.setImageResource(R.drawable.bell);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 9));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.bell_ringing);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(9));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

                break;
            case 10: // Gyroscope up and down
                gestureInstruction.setText("Churn the butter!");
                imageview.setImageResource(R.drawable.butter_churn);
                //testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 10));

                new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        imageview.setImageResource(R.drawable.butter);
                        checkmark.setVisibility(View.VISIBLE);

                        new CountDownTimer(750, 250) { // 5000 = 5 sec
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                currentScore += 100;
                                score.setText(String.valueOf(currentScore));
                                checkmark.setVisibility(View.INVISIBLE);
                                responded = false;
                                initializeGesture(randomGenerator(10));
                            }
                        }.start();
                    }
                }.start();

                /*new CountDownTimer(beginConstraint, 1) {
                    public void onTick(long millisUntilFinished) {
                        if (responded == true) {
                            cancel();
                        }
                    }
                    public void onFinish() {
                        if (responded == false) {
                            Intent exitGame = new Intent (Game.this, EndGame.class);
                            exitGame.putExtra("finalScore", currentScore);
                            startActivity(exitGame);
                        }
                    }
                }.start();*/

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
            if(gestureNumber > 0){
                imageview.setImageResource(R.drawable.sheep_herded);
                checkmark.setVisibility(View.VISIBLE);

                new CountDownTimer(750, 250) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
                        responded = false;
                        initializeGesture(randomGenerator(1));
                    }
                }.start();

            }
            else {
                Intent exitGame = new Intent (Game.this, EndGame.class);
                exitGame.putExtra("finalScore", currentScore);
                startActivity(exitGame);
            }
        }

        public void onSwipeRight() {
            if(gestureNumber > 0){
                imageview.setImageResource(R.drawable.horseright);
                checkmark.setVisibility(View.VISIBLE);

                new CountDownTimer(750, 250) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
                        responded = false;
                        initializeGesture(randomGenerator(2));
                    }
                }.start();

            }
            else {
                Intent exitGame = new Intent (Game.this, EndGame.class);
                exitGame.putExtra("finalScore", currentScore);
                startActivity(exitGame);
            }
        }

        public void onSwipeDown() {
            if(gestureNumber > 0) {
                imageview.setImageResource(R.drawable.plowed_field);
                checkmark.setVisibility(View.VISIBLE);

                new CountDownTimer(750, 250) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
                        responded = false;
                        initializeGesture(randomGenerator(3));
                    }
                }.start();

            }
            else {
                Intent exitGame = new Intent (Game.this, EndGame.class);
                exitGame.putExtra("finalScore", currentScore);
                startActivity(exitGame);
            }
        }

        public void onSwipeUp() {
            if(gestureNumber > 0){
                imageview.setImageResource(R.drawable.chicken_eating);
                checkmark.setVisibility(View.VISIBLE);

                new CountDownTimer(750, 250) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
                        responded = false;
                        initializeGesture(randomGenerator(4));
                    }
                }.start();

            }
            else {
                Intent exitGame = new Intent (Game.this, EndGame.class);
                exitGame.putExtra("finalScore", currentScore);
                startActivity(exitGame);
            }
        }

        public boolean onTouch(View v, MotionEvent event) {
            responded = true;
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                //onSwipeDown();
                responded = true;
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
                        responded = true;
                        onSwipeDown();
                    }
                    else {
                        responded = true;
                        onSwipeUp();
                    }
                    return true;
                }
                if (absoluteX > absoluteY && absoluteX > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if(distanceX > 0) {
                        responded = true;
                        onSwipeRight();
                    }
                    else {
                        responded = true;
                        onSwipeLeft();
                    }
                    return true;
                }
                return false;
            }
        }
    }
}