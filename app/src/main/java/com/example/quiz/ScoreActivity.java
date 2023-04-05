package com.example.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        TextView text;

        //Intent intent = getIntent();

        int finalScore = getIntent().getExtras().getInt("score");

        text = findViewById(R.id.tvscore);

        text.setText("total Score:" +finalScore);

    }
}
