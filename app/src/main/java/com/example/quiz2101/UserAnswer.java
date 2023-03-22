package com.example.quiz2101;

import java.io.Serializable;

public class UserAnswer implements Serializable {
    private int question; // ID вопроса, который указывает на строку в strings.xml
    private boolean userAnswer; // ответ пользователя (кнопка которую он нажал, true - да/false - нет)
    private boolean correctAnswer; // правильный ответ на вопрос
    // Конструктор
    public UserAnswer(int question, boolean userAnswer, boolean correctAnswer) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
    }
    // Геттеры
    public int getQuestion() {
        return question;
    }

    public boolean isUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
}
