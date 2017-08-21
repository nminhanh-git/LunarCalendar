package com.manhcorp.lunarcalendar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome_Activity extends AppCompatActivity {

    private final int LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(Welcome_Activity.this, MainActivity.class);
                Welcome_Activity.this.startActivity(welcomeIntent);
                Welcome_Activity.this.finish();
            }
        }, LENGTH);
    }
}
