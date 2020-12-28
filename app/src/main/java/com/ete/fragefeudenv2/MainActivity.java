package com.ete.fragefeudenv2;
//konga

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(com.ete.fragefeudenv2.MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, 2000); //antal millisekunder att visa splash screen
    }
}