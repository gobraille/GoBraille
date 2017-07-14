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

public class games extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        final long[] back = {0,90,90,90};
        final TextView character = (TextView) findViewById(R.id.character);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Prime Regular.otf");
        character.setTypeface(custom_font);
        character.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    character.setAlpha((float)0.5);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() ==
                        MotionEvent.ACTION_CANCEL){
                    character.setAlpha(1);
                }
                return false;
            }
        });
        character.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                vibe.vibrate(back, -1);
                Intent i   = new Intent(games.this,MainActivity.class);
                setResult(2, i);
                finish();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(games.this,MainActivity.class);
        setResult(2, i);
        finish();
        super.onBackPressed();
    }
}
