package com.ete.fragefeudenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {

    DatabaseReference myRef;
    Button clickedButton;
    String correctString;
    String questionString;
    TextView questionTextBox;
    Button optionButton1;
    Button optionButton2;
    Button optionButton3;
    Button optionButton4;
    Button nextQuestionButton;
    TextView resultText;

    int q = 0;

    ArrayList<String> usedQuestionsList = new ArrayList<String>();

    int answeredQuestions = 0;
    int correctAnswers = 0;
    int wrongAnswers = 0;

    int gameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);


        questionTextBox = findViewById(R.id.questionText);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        resultText = findViewById(R.id.resultTextView);

        String stringGameID = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        gameID = Integer.parseInt(stringGameID);

        Toast.makeText(GameplayActivity.this, String.valueOf(gameID), Toast.LENGTH_LONG).show();

        reset();
        getNewQuestion();
    }

    public void checkAnswer() {
        optionButton1.setEnabled(false);
        optionButton2.setEnabled(false);
        optionButton3.setEnabled(false);
        optionButton4.setEnabled(false);
        if (clickedButton.getText().equals(correctString)) {
            //Om man har rätt
            correctAnswers++;
            clickedButton.setBackgroundColor(Color.GREEN);
            resultText.setText("RÄTT!");
        }
        else {
            //Om man har fel
            clickedButton.setBackgroundColor(Color.RED); //Sätt tryckt knapp till röd
            resultText.setText("FEL!");
            //Sätt rätt svar till grön så man ser vad som var rätt
            wrongAnswers++;
            if (optionButton1.getText().equals(correctString)) optionButton1.setBackgroundColor(Color.GREEN);
            else if (optionButton2.getText().equals(correctString)) optionButton2.setBackgroundColor(Color.GREEN);
            else if (optionButton3.getText().equals(correctString)) optionButton3.setBackgroundColor(Color.GREEN);
            else if (optionButton4.getText().equals(correctString)) optionButton4.setBackgroundColor(Color.GREEN);
        }
        answeredQuestions++;
        nextQuestionButton.setVisibility(View.VISIBLE);
    }

    public void getNewQuestion() {

        myRef = FirebaseDatabase.getInstance().getReference().child("questions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int numberOfQuestions = (int) snapshot.getChildrenCount();
                int randomQuestionIndex = new Random().nextInt(numberOfQuestions);
                questionString = "";
                correctString = "";
                String wrong1String = "";
                String wrong2String = "";
                String wrong3String = "";

                //Bästa sättet jag hittade att slumpa fram en fråga
                int i = 0;
                for (DataSnapshot snap : snapshot.getChildren()) { //Uppdaterar fråga tills den når det framslumpade talet, ye det låter dumt men jag google och det var typ det bästa sättet
                    if (i == randomQuestionIndex) {
                        questionString = snap.child("questionString").getValue().toString();
                        correctString = snap.child("correctString").getValue().toString();
                        wrong1String = snap.child("wrong1String").getValue().toString();
                        wrong2String = snap.child("wrong2String").getValue().toString();
                        wrong3String = snap.child("wrong3String").getValue().toString();
                        break;
                    }
                    i++;
                }

                //if (questionUsed()) getNewQuestion();

                ArrayList<String> optionsList = new ArrayList<String>();
                optionsList.add(correctString);
                optionsList.add(wrong1String);
                optionsList.add(wrong2String);
                optionsList.add(wrong3String);
                Collections.shuffle(optionsList);
                usedQuestionsList.add(questionString);

                questionTextBox.setText(questionString);
                optionButton1.setText(optionsList.get(0));
                optionButton2.setText(optionsList.get(1));
                optionButton3.setText(optionsList.get(2));
                optionButton4.setText(optionsList.get(3));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GameplayActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void reset() {
        questionTextBox.setText("Laddar fråga...");
        optionButton1.setText("");
        optionButton2.setText("");
        optionButton3.setText("");
        optionButton4.setText("");
        resultText.setText("");
        optionButton1.setTextColor(Color.WHITE);
        optionButton2.setTextColor(Color.WHITE);
        optionButton3.setTextColor(Color.WHITE);
        optionButton4.setTextColor(Color.WHITE);
        optionButton1.setBackgroundColor(getResources().getColor(R.color.orange_700));
        optionButton2.setBackgroundColor(getResources().getColor(R.color.orange_700));
        optionButton3.setBackgroundColor(getResources().getColor(R.color.orange_700));
        optionButton4.setBackgroundColor(getResources().getColor(R.color.orange_700));
        optionButton1.setEnabled(true);
        optionButton2.setEnabled(true);
        optionButton3.setEnabled(true);
        optionButton4.setEnabled(true);
        nextQuestionButton.setVisibility(View.INVISIBLE);
    }

    public void onClickOption1Button(View view) {
        clickedButton = findViewById(R.id.optionButton1);
        checkAnswer();
    }

    public void onClickOption2Button(View view) {
        clickedButton = findViewById(R.id.optionButton2);
        checkAnswer();
    }

    public void onClickOption3Button(View view) {
        clickedButton = findViewById(R.id.optionButton3);
        checkAnswer();
    }

    public void onClickOption4Button(View view) {
        clickedButton = findViewById(R.id.optionButton4);
        checkAnswer();
    }

    public void onClickNextQuestionButton(View view) {
        if (answeredQuestions < 4) {
            reset();
            getNewQuestion();
        }
        else {
            myRef = FirebaseDatabase.getInstance().getReference("activeGames").child(String.valueOf(gameID));
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int gameRounds = Integer.parseInt(snapshot.child("gameRound").getValue().toString());
                    if(gameRounds % 2 == 0) {
                        int currentPoints = Integer.parseInt(snapshot.child("playerTwoPoints").getValue().toString());
                        myRef.child("playerTwoPoints").setValue(currentPoints + correctAnswers);
                        myRef.child("gameRound").setValue(gameRounds+1);
                    }
                    else if(gameRounds % 2 == 1){
                        int currentPoints = Integer.parseInt(snapshot.child("playerOnePoints").getValue().toString());
                        myRef.child("playerOnePoints").setValue(currentPoints + correctAnswers);
                        myRef.child("gameRound").setValue(gameRounds+1);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(GameplayActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
                }
            });
            Intent onClickIntent = new Intent(GameplayActivity.this, gamesActivity.class);
            startActivity(onClickIntent);
        }
    }

    @Override
    public void onBackPressed() {
        // Tom så att man inte kan backa ur spel
    }

    public boolean questionUsed() {
        //SKRIV METOD SOM KOLLAR OM FRÅGAN ÄR ANVÄND HÄR
        if (true) {
            return true;
        }
        else {
            return false;
        }
    }
}