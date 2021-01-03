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
    String playerName;
    String currentGame;
    private final int MAX_ROUNDS = 3;

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

        Intent intent = getIntent();
        playerName = intent.getStringExtra("playerName");

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

        //Hämtar spelarens aktiva spel från databasen

        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                player = new Player(playerName);

                if (snapshot.child("players").child(playerName).child("Game0ID").exists()) {

                    String gameIDString = snapshot.child("players").child(playerName).child("Game0ID").getValue().toString();


                    boolean playerOne;
                    int gameRoundsCheck = Integer.parseInt(snapshot.child("activeGames").child(gameIDString).child("gameRound").getValue().toString());
                    if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){
                        playerOne = true;
                    }
                    else{
                        playerOne = false;
                    }

                    if(gameRoundsCheck == MAX_ROUNDS){

                        spel0Knapp.setText("Spel avslutat!");
                        spel0Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame0IDAvalible(false);
                        player.setGame0ID(foundGameID);
                        player.setGame0Finished(true);
                        spel0Knapp.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    else{
                        if(gameRoundsCheck % 2 == 1 && playerOne == false){
                            spel0Knapp.setEnabled(true);
                        }
                        else if(gameRoundsCheck % 2 == 1 && playerOne == true){
                            spel0Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == false){
                            spel0Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == true){
                            spel0Knapp.setEnabled(true);
                        }


                        if(snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString().equals("")){
                            spel0Knapp.setText("Spel-ID: " + gameIDString);
                        }
                        else if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){

                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();

                            spel0Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);


                        }
                        else{
                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();


                            spel0Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);
                        }



                        spel0Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame0IDAvalible(false);
                        player.setGame0ID(foundGameID);
                    }



                }
                if (snapshot.child("players").child(playerName).child("Game1ID").exists()) {

                    String gameIDString = snapshot.child("players").child(playerName).child("Game1ID").getValue().toString();


                    boolean playerOne;
                    int gameRoundsCheck = Integer.parseInt(snapshot.child("activeGames").child(gameIDString).child("gameRound").getValue().toString());
                    if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){
                        playerOne = true;
                    }
                    else{
                        playerOne = false;
                    }
                    if(gameRoundsCheck == MAX_ROUNDS){

                        spel1Knapp.setText("Spel avslutat!");
                        spel1Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame1IDAvalible(false);
                        player.setGame1ID(foundGameID);
                        player.setGame1Finished(true);
                        spel1Knapp.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    else{
                        if(gameRoundsCheck % 2 == 1 && playerOne == false){
                            spel1Knapp.setEnabled(true);
                        }
                        else if(gameRoundsCheck % 2 == 1 && playerOne == true){
                            spel1Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == false){
                            spel1Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == true){
                            spel1Knapp.setEnabled(true);
                        }


                        if(snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString().equals("")){
                            spel1Knapp.setText("Spel-ID: " + gameIDString);
                        }
                        else if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){

                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();

                            spel1Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);


                        }
                        else{
                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();


                            spel1Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);
                        }



                        spel1Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame1IDAvalible(false);
                        player.setGame1ID(foundGameID);
                    }
                }
                if (snapshot.child("players").child(playerName).child("Game2ID").exists()) {

                    String gameIDString = snapshot.child("players").child(playerName).child("Game2ID").getValue().toString();


                    boolean playerOne;
                    int gameRoundsCheck = Integer.parseInt(snapshot.child("activeGames").child(gameIDString).child("gameRound").getValue().toString());
                    if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){
                        playerOne = true;
                    }
                    else{
                        playerOne = false;
                    }
                    if(gameRoundsCheck == MAX_ROUNDS){

                        spel2Knapp.setText("Spel avslutat!");
                        spel2Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame2IDAvalible(false);
                        player.setGame2ID(foundGameID);
                        player.setGame2Finished(true);
                        spel2Knapp.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    else{
                        if(gameRoundsCheck % 2 == 1 && playerOne == false){
                            spel2Knapp.setEnabled(true);
                        }
                        else if(gameRoundsCheck % 2 == 1 && playerOne == true){
                            spel2Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == false){
                            spel2Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == true){
                            spel2Knapp.setEnabled(true);
                        }


                        if(snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString().equals("")){
                            spel2Knapp.setText("Spel-ID: " + gameIDString);
                        }
                        else if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){

                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();

                            spel2Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);


                        }
                        else{
                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();


                            spel2Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);
                        }



                        spel2Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame2IDAvalible(false);
                        player.setGame2ID(foundGameID);
                    }
                }
                if (snapshot.child("players").child(playerName).child("Game3ID").exists()) {

                    String gameIDString = snapshot.child("players").child(playerName).child("Game3ID").getValue().toString();


                    boolean playerOne;
                    int gameRoundsCheck = Integer.parseInt(snapshot.child("activeGames").child(gameIDString).child("gameRound").getValue().toString());
                    if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){
                        playerOne = true;
                    }
                    else{
                        playerOne = false;
                    }
                    if(gameRoundsCheck == MAX_ROUNDS){

                        spel3Knapp.setText("Spel avslutat!");
                        spel3Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame3IDAvalible(false);
                        player.setGame3ID(foundGameID);
                        player.setGame3Finished(true);
                        spel3Knapp.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    else{
                        if(gameRoundsCheck % 2 == 1 && playerOne == false){
                            spel3Knapp.setEnabled(true);
                        }
                        else if(gameRoundsCheck % 2 == 1 && playerOne == true){
                            spel3Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == false){
                            spel3Knapp.setEnabled(false);
                        }
                        else if(gameRoundsCheck % 2 == 0 && playerOne == true){
                            spel3Knapp.setEnabled(true);
                        }


                        if(snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString().equals("")){
                            spel3Knapp.setText("Spel-ID: " + gameIDString);
                        }
                        else if(snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString().equals(playerName)){

                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerTwo").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();

                            spel3Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);


                        }
                        else{
                            String opponent = snapshot.child("activeGames").child(gameIDString).child("playerOne").getValue().toString();
                            String yourScore = snapshot.child("activeGames").child(gameIDString).child("playerTwoPoints").getValue().toString();
                            String opponentScore = snapshot.child("activeGames").child(gameIDString).child("playerOnePoints").getValue().toString();


                            spel3Knapp.setText(opponent + " " + opponentScore + "-" + yourScore);
                        }



                        spel3Knapp.setVisibility(View.VISIBLE);
                        foundGameID = Integer.parseInt(gameIDString);
                        player.getGameList().add(new Game(foundGameID));
                        player.setGame3IDAvalible(false);
                        player.setGame3ID(foundGameID);
                    }
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
        Intent onFinishedIntent = new Intent(gamesActivity.this, GameFinishedActivity.class);

        String stringGameNumber;
        //Här skickas bara en siffra med till gameplay activity för att indikera vilket spel/knapp man tryckte på
        switch (btnClicked) {
            case 0:
                gameNumber = player.getGame0ID();
                stringGameNumber = String.valueOf(gameNumber);
                if(player.isGame0Finished())
                {
                    onFinishedIntent.putExtra("gameID", stringGameNumber);
                    onFinishedIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onFinishedIntent);
                }
                else{
                    onClickIntent.putExtra("gameID", stringGameNumber);
                    onClickIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onClickIntent);
                }
                break;
            case 1:
                gameNumber = player.getGame1ID();
                stringGameNumber = String.valueOf(gameNumber);
                if(player.isGame1Finished())
                {
                    onFinishedIntent.putExtra("gameID", stringGameNumber);
                    onFinishedIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onFinishedIntent);
                }
                else{
                    onClickIntent.putExtra("gameID", stringGameNumber);
                    onClickIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onClickIntent);
                }
                break;
            case 2:
                gameNumber = player.getGame2ID();
                stringGameNumber = String.valueOf(gameNumber);

                if(player.isGame2Finished())
                {
                    onFinishedIntent.putExtra("gameID", stringGameNumber);
                    onFinishedIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onFinishedIntent);
                }
                else{
                    onClickIntent.putExtra("gameID", stringGameNumber);
                    onClickIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onClickIntent);
                }

                break;
            case 3:
                gameNumber = player.getGame3ID();
                stringGameNumber = String.valueOf(gameNumber);
                if(player.isGame3Finished())
                {
                    onFinishedIntent.putExtra("gameID", stringGameNumber);
                    onFinishedIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onFinishedIntent);
                }
                else{
                    onClickIntent.putExtra("gameID", stringGameNumber);
                    onClickIntent.putExtra("playerName", player.getPlayerName());
                    startActivity(onClickIntent);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btnClicked);
        }

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
            spel0Knapp.setEnabled(true);
            spel1Knapp.setEnabled(true);
            spel2Knapp.setEnabled(true);
            spel3Knapp.setEnabled(true);

        }
        else { //Avbryt lämna spel läge genom att trycka på knappen igen
            finish();
            startActivity(getIntent());
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
        int gameNumber;

        switch (btnClicked) {
            case 0:
                gameNumber = player.getGame0ID();
                player.setGame0IDAvalible(true);
                spel0Knapp.setVisibility(View.INVISIBLE);
                player.getGameList().remove(indexOfEqualGameID(gameNumber));
                currentGame = "Game0ID";
                break;
            case 1:
                gameNumber = player.getGame1ID();
                player.setGame1IDAvalible(true);
                spel1Knapp.setVisibility(View.INVISIBLE);
                player.getGameList().remove(indexOfEqualGameID(gameNumber));
                currentGame = "Game1ID";
                break;
            case 2:
                gameNumber = player.getGame2ID();
                player.setGame2IDAvalible(true);
                spel2Knapp.setVisibility(View.INVISIBLE);;
                player.getGameList().remove(indexOfEqualGameID(gameNumber));
                currentGame = "Game2ID";
                break;
            case 3:
                gameNumber = player.getGame3ID();
                player.setGame3IDAvalible(true);
                spel3Knapp.setVisibility(View.INVISIBLE);
                player.getGameList().remove(indexOfEqualGameID(gameNumber));
                currentGame = "Game3ID";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btnClicked);
        }

        myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(String.valueOf(gameNumber));
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerTwo = snapshot.child("playerTwo").getValue().toString();
                String playerOne = snapshot.child("playerOne").getValue().toString();

                myRef = FirebaseDatabase.getInstance().getReference().child("activeGames").child(String.valueOf(gameNumber));
                myRef.removeValue();

                myRef = FirebaseDatabase.getInstance().getReference().child("players").child(playerName).child(currentGame);
                myRef.setValue(null);

                if(snapshot.child("playerOne").getValue().toString().equals(playerName)){
                    myRef = FirebaseDatabase.getInstance().getReference().child("players").child(playerTwo).child(currentGame);
                }
                else{
                    myRef = FirebaseDatabase.getInstance().getReference().child("players").child(playerOne).child(currentGame);
                }
                myRef.setValue(null);

                finish();
                startActivity(getIntent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(gamesActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent onBackIntent = new Intent(gamesActivity.this, HomeActivity.class);
        startActivity(onBackIntent);
    }

    public int indexOfEqualGameID(int gameNumber) {
        if (gameNumber == player.getGameList().get(0).getGame_ID()) return 0;
        else if (gameNumber == player.getGameList().get(1).getGame_ID()) return 1;
        else if (gameNumber == player.getGameList().get(2).getGame_ID()) return 2;
        else if (gameNumber == player.getGameList().get(3).getGame_ID()) return 3;
        else {
            Toast.makeText(gamesActivity.this, "oopsie poopie", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}