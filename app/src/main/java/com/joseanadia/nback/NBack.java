package com.joseanadia.nback;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NBack extends AppCompatActivity {
    TextView initTime;
    TextView correctLabel;
    TextView correct;
    TextView missedLabel;
    TextView missed;
    TextView wrongLabel;
    TextView wrong;
    ProgressBar progBar;
    Button[] buttonGroup;

    Random r;
    int[] sequence = new int[20];
    int index = 0;
    ArrayList<Integer> rig = new ArrayList<Integer>();

    Handler unfill = new Handler();
    Handler increment = new Handler();

    CountDownTimer introTimer;
    Thread gameThread;

    boolean clicked = false;
    int correctValue = 0;
    int wrongValue = 0;
    int missedValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nback);

        initTime = findViewById(R.id.lbl_inittime);
        correctLabel = findViewById(R.id.lbl_correct);
        correct = findViewById(R.id.lbl_correct_value);
        wrongLabel = findViewById(R.id.lbl_wrong);
        wrong = findViewById(R.id.lbl_wrong_value);
        missedLabel = findViewById(R.id.lbl_missed);
        missed = findViewById(R.id.lbl_missed_value);

        progBar = findViewById(R.id.pbar_patterns);

        buttonGroup = new Button[9];
        buttonGroup[0] = findViewById(R.id.b0);
        buttonGroup[1] = findViewById(R.id.b1);
        buttonGroup[2] = findViewById(R.id.b2);
        buttonGroup[3] = findViewById(R.id.b3);
        buttonGroup[4] = findViewById(R.id.b4);
        buttonGroup[5] = findViewById(R.id.b5);
        buttonGroup[6] = findViewById(R.id.b6);
        buttonGroup[7] = findViewById(R.id.b7);
        buttonGroup[8] = findViewById(R.id.b8);

        r = new Random();
        for (int i=0; i<sequence.length; i++) {
            sequence[i] = r.nextInt(8);
        }

        for (int i=2; i<20; i++) {
            rig.add(i);
        }
        Collections.shuffle(rig);

        for (int i=0; i<8; i++) {
            sequence[rig.get(i)] = sequence[rig.get(i)-2];
        }

        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                buttonGroup[sequence[index]].setBackground(getResources().getDrawable(R.drawable.background_visual_filled, null));
                progBar.setProgress(index+1);
                unfill.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonGroup[sequence[index]].setBackground(getResources().getDrawable(R.drawable.background_visual_unfilled, null));
                    }
                }, 1500);
                increment.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean hit = false;
                        if (index >= 2 && sequence[index] == sequence[index-2]) {
                            hit = true;
                        }

                        if (!hit && clicked){
                            wrongValue++;
                        }

                        if (hit && !clicked) {
                            missedValue++;
                        }

                        if (hit && clicked) {
                            correctValue++;
                        }

                        correct.setText(String.valueOf(correctValue));
                        wrong.setText(String.valueOf(wrongValue));
                        missed.setText(String.valueOf(missedValue));

                        clicked = false;

                        index++;

                        if (index < 20) {
                            gameThread.run();
                        } else {
                            Intent getResults = new Intent(NBack.this, Results.class);
                            getResults.putExtra("correct", correctValue);
                            getResults.putExtra("wrong", wrongValue);
                            getResults.putExtra("missed", missedValue);
                            startActivity(getResults);
                        }

                    }
                }, 3500);
            }
        });

       introTimer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisTillFinished) {
                int init = (int) millisTillFinished/1000;
                initTime.setText(Integer.toString(init));
            }

            @Override
            public void onFinish() {
                initTime.setVisibility(View.INVISIBLE);
                correctLabel.setVisibility(View.VISIBLE);
                correct.setVisibility(View.VISIBLE);
                wrongLabel.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.VISIBLE);
                missedLabel.setVisibility(View.VISIBLE);
                missed.setVisibility(View.VISIBLE);

                gameThread.start();
            }
       };

       introTimer.start();
    }

    public void visualClick(View v) {
        clicked = true;
    }
}