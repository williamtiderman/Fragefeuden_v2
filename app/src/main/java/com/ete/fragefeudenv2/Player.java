package com.ete.fragefeudenv2;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    private List<Game> gameList = new ArrayList<>();
    private int game0ID;
    private int game1ID;
    private int game2ID;
    private int game3ID;
    private boolean game0IDAvalible;
    private boolean game1IDAvalible;
    private boolean game2IDAvalible;
    private boolean game3IDAvalible;
    private boolean game0Finished;
    private boolean game1Finished;

    public boolean isGame0Finished() {
        return game0Finished;
    }

    public void setGame0Finished(boolean game0Finished) {
        this.game0Finished = game0Finished;
    }

    public boolean isGame1Finished() {
        return game1Finished;
    }

    public void setGame1Finished(boolean game1Finished) {
        this.game1Finished = game1Finished;
    }

    public boolean isGame2Finished() {
        return game2Finished;
    }

    public void setGame2Finished(boolean game2Finished) {
        this.game2Finished = game2Finished;
    }

    public boolean isGame3Finished() {
        return game3Finished;
    }

    public void setGame3Finished(boolean game3Finished) {
        this.game3Finished = game3Finished;
    }

    private boolean game2Finished;
    private boolean game3Finished;



    public Player (String playerName){
        this.playerName = playerName;
        game0IDAvalible = true;
        game1IDAvalible = true;
        game2IDAvalible = true;
        game3IDAvalible = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public int getGame0ID() {
        return game0ID;
    }

    public void setGame0ID(int game0ID) {
        this.game0ID = game0ID;
    }

    public int getGame1ID() {
        return game1ID;
    }

    public void setGame1ID(int game1ID) {
        this.game1ID = game1ID;
    }

    public int getGame2ID() {
        return game2ID;
    }

    public void setGame2ID(int game2ID) {
        this.game2ID = game2ID;
    }

    public int getGame3ID() {
        return game3ID;
    }

    public void setGame3ID(int game3ID) {
        this.game3ID = game3ID;
    }

    public boolean getGame0IDAvalible() {
        return game0IDAvalible;
    }

    public void setGame0IDAvalible(boolean game0IDAvalible) {
        this.game0IDAvalible = game0IDAvalible;
    }

    public boolean getGame1IDAvalible() {
        return game1IDAvalible;
    }

    public void setGame1IDAvalible(boolean game1IDAvalible) {
        this.game1IDAvalible = game1IDAvalible;
    }

    public boolean getGame2IDAvalible() {
        return game2IDAvalible;
    }

    public void setGame2IDAvalible(boolean game2IDAvalible) {
        this.game2IDAvalible = game2IDAvalible;
    }

    public boolean getGame3IDAvalible() {
        return game3IDAvalible;
    }

    public void setGame3IDAvalible(boolean game3IDAvalible) {
        this.game3IDAvalible = game3IDAvalible;
    }
}
