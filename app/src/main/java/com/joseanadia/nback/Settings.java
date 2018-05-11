package com.joseanadia.nback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {
    SeekBar sbarLevel;
    Button doneButton;
    Intent updateSettings;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateSettings = getIntent();

        doneButton = findViewById(R.id.btn_done);
        sbarLevel = findViewById(R.id.sbar_level);

        sbarLevel.setProgress(updateSettings.getIntExtra("level", 0));
    }

    public void done (View view) {
        int level = sbarLevel.getProgress();
        editor.putInt("level", level);
        editor.commit();
        finish();
    }

    @Override
    public void onBackPressed() {
        done(doneButton);
    }
}
