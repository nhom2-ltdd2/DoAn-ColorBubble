package com.example.admin.animationfootball;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Animation animBall, animGoalKeeper, animHIHI, animAngry, animFallDown, animBallL, animBallR, animGoalKeeper2;
    Button btnKick, btnKickL, btnKickR;
    ImageView imgBall, imgGoalKeeper, imgHIHI, imgAngry;
    long pos;
    int btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animBall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ball_animation3);
        animBallR = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ball_animation);
        animBallL = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ball_animation2);
        animGoalKeeper = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.goalkeeper_animation);
        animGoalKeeper2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.goalkeeper_animation2);
        animHIHI = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hihi_animation);
        animAngry = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.angry_animation);
        animFallDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.falldown_animation);


        imgBall = (ImageView) findViewById(R.id.imageView2);
        imgGoalKeeper = (ImageView) findViewById(R.id.imageView3);
        imgHIHI = (ImageView) findViewById(R.id.imageView5);
        imgAngry = (ImageView) findViewById(R.id.imageView6);
        btnKick = (Button) findViewById(R.id.button);
        btnKickL = (Button) findViewById(R.id.buttonLeft);
        btnKickR = (Button) findViewById(R.id.buttonRight);

        animGoalKeeper.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(pos == btn){
                    imgGoalKeeper.startAnimation(animFallDown);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animGoalKeeper2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(pos == btn){
                    imgGoalKeeper.startAnimation(animFallDown);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animBall.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgHIHI.setVisibility(View.VISIBLE);
                imgHIHI.startAnimation(animHIHI);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animBallL.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgHIHI.setVisibility(View.VISIBLE);
                imgHIHI.startAnimation(animHIHI);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animBallR.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgHIHI.setVisibility(View.VISIBLE);
                imgHIHI.startAnimation(animHIHI);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animHIHI.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgAngry.setVisibility(View.VISIBLE);
                imgAngry.startAnimation(animAngry);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btnKick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = 2;
                imgBall.startAnimation(animBall);
                pos = Math.round(Math.random()*2);
                if(pos == 0) {
                    imgGoalKeeper.startAnimation(animGoalKeeper);
                }else if(pos ==1 ){
                    imgGoalKeeper.startAnimation(animGoalKeeper2);
                }

            }
        });

        btnKickL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = 1;
                imgBall.startAnimation(animBallL);
                pos = Math.round(Math.random()*2);
                Log.d("av", String.valueOf(pos));
                if(pos == 0) {
                    imgGoalKeeper.startAnimation(animGoalKeeper);
                }else if(pos ==1 ){
                    imgGoalKeeper.startAnimation(animGoalKeeper2);
                }

            }
        });

        btnKickR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = 0;
                imgBall.startAnimation(animBallR);
                pos = Math.round(Math.random()*2);
                if(pos == 0) {
                    imgGoalKeeper.startAnimation(animGoalKeeper);
                }else if(pos == 1 ){
                    imgGoalKeeper.startAnimation(animGoalKeeper2);
                }

            }
        });
    }
}
