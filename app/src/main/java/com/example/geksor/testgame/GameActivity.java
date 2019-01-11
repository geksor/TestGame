package com.example.geksor.testgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int
        correctRequest,
        currentScore = 0,
        currentLevel = 1;

    Button
        buttonObjectFirstRequest,
        buttonObjectSecondRequest,
        buttonObjectLastRequest;

    TextView
        textObjectFirstValue,
        textObjectLastValue,
        textObjectScore,
        textObjectLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textObjectFirstValue = findViewById(R.id.firstValue);
        textObjectLastValue = findViewById(R.id.lastValue);
        textObjectScore = findViewById(R.id.textScore);
        textObjectLevel = findViewById(R.id.textLevel);

        buttonObjectFirstRequest = findViewById(R.id.firstRequest);
        buttonObjectSecondRequest = findViewById(R.id.secondRequest);
        buttonObjectLastRequest = findViewById(R.id.lastRequest);


        buttonObjectFirstRequest.setOnClickListener(this);
        buttonObjectSecondRequest.setOnClickListener(this);
        buttonObjectLastRequest.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View v) {

        int requestValue = 0;

        switch (v.getId()){
            case R.id.firstRequest:
                requestValue = Integer.parseInt("" + buttonObjectFirstRequest.getText());
                break;
            case R.id.secondRequest:
                requestValue = Integer.parseInt("" + buttonObjectSecondRequest.getText());
                break;
            case R.id.lastRequest:
                requestValue = Integer.parseInt("" + buttonObjectLastRequest.getText());
                break;
        }
        updateScoreAndLevel(requestValue);
        setQuestion();
    }

    public void setQuestion(){
        int numberRange = currentLevel * 3;

        Random r = new Random();

        int partA = r.nextInt(numberRange) + 1;
        int partB = r.nextInt(numberRange) + 1;

        correctRequest = partA * partB;
        int
            wrong1 = correctRequest - 2,
            wrong2 = correctRequest + 2;

        textObjectFirstValue.setText("" + partA);
        textObjectLastValue.setText("" + partB);

        int buttonLayout = r.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectFirstRequest.setText(""+correctRequest);
                buttonObjectSecondRequest.setText(""+wrong1);
                buttonObjectLastRequest.setText(""+wrong2);
                break;
            case 1:
                buttonObjectSecondRequest.setText(""+correctRequest);
                buttonObjectLastRequest.setText(""+wrong1);
                buttonObjectFirstRequest.setText(""+wrong2);
                break;
            case 2:
                buttonObjectLastRequest.setText(""+correctRequest);
                buttonObjectFirstRequest.setText(""+wrong1);
                buttonObjectSecondRequest.setText(""+wrong2);
                break;
        }
    }

    public void updateScoreAndLevel(int requestValue){
        if(isCorrect(requestValue)){
            for(int i = 1; i <= currentLevel; i++){
                currentScore = currentScore + i;
            }
            currentLevel++;
        }else{
            currentScore = 0;
            currentLevel = 1;
        }


        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

    private boolean isCorrect(int requestValue) {

        boolean returnValue;

        if (requestValue == correctRequest){
            Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_LONG).show();
            returnValue = true;
        }else {
            Toast.makeText(getApplicationContext(), "Нет не верно", Toast.LENGTH_LONG).show();
            returnValue = false;
        }

        return returnValue;
    }
}
