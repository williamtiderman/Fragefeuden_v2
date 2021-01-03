package com.ete.fragefeudenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameFinishedActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private String currentGame;
    private String gameNumber;
    private String playerName;
    private String playerScore;
    private String opponentName;
    private String opponentScore;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        Intent intent = getIntent();
        gameNumber = intent.getStringExtra("gameID");
        currentGame = intent.getStringExtra("currentGame");
        playerName = intent.getStringExtra("playerName");

        myRef = FirebaseDatabase.getInstance().getReference("activeGames").child(gameNumber);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("playerOne").getValue().toString().equals(playerName)) {
                    playerScore = snapshot.child("playerOnePoints").getValue().toString();
                    opponentScore = snapshot.child("playerTwoPoints").getValue().toString();
                    opponentName = snapshot.child("playerTwo").getValue().toString();
                }
                else {
                    playerScore = snapshot.child("playerTwoPoints").getValue().toString();
                    opponentScore = snapshot.child("playerOnePoints").getValue().toString();
                    opponentName = snapshot.child("playerOne").getValue().toString();
                }

                TextView resultTV = findViewById(R.id.resultTextView);
                if (Integer.parseInt(playerScore) > Integer.parseInt(opponentScore)) {
                    resultTV.setText("Du vann!");
                }
                else if (Integer.parseInt(playerScore) == Integer.parseInt(opponentScore)) {
                    resultTV.setText("Oavgjort!");
                }
                else {
                    resultTV.setText("Du förlorade!");
                }

                TextView yourScoreTV = findViewById(R.id.yourScoreTextView);
                TextView opponentScoreTV = findViewById(R.id.opponentScoreTextView);
                yourScoreTV.setText("Du: " + playerScore + " poäng");
                opponentScoreTV.setText(opponentName + ": " + opponentScore + " poäng");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GameFinishedActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickCompleteGame (View view) {
        myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("playerOne").getValue().toString().equals(playerName)) {
                    FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber).child("playerOneDone").setValue("true");
                }
                else {
                    FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber).child("playerTwoDone").setValue("true");
                }

                if (snapshot.child("playerOne").getValue().toString().equals(playerName)) { //om man är spelare 1
                    if (snapshot.child("playerTwoDone").getValue().toString().equals("true")) { //kolla om spelare två är klar
                        myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber); //ta isåfall bort matchen ifrån activeGames
                        myRef.removeValue();
                    }
                }
                else { //vice versa
                    if (snapshot.child("playerOneDone").getValue().toString().equals("true")) {
                        myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber);
                        myRef.removeValue();
                    }
                }
                myRef = FirebaseDatabase.getInstance().getReference().child("players").child(playerName).child(currentGame);
                myRef.setValue(null);

                Intent gamesIntent = new Intent(com.ete.fragefeudenv2.GameFinishedActivity.this, gamesActivity.class);
                startActivity(gamesIntent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GameFinishedActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean otherPlayerFinished() {
        myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(gameNumber);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GameFinishedActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
        return status;
    }
}