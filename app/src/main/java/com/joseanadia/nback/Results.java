package com.joseanadia.nback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    TextView matchesLabel;
    TextView mistakesLabel;
    TextView scoreLabel;

    Intent results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        results = getIntent();

        matchesLabel = findViewById(R.id.lbl_matches);
        mistakesLabel = findViewById(R.id.lbl_mistakes);
        scoreLabel = findViewById(R.id.lbl_score);

        int correct = results.getIntExtra("correct", 0);
        int wrong = results.getIntExtra("wrong", 0);
        int missed = results.getIntExtra("missed", 0);

        int perfect = correct + missed;
        matchesLabel.setText(String.valueOf(correct) + "/" + String.valueOf(perfect));

        mistakesLabel.setText(String.valueOf(wrong));

        double score = ((double) correct/(double) (perfect+wrong))*100;
        scoreLabel.setText(String.format("%.2f", score) + "%");
    }

    public void tryAgain(View view) {
        startActivity(new Intent(Results.this, NBack.class));
    }
}
