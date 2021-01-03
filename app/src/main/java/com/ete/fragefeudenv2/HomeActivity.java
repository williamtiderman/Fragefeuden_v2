package com.ete.fragefeudenv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
        //Alla älskar hundar
    }

    public void onClickPlay(View view) {

        EditText playerView = (EditText) findViewById(R.id.playerNameText);
        String playerName = playerView.getText().toString().toLowerCase().trim();

        Intent onClickIntent = new Intent(HomeActivity.this, gamesActivity.class);
        onClickIntent.putExtra("playerName", playerName);

        startActivity(onClickIntent);
    }

    public void onClickAddQuestion(View view) {
        Intent addQuestionIntent = new Intent(HomeActivity.this, addQuestionActivity.class);
        startActivity(addQuestionIntent);
    }

    @Override
    public void onBackPressed() {
        Intent quitIntent = new Intent(Intent.ACTION_MAIN);
        quitIntent.addCategory(Intent.CATEGORY_HOME);
        quitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(quitIntent);
    }
}