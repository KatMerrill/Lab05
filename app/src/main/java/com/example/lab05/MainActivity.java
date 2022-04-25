package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // onCreate, onStart, onResume, onPause, onStop, onRestart, onDestroy = 0;
    String[] countLabels = new String[]{"onCreate", "onStart", "onResume", "onPause", "onStop", "onRestart", "onDestroy"};
    int[] countValuesShared = new int[]{0, 0, 0, 0, 0, 0, 0};
    int[] countValuesLocal = new int[]{0, 0, 0, 0, 0, 0, 0};
    TextView localsView, sharedView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        localsView = findViewById(R.id.locals);
        sharedView = findViewById(R.id.shared);

        setInitialValues();
        // increments count for onCreate
        countValuesShared[0]++;
    }

    private void setInitialValues() {
        for (int x = 0; x < countValuesShared.length; x++) {
            countValuesShared[x] = sharedPreferences.getInt(countLabels[x], 0);
        }
        // displays the values on screen
        String[] stringLocals = new String[7];
        for (int x = 0; x < countValuesLocal.length; x++) {
            stringLocals[x] = "" + countValuesLocal[x];
        }
        localsView.setText(getString(R.string.default_vals, stringLocals));
    }
    private void storeValues() {
        for(int x = 0; x < countValuesShared.length; x++) {
            editor.putInt(countLabels[x], countValuesShared[x]);
        }
        editor.apply();

        // displays the values on screen
        String[] stringLocals = new String[7];
        for (int x = 0; x < countValuesLocal.length; x++) {
            stringLocals[x] = "" + countValuesLocal[x];
        }
        localsView.setText(getString(R.string.default_vals, stringLocals));
        String[] stringShared = new String[7];
        for (int x = 0; x < countValuesShared.length; x++) {
            stringShared[x] = "" + countValuesShared[x];
        }
        sharedView.setText(getString(R.string.default_vals, stringShared));
    }

    @Override
    protected void onStart() {
        super.onStart();
        countValuesShared[1]++;
        countValuesLocal[1]++;
        storeValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countValuesShared[2]++;
        countValuesLocal[2]++;
        storeValues();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countValuesShared[3]++;
        countValuesLocal[3]++;
        storeValues();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countValuesShared[4]++;
        countValuesLocal[4]++;
        storeValues();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countValuesShared[5]++;
        countValuesLocal[5]++;
        storeValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countValuesShared[6]++;
        countValuesLocal[6]++;
        storeValues();
    }
}