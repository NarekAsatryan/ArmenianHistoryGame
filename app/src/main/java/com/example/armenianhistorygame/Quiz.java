package com.example.armenianhistorygame;

import java.util.List;

public class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int currentQuestionIndex;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
    }

    public QuizQuestion getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean isLastQuestion() {
        return currentQuestionIndex == questions.size() - 1;
    }

    public void moveToNextQuestion() {
        if (!isLastQuestion()) {
            currentQuestionIndex++;
        }
    }

    public boolean checkAnswer(int answer) {
        boolean isCorrect = getCurrentQuestion().getAnswer() == answer;
        if (isCorrect) {
            incrementScore();
        }
        return isCorrect;
    }

    public int getScore() {
        return score;
    }

    void incrementScore() {
        score++;
    }
}
