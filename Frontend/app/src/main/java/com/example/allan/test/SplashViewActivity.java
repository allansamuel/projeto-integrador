package com.example.allan.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.VideoView;

public class SplashViewActivity extends AppCompatActivity {

    private static int TIME_OUT = 7000;
    private VideoView videoView;
    private ImageView imageView;
    private Animation fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);

        inicializaComponentes();
        String path = ("android.resource://" + getPackageName()+ "/" + R.raw.barack_video);
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(500);
        imageView.setAnimation(fadeOut);
        videoView.start();


        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) { videoView.bringToFront(); }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashViewActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

    }

    private void inicializaComponentes(){
        this.videoView = findViewById(R.id.videoView);
        this.imageView = findViewById(R.id.imageView);
        this.fadeOut = new AlphaAnimation(1, 0);
    }
}
