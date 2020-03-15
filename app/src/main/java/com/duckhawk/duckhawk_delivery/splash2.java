package com.duckhawk.duckhawk_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash2 extends AppCompatActivity {

    ImageView i ;
    private static int time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        i = findViewById(R.id.p);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash2.this,LoginActivity.class));
                finish();
            }
         },time);

        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.splashanim);
        i.startAnimation(myAnim);
//        startActivity(new Intent(this,LoginActivity.class));
    }
}
