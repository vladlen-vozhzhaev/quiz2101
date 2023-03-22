package com.example.quiz2101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private boolean isAnswerTrue; // переменная в которую мы положим правильный ответ из intent
    private TextView answerTextView; // создаём объект TextView изначально пустой
    @Override // @Override - метод переопределён
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // вызываем onCreate из класса AppCompatActivity
        setContentView(R.layout.activity_answer); // размещает на экране шаблон активности
        // getIntent() - получаем интент с которым открыли активность (см. MainActivity)
        // getIntent().getBooleanExtra() - получаем дополнение типа Boolean с ключем "answer"
        isAnswerTrue = getIntent().getBooleanExtra("answer", false);
        // Находим элемент по ID findViewById() и записывем его в переменную
        answerTextView = findViewById(R.id.answerTextView);
        // Если isAnswerTrue == ture печатаем на экран "Ответ: ДА", в противном случае "Ответ: НЕТ"
        if(isAnswerTrue){
            answerTextView.setText("Ответ: ДА");
        }else{
            answerTextView.setText("Ответ: НЕТ");
        }

    }
}