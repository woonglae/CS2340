package com.example.m6.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.m6.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buyButton;
    private Button sellButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buyButton = findViewById(R.id.buy_button);
        buyButton.setOnClickListener(this);
        sellButton = findViewById(R.id.sell_button);
        sellButton.setOnClickListener(this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void openBuyGoods() {
        Intent intent = new Intent(this, BuyGoodsActivity.class);
        startActivity(intent);
    }

    public void openSellGoods() {
        Intent intent = new Intent(this, SellGoodsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_button:
                openBuyGoods();
                break;
            case R.id.sell_button:
                openSellGoods();
                break;

        }
    }
}
