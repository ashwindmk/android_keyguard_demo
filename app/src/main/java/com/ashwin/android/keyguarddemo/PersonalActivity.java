package com.ashwin.android.keyguarddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PersonalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
