package com.example.quiz2101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ArrayList<UserAnswer> userAnswers = (ArrayList<UserAnswer>) getIntent().getSerializableExtra("userAnswers");
        resultTextView = findViewById(R.id.resultTextView);
        for (UserAnswer userAnswer: userAnswers) {
            Log.i("Текст вопроса:", getResources().getString(userAnswer.getQuestion()));
            Log.i("Ответ пользователя: ", String.valueOf(userAnswer.isUserAnswer()));
            Log.i("Правильный ответ: ", String.valueOf(userAnswer.isCorrectAnswer()));
            if(userAnswer.isUserAnswer() == userAnswer.isCorrectAnswer()){
                resultTextView.append(getResources().getString(userAnswer.getQuestion())+" - правильно\n");
            }else{
                resultTextView.append(getResources().getString(userAnswer.getQuestion())+" - не правильно\n");
            }
        }
    }
}