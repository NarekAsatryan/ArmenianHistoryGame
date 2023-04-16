package com.example.armenianhistorygame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button buttonConfirmNext;

    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuestion = findViewById(R.id.text_view_question);
        radioGroupOptions = findViewById(R.id.radio_group_options);
        radioButton1 = findViewById(R.id.radio_button1);
        radioButton2 = findViewById(R.id.radio_button2);
        radioButton3 = findViewById(R.id.radio_button3);
        radioButton4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        quiz = new Quiz(dbHelper.getQuestions());

        showQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        QuizQuestion question = quiz.getCurrentQuestion();
        textViewQuestion.setText(question.getQuestion());

        radioButton1.setText(question.getOptions().get(0));
        radioButton2.setText(question.getOptions().get(1));
        radioButton3.setText(question.getOptions().get(2));
        radioButton4.setText(question.getOptions().get(3));
        radioGroupOptions.clearCheck();
    }

    private void checkAnswer() {
        RadioButton selectedRadioButton = findViewById(radioGroupOptions.getCheckedRadioButtonId());
        int answerIndex = radioGroupOptions.indexOfChild(selectedRadioButton) + 1;

        if (answerIndex == quiz.getCurrentQuestion().getAnswer()) {
            quiz.incrementScore();
        }

        if (quiz.isLastQuestion()) {
            showResultActivity();
        } else {
            quiz.moveToNextQuestion();
            showQuestion();
        }
    }

    private void showResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", quiz.getScore());
        startActivity(intent);
        finish();
    }
}
