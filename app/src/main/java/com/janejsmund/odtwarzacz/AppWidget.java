package com.janejsmund.odtwarzacz;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AppWidget extends AppWidgetProvider {

    static ImageView imageView;
    static RemoteViews views;

    static MediaPlayer mediaPlayer;
    static AudioManager audioManager;

    static final int[] playlist = {R.raw.anger, R.raw.frogs, R.raw.gong, R.raw.movie_projector, R.raw.radio_tuning};
    static int currentTrack;

    static final int[] slides = {R.raw.lake, R.raw.mountains, R.raw.peacock};
    static int currentSlide;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

        currentSlide = 0;
        views.setImageViewResource(R.id.imageView, slides[currentSlide]);

        currentTrack = 0;
        mediaPlayer = MediaPlayer.create(context, playlist[currentTrack]);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        checkWeather(context);


        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void checkWeather(Context context) {
        try {
            final String siteUrl = "http://if.pw.edu.pl/~meteo/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(siteUrl));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.btnCheckWeather, pendingIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Brak zainstalowanej przeglądarki.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void nextImage(View view) {
        if (currentSlide < slides.length-1) {
            imageView.setImageResource(slides[++currentSlide]);
        }
        else {
            currentSlide = 0;
            imageView.setImageResource(slides[currentSlide]);
        }
    }

    public void start(Context context) {    mediaPlayer.start();    }

    public void pause(Context context) {
        mediaPlayer.pause();
    }

    public void stop(Context context) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(context, playlist[currentTrack]);
    }

    public void next(Context context) { nextTrack(context);    }

    private void nextTrack(Context context) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        if (currentTrack < playlist.length-1) {
            mediaPlayer = MediaPlayer.create(context, playlist[++currentTrack]);
        }
        else {
            currentTrack = 0;
            mediaPlayer = MediaPlayer.create(context, playlist[0]);
        }
        mediaPlayer.start();
    }
}

