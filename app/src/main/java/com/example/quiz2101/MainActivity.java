package com.example.quiz2101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
// MainActivity является Launcher activity - это прописано в манифесте
public class MainActivity extends AppCompatActivity {
    // Создаём объекты кнопок и одной TextView изначально они пустые
    Button yesBtn;
    Button noBtn;
    Button showAnswer;
    TextView textView;
    // Массив с вопросами, массив содержащий объекты класса Question
    Question[] questions = {
      new Question(R.string.question1, true),
      new Question(R.string.question2, false),
      new Question(R.string.question3, true),
      new Question(R.string.question4, true),
      new Question(R.string.question5, false)
    };
    int questionIndex = 0; // Индекс вопроса (счётчик)
    final String TAG = "SYSTEM INFO:"; // Константа, используется как заголовок для вывода информации на консоль
    // Коллекция ответов пользователя
    ArrayList<UserAnswer> userAnswers = new ArrayList<>();
    @Override // @Override - метод переопределён
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // вызываем onCreate из класса AppCompatActivity
        setContentView(R.layout.activity_main); // размещает на экране шаблон активности
        Log.i(TAG, "Вызван метод onCreate()"); // вывод сообщения на консоль
        // При запуске приложения savedInstanceState пустой и равен null
        if(savedInstanceState != null){
            // Получаем сохранённый индекс вопроса
            questionIndex = savedInstanceState.getInt("questionIndex");
        }
        // Находим элемент по ID findViewById() и записывем его в переменную
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        textView = findViewById(R.id.textView);
        showAnswer = findViewById(R.id.showAnswer);
        // Обращаемся к textView и пишем в него текст
        // questions - массив вопросов
        // questions[questionIndex] - вопрос по индесу questionIndex
        // questions[questionIndex].getQuestion() - получаем id строки с текстом вопроса
        textView.setText(questions[questionIndex].getQuestion());
        // Устанавливаем слушателя события click на кнопки (ожидаем наступления события click)
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // вызываем метод проверки ответа, передавая в аргумент true - так как нажата кнопка "Да"
                checkAnswer(true);
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // вызываем метод проверки ответа, передавая в аргумент false - так как нажата кнопка "Нет"
                checkAnswer(false);
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаём намерение (интент) указывая кто и какую активность хочет запустить
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                // Extra - дополнение
                // put extra - положить дополнение
                // intent.putExtra("КЛЮЧ", "ЗНАЧЕНИЕ")
                // Дпоплнения мы передаём в интент для того, чтобы в вызванной активности AnswerActivity.class
                // воспользоваться этими данными
                // questions[questionIndex].isAnswer() - получаем правильный ответ на вопрос
                intent.putExtra("answer", questions[questionIndex].isAnswer());
                startActivity(intent); // запускаем активность
            }
        });
    }

    // boolean btn - true если нажали "Да", false если нажали "Нет"
    public void checkAnswer(boolean btn){
        // (ответ true И кнопка да) ИЛИ (ответ false И кнопка нет) = (true AND true) OR (!false AND !false)
        if((questions[questionIndex].isAnswer() && btn) || (!questions[questionIndex].isAnswer() && !btn)){
            // Всплывающее уведомление
            Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Не правильно", Toast.LENGTH_SHORT).show();
        }
        // Создаём ответ пользователя
        UserAnswer userAnswer = new UserAnswer(
                questions[questionIndex].getQuestion(), // получение id строки с текстом вопроса
                btn, // Что ответил пользователь boolean
                questions[questionIndex].isAnswer() // Правильный ответ на вопрос boolean
        );
        userAnswers.add(userAnswer); // добавляем ответ пользователя в коллекцию ответов
        // сравниваем индекс и кол-во вопросов-1
        // если последний вопрос, то открываем новую активность
        if (questionIndex == questions.length-1){
            // Создаём намерение (интент) указывая кто и какую активность хочет запустить
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            // intent.putExtra("КЛЮЧ", "ЗНАЧЕНИЕ")
            // Дпоплнения мы передаём в интент для того, чтобы в вызванной активности ResultActivity.class
            // воспользоваться этими данными
            // Обратите внимание на то, что мы передаём коллекцию с объектами
            // ЭТО является сериализацией, класс объекта который передвём обязательно
            // должен быть подписан на интерфейс Serializable
            intent.putExtra("userAnswers", userAnswers);
            startActivity(intent); // запускаем активность
        }
        // увеличиваем счётчик, когда questionIndex становится равным кол-ву вопросов,
        // его значение становится 0
        /*
        * questionIndex = (0+1)%5; // 1
        * questionIndex = (1+1)%5; // 2
        * questionIndex = (2+1)%5; // 3
        * questionIndex = (3+1)%5; // 4
        * questionIndex = (4+1)%5; // 0
        * */
        questionIndex = (questionIndex+1)%questions.length;
        // Обращаемся к textView и пишем в него текст
        // questions - массив вопросов
        // questions[questionIndex] - вопрос по индесу questionIndex
        // questions[questionIndex].getQuestion() - получаем id строки с текстом вопроса
        textView.setText(questions[questionIndex].getQuestion());
    }

    @Override // Сохраняем необходимые данные перед уничтожением активности
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        // сохраняем индекс текущего вопроса в коллекцию savedInstanceState
        savedInstanceState.putInt("questionIndex", questionIndex);
        Log.i(TAG, "Вызван метод onSaveInstanceState()");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "Вызван метод onStart()");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "Вызван метод onResume()");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "Вызван метод onPause()");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "Вызван метод onStop()");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Вызван метод onDestroy()");
    }
}