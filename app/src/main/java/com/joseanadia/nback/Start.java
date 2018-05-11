package com.joseanadia.nback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    int change_settings = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sp = getApplicationContext().getSharedPreferences("nback", 0);
        editor = sp.edit();

    }

    public void play(View view) {
        Intent intent = new Intent(Start.this, NBack.class);
        startActivity(intent);
    }

    public void settings (View view) {
        Intent updateSettings = new Intent(Start.this, Settings.class);
        if (sp.getInt("level", 6) == 5) {
            updateSettings.putExtra("level", 1);
        } else {
            updateSettings.putExtra("level", sp.getInt("level", 5));
        }
        startActivity(updateSettings);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == change_settings) {
            if (resultCode == RESULT_OK) {
                int level = data.getIntExtra("level", 0);
                editor.putInt("level", level);
                editor.commit();
            }
        }
    }*/
}