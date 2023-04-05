package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView, questionsTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestions = QuestionAnswer.questions.length;

    int currentQuestionsIndex = 0;
    String selectAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionsTextView = findViewById(R.id.questions);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions :" +totalQuestions);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickButton = (Button) view;
        if(clickButton.getId() == R.id.submit_btn){

            if(selectAnswer.equals(QuestionAnswer.correct_answer[currentQuestionsIndex])){
                score ++;
            }

            currentQuestionsIndex++;
            loadNewQuestion();

        }else{
            selectAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion(){

        if(currentQuestionsIndex == totalQuestions){
            finishedQuiz();
            return;
        }

        questionsTextView.setText(QuestionAnswer.questions[currentQuestionsIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionsIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionsIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionsIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionsIndex][3]);

    }

    void  finishedQuiz(){
        String passStatus = "";
        if(score == totalQuestions){
            passStatus = "Topper";
        }else if(score > totalQuestions * 0.60) {
            passStatus = "Passed";
        }else {
            passStatus = "Failed";
        }

        Intent intent = new Intent(this,ScoreActivity.class);
        intent.putExtra("score",score);
        startActivity(intent);


    }

    void restartQuiz(){
        score = 0;
        currentQuestionsIndex = 0;
        loadNewQuestion();
    }

}