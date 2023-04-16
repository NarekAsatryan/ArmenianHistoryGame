package com.example.armenianhistorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.text_view_result);
        buttonRestart = findViewById(R.id.button_restart);

        int score = getIntent().getIntExtra("SCORE", 0);
        textViewResult.setText("Your score: " + score);

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartQuiz();
            }
        });
    }

    private void restartQuiz() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
