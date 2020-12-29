package com.ete.fragefeudenv2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String opponentName;
    private int playerScore;
    private int opponentScore;
    private List<Integer> usedQuestions = new ArrayList<>();
    private int game_ID;

    public Game(int game_ID){
        this.game_ID = game_ID;
    }

    public void setOpponentName(String name){
        this.opponentName = name;
    }

    public void incrementPlayerScore(){
        this.playerScore++;
    }

    public void incrementOpponentScore(){
        this.opponentScore++;
    }

    public void useQuestion(int questionID){
        this.usedQuestions.add(questionID);
    }

    public int getGame_ID() {
        return game_ID;
    }
}
