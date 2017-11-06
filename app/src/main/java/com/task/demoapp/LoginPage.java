package com.task.demoapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginPage extends AppCompatActivity {

    AnimationDrawable animationDrawable;
    LinearLayout relativeLayout;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        relativeLayout = (LinearLayout) findViewById(R.id.relativeLayout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();

        final CircularProgressButton btn = (CircularProgressButton) findViewById(R.id.progressButtonNoPadding);


        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(2000);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn.revertAnimation();

                btn.startAnimation();

                Runnable myRunnable = new Runnable() {
                    public void run() {
                        // do something


                        btn.doneLoadingAnimation(Color.parseColor("#FF4081"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                        // btn.revertAnimation();
                        // btn.stopAnimation();

                        animateButtonAndRevert();


                    }
                };
                handler.postDelayed(myRunnable, 3000);

                // handler.postDelayed(runnable, 3000);

            }
        });


    }








    private void animateButtonAndRevert() {
        //val handler = Handler()




        Runnable myRunnable = new Runnable() {
            public void run() {
                // do something


                // btn.revertAnimation();
                // btn.stopAnimation();

                Intent i = new Intent(LoginPage.this, MainActivity.class);
                startActivity(i);


            }
        };
        handler.postDelayed(myRunnable, 1200);



    }


    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }


}
