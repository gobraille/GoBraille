package com.example.gobraille.gobraille;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class bantuan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_bantuan);
        TextView bantuanText = (TextView) findViewById(R.id.bantuan);
        final long[] back = {0,90,90,90};
        final TextView bantuan_back = (TextView) findViewById(R.id.bantuan_back);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/Prime Regular.otf");
        bantuanText.setTypeface(custom_font);
        bantuan_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    bantuan_back.setAlpha((float)0.5);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP ||
                        motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
                    bantuan_back.setAlpha(1);
                }
                return false;
            }
        });
        bantuan_back.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                vibe.vibrate(back, -1);
                Intent i   = new Intent(bantuan.this,MainActivity.class);
                setResult(3, i);
                finish();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(bantuan.this,MainActivity.class);
        setResult(3, i);
        finish();
        super.onBackPressed();
    }
}
