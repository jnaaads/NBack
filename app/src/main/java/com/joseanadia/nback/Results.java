package com.joseanadia.nback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent result = getIntent();
        int i = result.getIntExtra("percent", 0);

        tv = findViewById(R.id.text_result);

        tv.setText(i + "%");
    }
}
