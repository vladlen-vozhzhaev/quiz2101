package com.example.quiz2101;

public class Question {
    private int question; // ID вопроса, который указывает на строку в strings.xml
    private boolean answer; // true/false - правильный ответ
    // конструктор для создания объекта вопроса
    public Question(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }
    // геттер, для получения id строки с текстом вопроса
    public int getQuestion() {
        return question;
    }
    // геттер, для получения правильного ответа
    public boolean isAnswer() {
        return answer;
    }
}