package com.example.trivia11a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1,btn2,btn3,btn4;
    private TextView tvQuestion;
    private TextView tvQusetionNumber,tvPoints,tvGameOver;
    private Collection collection;
    private Questions q;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvQuestion = findViewById(R.id.tvQuestion);
        btn1 = findViewById(R.id.btna1);
        btn2 = findViewById(R.id.btna2);
        btn3 = findViewById(R.id.btna3);
        btn4 = findViewById(R.id.btna4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        tvPoints = findViewById(R.id.tvPoints);
        tvQusetionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);

        tvGameOver.setVisibility(View.INVISIBLE);

        collection = new Collection();
        collection.initQusetions();

        nextQuestion();
    }

    private void nextQuestion() {
        if (collection.isNotLastQuestion()){
            q = collection.getNextQusetion();

            tvQuestion.setText(q.getQuestions());
            btn1.setText(q.getA1());
            btn2.setText(q.getA2());
            btn3.setText(q.getA3());
            btn4.setText(q.getA4());
        }
        else{
            tvGameOver.setVisibility(View.VISIBLE);
            createDialog();
        }
    }

    private void createDialog() {
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            if(q.getAnswers() == 1){
                points++;
            }
        }
        if(v == btn2){
            if(q.getAnswers() == 2){
                points++;
            }
        }
        if(v == btn3){
            if(q.getAnswers() == 3){
                points++;
            }
        }
        if(v == btn4){
            if(q.getAnswers() == 4){
                points++;
            }
        }
        tvPoints.setText("points: " + points);
        if(collection.isNotLastQuestion()){
            tvQusetionNumber.setText("Question number: " + (collection.getIndex() + 1));
        }
        nextQuestion();
    }

    public void reset(){
        this.points = 0;
        collection.initQusetions();
        tvPoints.setText("points: "+0);
        tvQusetionNumber.setText("question number: "+1);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();
    }
}