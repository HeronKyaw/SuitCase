package com.app.suitcase.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.suitcase.ui.fragments.main.DetailFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailFragment.newInstance())
                    .commitNow();
        }
    }
}