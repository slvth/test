package com.example.pushup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if("PlayA".equals(getIntent().getStringExtra("command"))){
            AlarmReceiver.notificationManager.cancelAll();
        }
    }
}