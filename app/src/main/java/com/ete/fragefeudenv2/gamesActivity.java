package com.ete.fragefeudenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class gamesActivity extends AppCompatActivity {


    private List<Game> gameList = new ArrayList<>();
    Player player;
    DatabaseReference myRef;
    FirebaseDatabase root;
    private int gameID, gameRound, playerOnePoints, playerTwoPoints;
    private String playerOne, playerTwo;
    private Button spelKnapp;
    private Button leaveGameButton;
    private boolean gameExists;
    private boolean leavingGame = false;
    private int foundGameID;
    private Button spel0Knapp;
    private Button spel1Knapp;
    private Button spel2Knapp;
    private Button spel3Knapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        String playerName = getIntent().getStringExtra("playerName");

        //Här ska det kollas med databasen om det finns en spelare med det namnet

        spel0Knapp = findViewById(R.id.spel0);
        spel1Knapp = findViewById(R.id.spel1);
        spel2Knapp = findViewById(R.id.spel2);
        spel3Knapp = findViewById(R.id.spel3);
        Button createNewGameButton = findViewById(R.id.startaNytt);
        Button joinGameButton = findViewById(R.id.joinGame);
        leaveGameButton = findViewById(R.id.leaveGameButton);

        createNewGameButton.setEnabled(false);
        joinGameButton.setEnabled(false);
        createNewGameButton.setText("Laddar Aktiva Spel...");

        Toast.makeText(gamesActivity.this, playerName, Toast.LENGTH_LONG).show();

        //Hämtar spelarens aktiva spel från databasen

        myRef = FirebaseDatabase.getInstance().getReference().child("players").child(playerName);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                player = new Player(playerName);

                if (snapshot.child("Game0ID").exists()) {
                    String gameIDString = snapshot.child("Game0ID").getValue().toString();
                    spel0Knapp.setText(gameIDString);
                    spel0Knapp.setVisibility(View.VISIBLE);
                    foundGameID = Integer.parseInt(gameIDString);
                    player.getGameList().add(new Game(foundGameID));
                    player.setGame0IDAvalible(false);
                    player.setGame0ID(foundGameID);
                }
                if (snapshot.child("Game1ID").exists()) {
                    String gameIDString = snapshot.child("Game1ID").getValue().toString();
                    spel1Knapp.setText(gameIDString);
                    spel1Knapp.setVisibility(View.VISIBLE);
                    foundGameID = Integer.parseInt(gameIDString);
                    player.getGameList().add(new Game(foundGameID));
                    player.setGame1IDAvalible(false);
                    player.setGame1ID(foundGameID);
                }
                if (snapshot.child("Game2ID").exists()) {
                    String gameIDString = snapshot.child("Game2ID").getValue().toString();
                    spel2Knapp.setText(gameIDString);
                    spel2Knapp.setVisibility(View.VISIBLE);
                    foundGameID = Integer.parseInt(gameIDString);
                    player.getGameList().add(new Game(foundGameID));
                    player.setGame2IDAvalible(false);
                    player.setGame2ID(foundGameID);
                }
                if (snapshot.child("Game3ID").exists()) {
                    String gameIDString = snapshot.child("Game3ID").getValue().toString();
                    spel3Knapp.setText(gameIDString);
                    spel3Knapp.setVisibility(View.VISIBLE);
                    foundGameID = Integer.parseInt(gameIDString);
                    player.getGameList().add(new Game(foundGameID));
                    player.setGame3IDAvalible(false);
                    player.setGame3ID(foundGameID);
                }
                createNewGameButton.setText("Skapa Nytt Spel");
                createNewGameButton.setEnabled(true);
                joinGameButton.setEnabled(true);

                gameList = player.getGameList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(gamesActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }


    //Denna kallas när man startar nytt game
    public void createNewGame(View view) {
        Random rand = new Random();
        final int TALTAK = 9999;
        int int_random = rand.nextInt(TALTAK);
        int numOfGames = player.getGameList().size();

        Game newGame = new Game(int_random);

        //Byt denna när man ska ha mer spel
        if (numOfGames < 4) {
            player.getGameList().add(newGame);

            root = FirebaseDatabase.getInstance();
            myRef = root.getReference("activeGames");

            gameID = newGame.getGame_ID();
            playerOne = player.getPlayerName();


            databasePlayers sendGame = new databasePlayers(gameID, playerOne, playerTwo, playerOnePoints, playerTwoPoints, gameRound);
            myRef.child(String.valueOf(gameID)).setValue(sendGame);

            myRef = root.getReference("players");


            spelKnapp = null;

            if (player.getGame0IDAvalible()) {
                spelKnapp = (Button) findViewById(R.id.spel0);
                player.setGame0ID(int_random);
                myRef.child(String.valueOf(player.getPlayerName())).child("Game0ID").setValue(player.getGame0ID());
                player.setGame0IDAvalible(false);
            }
            else if (player.getGame1IDAvalible()) {
                spelKnapp = (Button) findViewById(R.id.spel1);
                player.setGame1ID(int_random);
                myRef.child(String.valueOf(player.getPlayerName())).child("Game1ID").setValue(player.getGame1ID());
                player.setGame1IDAvalible(false);
            }
            else if (player.getGame2IDAvalible()) {
                spelKnapp = (Button) findViewById(R.id.spel2);
                player.setGame2ID(int_random);
                myRef.child(String.valueOf(player.getPlayerName())).child("Game2ID").setValue(player.getGame2ID());
                player.setGame2IDAvalible(false);
            }
            else if (player.getGame3IDAvalible()) {
                spelKnapp = (Button) findViewById(R.id.spel3);
                player.setGame3ID(int_random);
                myRef.child(String.valueOf(player.getPlayerName())).child("Game3ID").setValue(player.getGame3ID());
                player.setGame3IDAvalible(false);
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Du kan ej ha mer än 4 aktiva spel!", Toast.LENGTH_LONG);
                toast.show();
            }

            spelKnapp.setText(String.valueOf(int_random));
            spelKnapp.setVisibility(View.VISIBLE);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Du kan ej ha mer än 4 aktiva spel!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Denna kallas när man fortsätter ett spel som redan är igång
    public void continueGame(View view, int btnClicked) {
        int gameNumber;
        Intent onClickIntent = new Intent(gamesActivity.this, GameplayActivity.class);

        //Här skickas bara en siffra med till gameplay activity för att indikera vilket spel/knapp man tryckte på
        switch (btnClicked) {
            case 0:
                gameNumber = player.getGame0ID();
                break;
            case 1:
                gameNumber = player.getGame1ID();
                break;
            case 2:
                gameNumber = player.getGame2ID();
                break;
            case 3:
                gameNumber = player.getGame3ID();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btnClicked);
        }
        String stringGameNumber = String.valueOf(gameNumber);

        onClickIntent.putExtra("playerName", player.getPlayerName());
        onClickIntent.putExtra("gameID", stringGameNumber);
        startActivity(onClickIntent);
    }

    public void joinGame(View view) {
        int numOfGames = player.getGameList().size();
        if (numOfGames < 4) {
            EditText joinGameID = (EditText) findViewById(R.id.joinGameNumber);
            String inputID = joinGameID.getText().toString();
            int gameID = Integer.parseInt(inputID);
            joinGameID.setText("");
            playerTwo = player.getPlayerName();
            gameExists = false;

            myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(String.valueOf(gameID));
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) { //Om spelet finns
                        myRef.child("playerTwo").setValue(playerTwo);
                        player.getGameList().add(new Game(gameID));
                        myRef = FirebaseDatabase.getInstance().getReference().child("players");

                        if (player.getGame0IDAvalible()) {
                            spelKnapp = (Button) findViewById(R.id.spel0);
                            player.setGame0ID(gameID);
                            myRef.child(String.valueOf(player.getPlayerName())).child("Game0ID").setValue(player.getGame0ID());
                            player.setGame0IDAvalible(false);
                        }
                        else if (player.getGame1IDAvalible()) {
                            spelKnapp = (Button) findViewById(R.id.spel1);
                            player.setGame1ID(gameID);
                            myRef.child(String.valueOf(player.getPlayerName())).child("Game1ID").setValue(player.getGame1ID());
                            player.setGame1IDAvalible(false);
                        }
                        else if (player.getGame2IDAvalible()) {
                            spelKnapp = (Button) findViewById(R.id.spel2);
                            player.setGame2ID(gameID);
                            myRef.child(String.valueOf(player.getPlayerName())).child("Game2ID").setValue(player.getGame2ID());
                            player.setGame2IDAvalible(false);
                        }
                        else if (player.getGame3IDAvalible()) {
                            spelKnapp = (Button) findViewById(R.id.spel3);
                            player.setGame3ID(gameID);
                            myRef.child(String.valueOf(player.getPlayerName())).child("Game3ID").setValue(player.getGame3ID());
                            player.setGame3IDAvalible(false);
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Du kan ej ha mer än 4 aktiva spel!", Toast.LENGTH_LONG);
                            toast.show();
                        }

                        spelKnapp.setText(String.valueOf(gameID));
                        spelKnapp.setVisibility(View.VISIBLE);
                    } else { //om spelet inte finns
                        Toast.makeText(gamesActivity.this, "Spelet hittades ej!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(gamesActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Du kan ej ha mer än 4 aktiva spel!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClickLeaveGame(View view) {
        if (!leavingGame) { //Starta lämna-spel-läge när man trycker på knappen
            leavingGame = true;
            leaveGameButton.setText("Avbryt Lämna Spel");
            spel0Knapp.setBackgroundColor(Color.RED);
            spel1Knapp.setBackgroundColor(Color.RED);
            spel2Knapp.setBackgroundColor(Color.RED);
            spel3Knapp.setBackgroundColor(Color.RED);

        }
        else { //Avbryt lämna spel läge genom att trycka på knappen igen
            leavingGame = false;
            leaveGameButton.setText("Lämna Ett Spel");
            spel0Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
            spel1Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
            spel2Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
            spel3Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
        }
    }

    public void startaSpel0(View view) {
        if (!leavingGame) continueGame(view, 0);
        else leaveGame(view, 0);
    }

    public void startaSpel1(View view) {
        if (!leavingGame) continueGame(view, 1);
        else leaveGame(view, 1);
    }

    public void startaSpel2(View view) {
        if (!leavingGame) continueGame(view, 2);
        else leaveGame(view, 2);
    }

    public void startaSpel3(View view) {
        if (!leavingGame) continueGame(view, 3);
        else leaveGame(view, 3);
    }

    private void leaveGame(View view, int btnClicked) {
        leavingGame = false;
        leaveGameButton.setText("Lämna Ett Spel");
        spel0Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
        spel1Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
        spel2Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
        spel3Knapp.setBackgroundColor(getResources().getColor(R.color.orange_700));
        int numOfGames = player.getGameList().size();
        int gameNumber;

        switch (btnClicked) {
            case 0:
                gameNumber = player.getGame0ID();
                player.setGame0IDAvalible(false);
                spel0Knapp.setVisibility(View.INVISIBLE);
                /*gameList.remove();
                player.getGameList().remove();*/
                break;
            case 1:
                gameNumber = player.getGame1ID();
                player.setGame1IDAvalible(false);
                spel1Knapp.setVisibility(View.INVISIBLE);
                /*gameList.remove();
                player.getGameList().remove();*/
                break;
            case 2:
                gameNumber = player.getGame2ID();
                player.setGame2IDAvalible(false);
                spel2Knapp.setVisibility(View.INVISIBLE);
                /*gameList.remove();
                player.getGameList().remove();*/
                break;
            case 3:
                gameNumber = player.getGame3ID();
                player.setGame3IDAvalible(false);
                spel3Knapp.setVisibility(View.INVISIBLE);
                /*gameList.remove();
                player.getGameList().remove();*/
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btnClicked);
        }

        //TODO Delete activeGame from database & player class where gameID == gameNumber

    }

    @Override
    public void onBackPressed() {
        Intent onBackIntent = new Intent(gamesActivity.this, HomeActivity.class);
        startActivity(onBackIntent);
    }
}