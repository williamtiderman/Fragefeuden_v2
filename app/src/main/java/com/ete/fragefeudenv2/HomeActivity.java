package com.ete.fragefeudenv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences namePreference;
    private EditText playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
        playerView = (EditText) findViewById(R.id.playerNameText);

        namePreference = getSharedPreferences("previousPlayerName", MODE_PRIVATE);
        playerView.setText(namePreference.getString("previousPlayerName",""));
    }

    public void onClickPlay(View view) {
        String playerName = playerView.getText().toString();

        SharedPreferences.Editor editor = namePreference.edit();
        editor.putString("previousPlayerName", playerName);
        editor.apply();

        Intent onClickIntent = new Intent(HomeActivity.this, gamesActivity.class);
        onClickIntent.putExtra("playerName", playerName.toLowerCase().trim());

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