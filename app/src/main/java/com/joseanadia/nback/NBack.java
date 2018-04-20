package com.joseanadia.nback;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class NBack extends AppCompatActivity {
    Button[] buttonGroup;
    int filledButton = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nback);

        buttonGroup = new Button[9];

        buttonGroup[0] = (Button) findViewById(R.id.b1);
        buttonGroup[1] = (Button) findViewById(R.id.b2);
        buttonGroup[2] = (Button) findViewById(R.id.b3);
        buttonGroup[3] = (Button) findViewById(R.id.b4);
        buttonGroup[4] = (Button) findViewById(R.id.b5);
        buttonGroup[5] = (Button) findViewById(R.id.b6);
        buttonGroup[6] = (Button) findViewById(R.id.b7);
        buttonGroup[7] = (Button) findViewById(R.id.b8);
        buttonGroup[8] = (Button) findViewById(R.id.b9);

        buttonGroup[filledButton].setBackgroundColor(getResources().getColor(R.color.colorAccent, null));

        CountDownTimer timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                buttonGroup[filledButton%9].setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));
                filledButton++;
                buttonGroup[filledButton%9].setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
            }

            @Override
            public void onFinish() {

            }
        };

        timer.start();
    }


}
