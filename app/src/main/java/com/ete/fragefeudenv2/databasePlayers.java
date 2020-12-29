package com.ete.fragefeudenv2;

public class databasePlayers {
    private String playerOne, playerTwo, playerOnePoints, playerTwoPoints, gameRound;

    public databasePlayers(String playerOne, String playerTwo, String playerOnePoints, String playerTwoPoints, String gameRound){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOnePoints = playerOnePoints;
        this.playerTwoPoints = playerTwoPoints;
        this.gameRound = gameRound;
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

    public String getPlayerOnePoints() {
        return playerOnePoints;
    }

    public void setPlayerOnePoints(String playerOnePoints) {
        this.playerOnePoints = playerOnePoints;
    }

    public String getPlayerTwoPoints() {
        return playerTwoPoints;
    }

    public void setPlayerTwoPoints(String playerTwoPoints) {
        this.playerTwoPoints = playerTwoPoints;
    }

    public String getGameRound() {
        return gameRound;
    }

    public void setGameRound(String gameRound) {
        this.gameRound = gameRound;
    }
}
