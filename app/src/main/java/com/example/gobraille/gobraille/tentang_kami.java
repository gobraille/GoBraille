package com.example.gobraille.gobraille;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class tentang_kami extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        final int vib = 90;
        final long[] back = {0,90,90,90};
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        TextView tentangText = (TextView) findViewById(R.id.tentang);
        TextView tentang_descText = (TextView) findViewById(R.id.tentang_desc);
        TextView tentang_namaText = (TextView) findViewById(R.id.tentang_nama);
        final ImageView tentang_back = (ImageView)  findViewById(R.id.tentang_back);
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(),
                "fonts/Prime Regular.otf");
        tentangText.setTypeface(custom_font);
        tentang_descText.setTypeface(custom_font);
        tentang_namaText.setTypeface(custom_font);
        tentang_back.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                vibe.vibrate(back, -1);
                Intent i   = new Intent(tentang_kami.this,MainActivity.class);
                setResult(2, i);
                finish();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(tentang_kami.this,MainActivity.class);
        setResult(2, i);
        finish();
        super.onBackPressed();
    }
}
