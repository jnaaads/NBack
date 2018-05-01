package com.joseanadia.nback;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NBack extends AppCompatActivity {
    Button[] buttonGroup;
    Random r;

    TextView tv;
    String out = "";

    int[] sequence = new int[20];
    int seqIndex = 0;

    boolean[] answers = new boolean[20];
    /*boolean[] inputs = new boolean[20];*/
    int correct = 0, hps = 0;
    int percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nback);

        buttonGroup = new Button[9];
        buttonGroup[0] = findViewById(R.id.b1);
        buttonGroup[1] = findViewById(R.id.b2);
        buttonGroup[2] = findViewById(R.id.b3);
        buttonGroup[3] = findViewById(R.id.b4);
        buttonGroup[4] = findViewById(R.id.b5);
        buttonGroup[5] = findViewById(R.id.b6);
        buttonGroup[6] = findViewById(R.id.b7);
        buttonGroup[7] = findViewById(R.id.b8);
        buttonGroup[8] = findViewById(R.id.b9);

        tv = findViewById(R.id.text_items);

        r = new Random();
        for (int i=0; i<sequence.length; i++) {
            if ((i+1)%5==0) {
                sequence[i] = sequence[i-1];
            } else {
                sequence[i] = r.nextInt(8);
            }
        }

        for (int i=0; i<answers.length; i++) {
            answers[i] = false;
        }

        /*for (int i=0; i<inputs.length; i++) {
            inputs[i] = false;
        }*/

        CountDownTimer timer = new CountDownTimer(63000, 3000) {
            @Override
            public void onTick(long l) {

                if (l<=60000){
                    buttonGroup[sequence[seqIndex-1]].setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));
                    if (sequence[seqIndex] == sequence[seqIndex-1]) {
                        answers[seqIndex] = true;
                    }
                }
                buttonGroup[sequence[seqIndex]].setBackgroundColor(getResources().getColor(R.color.colorAccent, null));

                out = seqIndex+1 + "/20";
                seqIndex++;

                tv.setText(out);
            }

            @Override
            public void onFinish() {
                Intent results = new Intent(getApplication(), Results.class);
                results.putExtra("percent", percent);
                startActivity(results);
            }
        };

        timer.start();
    }

    public void visualClick(View v) {
        /*inputs[seqIndex] = true;*/
        hps++;

        if (answers[seqIndex]) {
            correct++;
        }

        percent = (correct/hps)*100;
    }
}
