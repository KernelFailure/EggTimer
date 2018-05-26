package com.example.leonp.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int seconds;
    int minutes;
    boolean timerIsRunning = false;
    boolean timerStopped;
    SeekBar timeSlider;
    CountDownTimer myCountDownTimer;



    TextView timeDisplay;

    public void displayTime (int secondsLeft) {


        seconds = secondsLeft % 60;
        minutes = secondsLeft / 60;

        if (seconds < 10) {
            timeDisplay.setText(minutes + ":0" + seconds);
        } else {
            timeDisplay.setText(minutes + ":" + seconds);
        }
    }

    public void runTimer() {
        myCountDownTimer = new CountDownTimer(timeSlider.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                displayTime((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.i("Timer is: ", "Done");
                MediaPlayer mplayer = (MediaPlayer.create(getApplicationContext(),R.raw.airhorn));
                mplayer.start();
            }
        }.start();
    }

    public void setTimer(View view) {

        Button myButton = (Button) findViewById(R.id.setTimeButton);

        if (!timerIsRunning) {
            timerIsRunning = true;
            myButton.setText("Stop");
            timeSlider.setEnabled(false);
            runTimer();

        } else {
            timerIsRunning = false;
            myButton.setText(("Start"));
            timeSlider.setEnabled(true);
            myCountDownTimer.cancel();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSlider = (SeekBar) findViewById(R.id.timeSetterSeekBar);
        timeDisplay = (TextView) findViewById(R.id.timeDisplay);

        timeSlider.setMax(1800);
        timeSlider.setProgress(0);

        timeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                displayTime(progress);

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
