package com.example.charleychau.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.util.ArrayList;

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
    public static int currentScore;
    private ImageView imageview;
    private ImageView checkmark;
    private SensorManager sensorManager;

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
                        initializeGesture(randomGenerator(0));
                    }
                }, 1000);

            }
        }.start();

    }
    private int randomGenerator(int avoidGestureNumber) {
        int randomGesture = (1 + (int) (Math.random() * ((9 - 1) + 1)));
        while(randomGesture ==avoidGestureNumber){
            randomGesture = (1 + (int) (Math.random() * ((9 - 1) + 1)));
        }
        return randomGesture;
    }
    private void initializeGesture(int gestureNumber) {
        switch (gestureNumber) {
            case 1:
                gestureInstruction.setText("Herd the sheep"); //left but is a "circle" -> need to fix, p dollar
                imageview.setImageResource(R.drawable.sheep_unherded);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 1));
                break;
            case 2:
                gestureInstruction.setText("Move the horse"); //right
                imageview.setImageResource(R.drawable.horseleft);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 2));
                break;
            case 3:
                gestureInstruction.setText("Plow the fields"); //down
                imageview.setImageResource(R.drawable.empty_field);
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 3));
                break;
            case 4:
                gestureInstruction.setText("Feed the chicken!"); //up
                imageview.setImageResource(R.drawable.chicken_hungry); //replace this with chicken w/grain
                testGestureText.setOnTouchListener(new OnSwipeTouchListener(Game.this, 4));
                break;
            case 5:
                gestureInstruction.setText("Go fish!"); //gyroscope that accepts any movement 4 now
                imageview.setImageResource(R.drawable.fishing_rod);
                useGyroscope(5);
                break;
            case 6:
                gestureInstruction.setText("Pour the milk"); //counterclockwise turn
                imageview.setImageResource(R.drawable.milk_empty_glass);
                useGyroscope(6);
                break;
            case 7:
                gestureInstruction.setText("Feed the pig"); //clockwise turn
                imageview.setImageResource(R.drawable.pig_hungry);
                useGyroscope(7);
                break;
            case 8:
                gestureInstruction.setText("Cut the tree!");
                imageview.setImageResource(R.drawable.tree); //right or left slashing movement via gyroscope
                useGyroscope(8);
                break;
            case 9:
                gestureInstruction.setText("Shake the bell!");
                imageview.setImageResource(R.drawable.bell); //shake ur phone
                useGyroscope(9);
                break;
            case 10:
                gestureInstruction.setText("Churn the butter!"); //p dollar
                imageview.setImageResource(R.drawable.butter_churn);
                useGyroscope(10);
                break;

        }
    }

    private PDollarRecognizer recognizer = new PDollarRecognizer();
    private ArrayList<Point> points = new ArrayList<Point>();
    private boolean isPdollarOn = false;

    private void usePdollar(int gestureNumber)
    {
        final int pdollarGesture = gestureNumber;
        isPdollarOn = true;
        if(pdollarGesture == 1)
        {
            //herd the sheep - detect circle

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = MotionEventCompat.getActionMasked(event);
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        RecognizerResults results;

        if(isPdollarOn) {
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.i("TAG", "DOWN: (" + x + ", " + y + ")");
                    Point p1 = new Point(x, y, 1);
                    points.add(p1);
                    break;

                case (MotionEvent.ACTION_UP):
                    Log.i("TAG", "UP: (" + x + ", " + y + ")");
                    Point p2 = new Point(x, y, 1);
                    points.add(p2);
                    if (points.size() > 10) {
                        results = recognizer.Recognize(points);
                        Log.i("TAG", results.mName);
                    }
                    points.clear();
                    isPdollarOn = false;
                    break;

                case (MotionEvent.ACTION_MOVE):
                    Log.i("TAG", "MOVING: (" + x + ", " + y + ")");
                    Point p3 = new Point(x, y, 1);
                    points.add(p3);
                    break;
            }
        }

        return true;
    }

        private void useGyroscope(int gestureNumber) {
            final int gyroscopeNumber = gestureNumber;
            if (gestureNumber == 5) {
                //Gesture currently not implemented, passing without any detection
                //Replace this code below with implemented gesture when ready
                new CountDownTimer(5000, 1) {
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
                                initializeGesture(randomGenerator(5));
                            }
                        }.start();
                    }
                }.start();
            }
            else if(gestureNumber ==6 || gestureNumber == 7){
                //Working implementation of clockwise and counterclockwise gesture recognition
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if (sensorEvent.values[2] > 3.0f ) { // anticlockwise
                            sensorManager.unregisterListener(this);
                            if(gyroscopeNumber ==6) {
                                imageview.setImageResource(R.drawable.milk_pouring);
                                checkmark.setVisibility(View.VISIBLE);
                                new CountDownTimer(750, 250) { // 5000 = 5 sec

                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        currentScore += 100;
                                        score.setText(String.valueOf(currentScore));
                                        checkmark.setVisibility(View.INVISIBLE);
                                        initializeGesture(randomGenerator(6));
                                    }
                                }.start();
                            }
                            else {
                                sensorManager.unregisterListener(this);
                                Intent exitGame = new Intent(Game.this, EndGame.class);
                                exitGame.putExtra("finalScore", currentScore);
                                startActivity(exitGame);
                            }
                        } else if (sensorEvent.values[2] < -3.0f) {  // clockwise
                            sensorManager.unregisterListener(this);
                            if (gyroscopeNumber == 7) {
                                imageview.setImageResource(R.drawable.pig_eating);
                                checkmark.setVisibility(View.VISIBLE);
                                new CountDownTimer(750, 250) { // 5000 = 5 sec

                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        currentScore += 100;
                                        score.setText(String.valueOf(currentScore));
                                        checkmark.setVisibility(View.INVISIBLE);
                                        initializeGesture(randomGenerator(7));
                                    }
                                }.start();


                            } else {
                                sensorManager.unregisterListener(this);
                                Intent exitGame = new Intent(Game.this, EndGame.class);
                                exitGame.putExtra("finalScore", currentScore);
                                startActivity(exitGame);

                            }
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {
                    }
                };

                // Register the listener
                sensorManager.registerListener(gyroscopeSensorListener,
                        gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            else if(gestureNumber ==8){
                //Gesture currently not implemented, passing without any detection
                //Replace this code below with implemented gesture when ready
                new CountDownTimer(5000, 1) {
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
                                initializeGesture(randomGenerator(5));
                            }
                        }.start();
                    }
                }.start();
            }
            else if(gestureNumber ==9){
                //Gesture currently not implemented, passing without any detection
                //Replace this code below with implemented gesture when ready
                new CountDownTimer(5000, 1) {
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
                                initializeGesture(randomGenerator(5));
                            }
                        }.start();
                    }
                }.start();
            }
            else if(gestureNumber ==10){
                //Gesture currently not implemented, passing without any detection
                //Replace this code below with implemented gesture when ready
                new CountDownTimer(5000, 1) {
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
                                initializeGesture(randomGenerator(5));
                            }
                        }.start();
                    }
                }.start();
            }
            else {
                //Extra gesture cases may be defined in the future.
            }

        }

    public class OnSwipeTouchListener implements OnTouchListener {
    //Touch listener for left, right, up & down gesture detection
        private final GestureDetector gestureDetector;
        private int gestureNumber;

        public OnSwipeTouchListener(Context context, int number) {
            gestureDetector = new GestureDetector(context, new GestureListener());
            gestureNumber = number;
        }

        public void onSwipeLeft() {
            if(gestureNumber ==1){
                imageview.setImageResource(R.drawable.sheep_herded);
                checkmark.setVisibility(View.VISIBLE);
                new CountDownTimer(750, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
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
            if(gestureNumber ==2){
                imageview.setImageResource(R.drawable.horseright);
                checkmark.setVisibility(View.VISIBLE);
                new CountDownTimer(750, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
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
            if(gestureNumber ==3){
                imageview.setImageResource(R.drawable.plowed_field);
                checkmark.setVisibility(View.VISIBLE);
                new CountDownTimer(750, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
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
            if(gestureNumber ==4){
                imageview.setImageResource(R.drawable.chicken_eating);
                checkmark.setVisibility(View.VISIBLE);
                new CountDownTimer(750, 250) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentScore += 100;
                        score.setText(String.valueOf(currentScore));
                        checkmark.setVisibility(View.INVISIBLE);
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
                System.out.println("Absolute X is: " + absoluteX);
                System.out.println("absoluteY is: " + absoluteY);
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


