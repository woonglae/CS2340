package com.example.m6.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.m6.model.Universe;
import com.example.m6.R;

public class UniverseActivity extends AppCompatActivity {

    private Universe universe = new Universe();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

    }
}
