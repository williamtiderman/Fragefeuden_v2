package com.ete.fragefeudenv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gamesActivity extends AppCompatActivity {


    private List<Game> gameList = new ArrayList<>();
    Player player;
    DatabaseReference myRef;
    FirebaseDatabase root;
    private int gameID, gameRound, playerOnePoints, playerTwoPoints;
    private String playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Intent intent = getIntent();

        String playerName = intent.getStringExtra(Intent.EXTRA_TEXT);

        //Här ska det kollas med databasen om det finns en spelare med det namnet

        player = new Player(playerName);

        gameList = player.getGameList();
    }


    //Denna kallas när man startar nytt game
    public void createNewGame(View view){
        Random rand = new Random();
        final int TALTAK = 9999;
        int int_random = rand.nextInt(TALTAK);

        Game newGame = new Game(int_random);

        int numOfGames = player.getGameList().size();


        //Byt denna när man ska ha mer spel
        if(numOfGames < 4){
            player.getGameList().add(newGame);

            root = FirebaseDatabase.getInstance();
            myRef = root.getReference("activeGames");

            gameID = newGame.getGame_ID();
            playerOne = player.getPlayerName();
            databasePlayers sendGame = new databasePlayers(gameID, playerOne, playerTwo, playerOnePoints, playerTwoPoints, gameRound);
            myRef.child(String.valueOf(gameID)).setValue(sendGame);


            Button spelKnapp = null;

            switch(numOfGames){
                case 0:
                    spelKnapp = (Button) findViewById(R.id.spel0);
                    player.setGame0ID(int_random);
                    break;
                case 1:
                    spelKnapp = (Button) findViewById(R.id.spel1);
                    player.setGame1ID(int_random);
                    break;
                case 2:
                    spelKnapp = (Button) findViewById(R.id.spel2);
                    player.setGame2ID(int_random);
                    break;
                case 3:
                    spelKnapp = (Button) findViewById(R.id.spel3);
                    player.setGame3ID(int_random);
                    break;
            }

            spelKnapp.setText(String.valueOf(int_random));
            spelKnapp.setVisibility(View.VISIBLE);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Du kan ej ha mer än 4 aktiva spel!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Denna kallas när man fortsätter ett spel som redan är igång
    public void continueGame(View view, int btnClicked){

        switch(btnClicked){
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }


    }

    public void joinGame(View view){

        EditText joinGameNumber = (EditText) findViewById(R.id.joinGameNumber);
        int gameNumber = Integer.parseInt(joinGameNumber.getText().toString());


    }

    public void startaSpel0(View view){
        continueGame(view, 0);
    }
    public void startaSpel1(View view){
        continueGame(view, 1);
    }
    public void startaSpel2(View view){
        continueGame(view, 2);
    }
    public void startaSpel3(View view){
        continueGame(view, 3);
    }
}