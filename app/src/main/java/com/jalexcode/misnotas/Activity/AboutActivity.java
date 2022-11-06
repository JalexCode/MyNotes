package com.jalexcode.misnotas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jalexcode.misnotas.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("Acerca de");
    }
}