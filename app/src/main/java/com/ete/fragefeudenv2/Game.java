package com.ete.fragefeudenv2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> usedQuestions = new ArrayList<>();
    private int game_ID;

    public Game(int game_ID){
        this.game_ID = game_ID;
    }
    public int getGame_ID() {
        return game_ID;
    }
}
