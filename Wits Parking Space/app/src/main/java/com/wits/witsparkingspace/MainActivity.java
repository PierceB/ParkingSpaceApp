package com.wits.witsparkingspace;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView1, textView2;
    //ImageView imgvw1,imgvw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startFlash();
            }
        },2500);
    }

    public void west_campus(View view){
        Intent intent = new Intent(this, activity_west_campus.class);
        startActivity(intent);
    }

    public void east_campus(View view){
        Intent intent = new Intent(this, activity_east_campus.class);
        startActivity(intent);
    }

    private void startFlash() {
        textView1 = (TextView) findViewById(R.id.textView8);
        textView2 = (TextView) findViewById(R.id.textView9);
        //imgvw1 = (ImageView) findViewById(R.id.imageView2);
        //imgvw2 = (ImageView) findViewById(R.id.imageView3);


        Animation mAnimation = new AlphaAnimation(1,0);
        mAnimation.setDuration(900);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);


        textView1.startAnimation(mAnimation);
        textView2.startAnimation(mAnimation);
        //imgvw1.startAnimation(mAnimation);
        //imgvw2.startAnimation(mAnimation);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }
}
