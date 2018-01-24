package com.janejsmund.odtwarzacz;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    Button btnCheckWeather, btnStart, btnPause, btnStop, btnNext;
    ImageView imageView;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckWeather = findViewById(R.id.btnCheckWeather);

        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnNext = findViewById(R.id.btnNext);

        imageView = findViewById(R.id.imageView);

        mediaPlayer = MediaPlayer.create(this, )
    }

    public void checkWeather(View view) {

    }

    public void start(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void stop(View view) {
        mediaPlayer.stop();
    }

    public void next(View view) {
        nextTrack();
    }

    private void nextTrack() {}
}
