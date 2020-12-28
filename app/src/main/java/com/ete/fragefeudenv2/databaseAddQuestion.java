package com.ete.fragefeudenv2;

public class databaseAddQuestion {
   private String questionString, correctString, wrong1String, wrong2String, wrong3String;

    public databaseAddQuestion(String questionString, String correctString, String wrong1String, String wrong2String, String wrong3String) {
        this.questionString = questionString;
        this.correctString = correctString;
        this.wrong1String = wrong1String;
        this.wrong2String = wrong2String;
        this.wrong3String = wrong3String;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getCorrectString() {
        return correctString;
    }

    public void setCorrectString(String correctString) {
        this.correctString = correctString;
    }

    public String getWrong1String() {
        return wrong1String;
    }

    public void setWrong1String(String wrong1String) {
        this.wrong1String = wrong1String;
    }

    public String getWrong2String() {
        return wrong2String;
    }

    public void setWrong2String(String wrong2String) {
        this.wrong2String = wrong2String;
    }

    public String getWrong3String() {
        return wrong3String;
    }

    public void setWrong3String(String wrong3String) {
        this.wrong3String = wrong3String;
    }
}
