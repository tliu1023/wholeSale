package com.example.wholesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");

        TextView textview_greeting = findViewById(R.id.textview_greeting);
        textview_greeting.setText("Hello "+ userName + "!");

    }
}
