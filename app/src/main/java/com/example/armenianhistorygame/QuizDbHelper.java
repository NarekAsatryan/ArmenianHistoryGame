package com.example.armenianhistorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ArmenianHistoryQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "quiz_questions";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OPTION1 = "option1";
    private static final String KEY_OPTION2 = "option2";
    private static final String KEY_OPTION3 = "option3";
    private static final String KEY_OPTION4 = "option4";
    private static final String KEY_ANSWER = "answer";

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_QUESTION + " TEXT, " +
                KEY_OPTION1 + " TEXT, " +
                KEY_OPTION2 + " TEXT, " +
                KEY_OPTION3 + " TEXT, " +
                KEY_OPTION4 + " TEXT, " +
                KEY_ANSWER + " INTEGER)";
        db.execSQL(CREATE_TABLE);
        // Add the questions to the table here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questionList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                QuizQuestion question = new QuizQuestion();
                question.setQuestion(getStringFromCursor(cursor, KEY_QUESTION));
                question.setOption1(getStringFromCursor(cursor, KEY_OPTION1));
                question.setOption2(getStringFromCursor(cursor, KEY_OPTION2));
                question.setOption3(getStringFromCursor(cursor, KEY_OPTION3));
                question.setOption4(getStringFromCursor(cursor, KEY_OPTION4));
                question.setAnswer(cursor.getInt(cursor.getColumnIndex(KEY_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionList;
    }

    private String getStringFromCursor(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index >= 0) {
            return cursor.getString(index);
        }
        return null;
    }
}
