package com.ete.fragefeudenv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addQuestionActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase root;
    String questionString, correctString, wrong1String, wrong2String, wrong3String;
    EditText question, correct, wrong1, wrong2, wrong3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toast.makeText(addQuestionActivity.this, "Connected", Toast.LENGTH_LONG).show();

        question = findViewById(R.id.editTextQuestion);
        correct = findViewById(R.id.editTextCorrect);
        wrong1 = findViewById(R.id.editTextWrong1);
        wrong2 = findViewById(R.id.editTextWrong2);
        wrong3 = findViewById(R.id.editTextWrong3);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (noEmptyValues()) {
                    root = FirebaseDatabase.getInstance();
                    myRef = root.getReference("questions");


                    questionString = question.getText().toString();
                    correctString = correct.getText().toString();
                    wrong1String = wrong1.getText().toString();
                    wrong2String = wrong2.getText().toString();
                    wrong3String = wrong3.getText().toString();
                    databaseAddQuestion addQuestionClass = new databaseAddQuestion(questionString, correctString, wrong1String, wrong2String, wrong3String);
                    myRef.child(questionString).setValue(addQuestionClass);

                    question.setText("");
                    correct.setText("");
                    wrong1.setText("");
                    wrong2.setText("");
                    wrong3.setText("");

                    Toast.makeText(addQuestionActivity.this, "Skickad!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(addQuestionActivity.this, "Du har ett eller flera tomma fält!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean noEmptyValues() {
        if (question.getText().toString().equals("") || correct.getText().toString().equals("") || wrong1.getText().toString().equals("") || wrong2.getText().toString().equals("") || wrong3.getText().toString().equals("")) {
            return false;
        } else return true;
    }
}