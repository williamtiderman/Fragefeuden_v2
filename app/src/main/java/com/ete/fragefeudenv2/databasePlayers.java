package com.ete.fragefeudenv2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class databasePlayers  {
    private int gameID, gameRound, playerOnePoints, playerTwoPoints;
    private String playerOne, playerTwo, playerOneDone, playerTwoDone;
    DatabaseReference myRef;
    FirebaseDatabase root;

    public databasePlayers(int gameID, String playerOne, String playerTwo, int playerOnePoints, int playerTwoPoints, int gameRound, String playerOneDone, String playerTwoDone){
        this.gameID = gameID;
        this.playerOne = playerOne;
        this.playerTwo = "";
        this.playerOnePoints = 0;
        this.playerTwoPoints = 0;
        this.gameRound = 1;
        this.playerOneDone = "false";
        this.playerTwoDone = "false";
    }
    public void gameStart(){

    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameRound() {
        return gameRound;
    }

    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public int getPlayerOnePoints() {
        return playerOnePoints;
    }

    public void setPlayerOnePoints(int playerOnePoints) {
        this.playerOnePoints = playerOnePoints;
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints;
    }

    public void setPlayerTwoPoints(int playerTwoPoints) {
        this.playerTwoPoints = playerTwoPoints;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getPlayerOneDone() {
        return playerOneDone;
    }

    public void setPlayerOneDone(String playerOne) {
        this.playerOneDone = playerOneDone;
    }

    public String getPlayerTwoDone() {
        return playerTwoDone;
    }

    public void setPlayerTwoDone(String playerTwo) {
        this.playerTwoDone = playerTwoDone;
    }
}
