package com.ete.fragefeudenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {

    DatabaseReference myRef;
    Button clickedButton;
    String correctString;
    Button optionButton1;
    Button optionButton2;
    Button optionButton3;
    Button optionButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        TextView questionTextBox = findViewById(R.id.questionText);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);

        myRef = FirebaseDatabase.getInstance().getReference().child("questions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int numberOfQuestions = (int) snapshot.getChildrenCount();
                int randomQuestionIndex = new Random().nextInt(numberOfQuestions);
                String questionString = "";
                correctString = "";
                String wrong1String = "";
                String wrong2String = "";
                String wrong3String = "";

                //Bästa sättet jag hittade att slumpa fram en fråga
                int i = 0;
                for (DataSnapshot snap : snapshot.getChildren()) { //Uppdaterar fråga tills den når det framslumpade talet, ye det låter dumt men jag google och det var typ det bästa sättet
                    if(i == randomQuestionIndex) {
                        questionString = snap.child("questionString").getValue().toString();
                        correctString = snap.child("correctString").getValue().toString();
                        wrong1String = snap.child("wrong1String").getValue().toString();
                        wrong2String = snap.child("wrong2String").getValue().toString();
                        wrong3String = snap.child("wrong3String").getValue().toString();

                        break;
                    }
                    i++;
                }

                ArrayList<String> optionsList = new ArrayList<String>();
                optionsList.add(correctString);
                optionsList.add(wrong1String);
                optionsList.add(wrong2String);
                optionsList.add(wrong3String);
                Collections.shuffle(optionsList);

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

    public void checkAnswer() {
        //TextView resultText = findViewById(R.id.resultTextView);
        if (clickedButton.getText().equals(correctString)) {
            clickedButton.setBackgroundColor(Color.GREEN);
            //resultText.setText("Rätt!");
        }
        else {
            clickedButton.setBackgroundColor(Color.RED);
            //resultText.setText("Fel!");
            if (optionButton1.getText().equals(correctString)) optionButton1.setBackgroundColor(Color.GREEN);
            else if (optionButton2.getText().equals(correctString)) optionButton2.setBackgroundColor(Color.GREEN);
            else if (optionButton3.getText().equals(correctString)) optionButton3.setBackgroundColor(Color.GREEN);
            else if (optionButton4.getText().equals(correctString)) optionButton4.setBackgroundColor(Color.GREEN);
        }
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
}