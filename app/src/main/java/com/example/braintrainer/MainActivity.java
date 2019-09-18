package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    Button btnGo, btnAgain;
    TextView tvScore, tvTimer, tvSum, tvResult;
    Button button0, button1, button2, button3;
    int location;
    int numberOfQuestions = 0;
    int score = 0;
    boolean click = true;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void chooseAnswer(View view) {

        if (click == true) {
            if (Integer.toString(location).equals(view.getTag().toString())) {
                tvResult.setText("Correct :)");
                score++;
            } else {
                tvResult.setText("Wrong :(");
            }

            numberOfQuestions++;
            tvScore.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));

            newQuestion();
        }
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(101);
        int b = random.nextInt(101);

        tvSum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        location = random.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == location)
                answers.add(a + b);
            else {
                int wrongAnswer = random.nextInt(202);

                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(202);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        tvTimer.setText("30s");
        tvScore.setText("0 / 0");
        btnAgain.setVisibility(GONE);
        tvResult.setText("");
        click = true;

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {

                tvTimer.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                tvTimer.setText("0s");
                tvResult.setText("Done!");
                btnAgain.setVisibility(View.VISIBLE);
                click = false;

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.btnGo);
        btnAgain = findViewById(R.id.btnAgain);
        tvSum = findViewById(R.id.tvSum);
        tvResult = findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGo.setVisibility(GONE);
                tvTimer.setVisibility(View.VISIBLE);
                tvSum.setVisibility(View.VISIBLE);
                tvScore.setVisibility(View.VISIBLE);
                button0.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                btnAgain.setVisibility(View.VISIBLE);
                tvResult.setVisibility(View.VISIBLE);

                tvResult.setText("");

                newQuestion();

                playAgain(findViewById(R.id.btnAgain));
            }
        });

    }
}