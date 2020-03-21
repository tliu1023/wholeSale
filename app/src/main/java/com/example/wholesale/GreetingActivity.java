package com.example.wholesale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.TintableBackgroundView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class GreetingActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(GreetingActivity.this, MainActivity.class);
                GreetingActivity.this.startActivity(mainIntent);
                GreetingActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
