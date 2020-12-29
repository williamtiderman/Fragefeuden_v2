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



    public Player (String playerName){
        this.playerName = playerName;
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
}
