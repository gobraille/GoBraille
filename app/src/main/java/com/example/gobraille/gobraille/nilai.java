package com.example.gobraille.gobraille;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class nilai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        final long[] back = {0,90,90,90};
        final TextView nilaiTertinggi = (TextView) findViewById(R.id.nilai_tertinggi);
        final TextView nilaiBack = (TextView) findViewById(R.id.nilai_back);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Prime Regular.otf");
        nilaiTertinggi.setTypeface(custom_font);
        nilaiBack.setTypeface(custom_font);
        nilaiBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    nilaiBack.setAlpha((float)0.5);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP ||
                        motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
                    nilaiBack.setAlpha(1);
                }
                return false;
            }
        });
        nilaiBack.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                vibe.vibrate(back, -1);
                Intent i   = new Intent(nilai.this,MainActivity.class);
                setResult(3, i);
                finish();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(nilai.this,MainActivity.class);
        setResult(3, i);
        finish();
        super.onBackPressed();
    }
}
