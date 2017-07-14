package com.example.gobraille.gobraille;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class keluar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Prime Regular.otf");
        final long[] back = {0,90,90,90};
        final TextView keluar = (TextView) findViewById(R.id.keluar);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        keluar.setTypeface(custom_font);
        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                keluar.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent i   = new Intent(keluar.this,MainActivity.class);
                setResult(7, i);
                finish();
            }

        }.start();
        keluar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    keluar.setAlpha((float)0.5);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP ||
                        motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
                    keluar.setAlpha(1);
                }
                return false;
            }
        });
        keluar.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                vibe.vibrate(back, -1);
                Intent i   = new Intent(keluar.this,MainActivity.class);
                setResult(4, i);
                finish();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(keluar.this,MainActivity.class);
        setResult(4, i);
        finish();
        super.onBackPressed();
    }
}
