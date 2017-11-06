package com.task.demoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.task.demoapp.view.ContentView;
import com.task.demoapp.view.MainView;
import com.task.demoapp.view.SplashView;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

        Animation slideUpAnimation, slideDownAnimation;
        ImageView imageView;
        LinearLayout linear;
        private static int SPLASH_TIME_OUT = 3100;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window w = getWindow(); // in Activity's onCreate() for instance
                w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
            setContentView(R.layout.splash_screen);

            linear = (LinearLayout) findViewById(R.id.linear);
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setVisibility(View.VISIBLE);

            slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_up_animation);

            slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_down_animation);


            imageView.startAnimation(slideUpAnimation);
            linear.startAnimation(slideDownAnimation);


          /*  new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity






                }
            }, 2500);*/



            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity





                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }




        public void startSlideUpAnimation(View view) {
            imageView.setVisibility(View.VISIBLE);
            imageView.startAnimation(slideUpAnimation);
        }

        public void startSlideDownAnimation(View view) {
            imageView.startAnimation(slideDownAnimation);
        }
    }