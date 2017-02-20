package com.example.charleychau.myapplication;

import android.content.Context;
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
    private ImageView imageView;
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
        gestureInstruction = (TextView) findViewById(R.id.gestureInstruction);
        gestureInstruction.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
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
        initializeGesture(randomGenerator());
        /**boolean continuePlaying = false;
        while(continuePlaying) {
            initializeGesture(randomGenerator());
        } **/


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private int randomGenerator() {
        return (1 + (int) (Math.random() * ((3 - 1) + 1)));
    }
    private void initializeGesture(int gestureNumber) {
        switch (gestureNumber) {
            case 1:
                gestureInstruction.setText("Swipe left on horse!");
                gestureInstruction.setOnTouchListener(new OnSwipeTouchListener(Game.this) {
                    @Override
                    public void onSwipeLeft() {
                        Toast.makeText(Game.this, "Fatastic!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                gestureInstruction.setText("Swipe right on the chicken!");
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this) {
                    @Override
                    public void onSwipeRight() {
                        Toast.makeText(Game.this, "Great job!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                gestureInstruction.setText("Swipe down on the egg!");
                //imageView.setVisibility(View.VISIBLE);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this) {
                    @Override
                    public void onSwipeDown() {
                        Toast.makeText(Game.this, "You did good!", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
}
    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
            Toast.makeText(Game.this, "You swiped left!", Toast.LENGTH_SHORT).show();
        }

        public void onSwipeRight() {
            Toast.makeText(Game.this, "You swiped right!", Toast.LENGTH_SHORT).show();
        }

        public void onSwipeDown() {
            Toast.makeText(Game.this, "You swiped down!", Toast.LENGTH_SHORT).show();
        }

        public void onSwipeUp() {
            Toast.makeText(Game.this, "You swiped up!", Toast.LENGTH_SHORT).show();
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


