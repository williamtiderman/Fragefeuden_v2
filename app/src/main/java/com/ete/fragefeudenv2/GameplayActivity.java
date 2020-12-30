package com.ete.fragefeudenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.Random;

public class GameplayActivity extends AppCompatActivity implements Runnable {

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        TextView questionTextBox = findViewById(R.id.questionText);
        Button optionButton1 = findViewById(R.id.optionButton1);
        Button optionButton2 = findViewById(R.id.optionButton2);
        Button optionButton3 = findViewById(R.id.optionButton3);
        Button optionButton4 = findViewById(R.id.optionButton4);



        myRef = FirebaseDatabase.getInstance().getReference().child("questions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int numberOfQuestions = (int) snapshot.getChildrenCount();
                int randomQuestionIndex = new Random().nextInt(numberOfQuestions);
                String questionString = "";
                String correctString = "";
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
                questionTextBox.setText(questionString);
                optionButton1.setText(correctString);
                optionButton2.setText(wrong1String);
                optionButton3.setText(wrong2String);
                optionButton4.setText(wrong3String);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GameplayActivity.this, "The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void run() {
        try {

        } catch (Exception e) {}
    }
}