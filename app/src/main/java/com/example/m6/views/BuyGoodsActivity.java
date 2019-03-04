package com.example.m6.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m6.R;
import com.example.m6.model.Goods;
import com.example.m6.model.Player;
import com.example.m6.model.Resource;
import com.example.m6.model.Spaceship;
import com.example.m6.viewmodels.collection;

public class BuyGoodsActivity extends AppCompatActivity implements BuyDialog.BuyDialogListener {
    Player player;
    TextView waterPrice, furPrice, foodPrice, orePrice, firearmPrice, gamePrice, medicinePrice, machinePrice, narcorticsPrice, robotPrice;
    int water, fur, food, ore, firearm, game, medicine, machine, narcortics, robot;
    TextView credit, bay;
    Button buy_water;
    Button waterMax, furMax, foodMax, oreMax, firearmMax, gameMax, medicineMax, machineMax, narcorticsMax, robotMax;
    String inputStr = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_goods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        player = (Player)getIntent().getSerializableExtra("player");
        Log.d("player", player.getName()+" is into BuyActivity sucessfully" );

        credit = findViewById(R.id.buy_credit);
        credit.setText(String.valueOf(player.getCredit())+" Cr");

        bay = findViewById(R.id.buy_bays);
        bay.setText(String.valueOf(player.getCargo())+"/"+player.getSpaceship().getBay());

        setupPrice();

        waterMax = findViewById(R.id.buy_max_water);
        clickMaxButton(waterMax, water, Goods.WATER);

        furMax = findViewById(R.id.buy_max_furs);
        clickMaxButton(furMax, fur, Goods.FURS);

        foodMax = findViewById(R.id.buy_max_food);
        clickMaxButton(foodMax, food, Goods.FOOD);

        oreMax = findViewById(R.id.buy_max_ore);
        clickMaxButton(oreMax, ore, Goods.ORE);

        firearmMax = findViewById(R.id.buy_max_firearms);
        clickMaxButton(firearmMax, firearm, Goods.FIREARMS);

        gameMax = findViewById(R.id.buy_max_games);
        clickMaxButton(gameMax, game, Goods.GAMES);

        medicineMax = findViewById(R.id.buy_max_medicine);
        clickMaxButton(medicineMax, medicine, Goods.MEDICINE);

        machineMax = findViewById(R.id.buy_max_machine);
        clickMaxButton(machineMax, machine, Goods.MACHINES);

        narcorticsMax = findViewById(R.id.buy_max_narcortics);
        clickMaxButton(narcorticsMax, narcortics, Goods.NARCOTICS);

        robotMax = findViewById(R.id.buy_max_robots);
        clickMaxButton(robotMax, robot, Goods.ROBOTS);

        buy_water =findViewById(R.id.buy_num_water);
        clickNumButton(buy_water, water, Goods.WATER);
//        buy_furs =findViewById(R.id.buy_num_furs);
//        clickNumButton(buy_water, water, Goods.FURS);
//
//        buy_food =findViewById(R.id.buy_num_food);
//        clickNumButton(buy_water, water, Goods.WATER);

//        buy_furs =findViewById(R.id.buy_num_furs);
//        clickNumButton(buy_water, water, Goods.FURS);
//
//        buy_food =findViewById(R.id.buy_num_food);
//        clickNumButton(buy_water, water, Goods.WATER);


    }
    public void clickMaxButton(Button button, final int price, final Goods goods) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsAble(goods)) {
                    int max = Math.min(player.getSpaceship().getBay() - player.getCargo(), player.getCredit() / price);
                    if (max > 0) {
                        player.setCargo(player.getCargo() + max);
                        player.setCredit(player.getCredit() - max * price);
                        bay.setText(String.valueOf(player.getCargo()) + "/" + player.getSpaceship().getBay());
                        credit.setText(String.valueOf(player.getCredit()) + " Cr");
                        Toast.makeText(getApplicationContext(), "you bought " + max + " " + goods.toString(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "you can not buy anymore. check your bay or credit", Toast.LENGTH_LONG).show();
                    }
                } else {
                    openAlert();
                }
            }
        });
    }
    public void clickNumButton(Button button, final int price, final Goods goods){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(IsAble(goods)){
                    openBuy(goods.toString(), price);

                } else {
                    openAlert();
                }
            }
        });
    }
    public int calculatePrice(Goods goods){
        int resource = player.getCurrentplanet().getResource();
        int price = goods.getBasePrice() + goods.getIPL()*(player.getCurrentplanet().getTechLevel() - goods.getMTLP());

        boolean head = (Math.random() < 0.5);
        if (head) {
            price +=goods.getBasePrice()*((int)(Math.random()*goods.getVar())/100);
        } else {
            price -= goods.getBasePrice()*((int)(Math.random()*goods.getVar())/100);
        }

        if (goods.equals(Goods.WATER)) {
            if (resource==Resource.DROUGHT.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.LOTSOFWATER.getNumber()) {
                price = price / 3;
            }
            if (resource==Resource.DESERT.getNumber()) {
                price = price * 2;
            }
        }
        if (goods.equals(Goods.FURS)) {
            if (resource==Resource.COLD.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.RICHFAUNA.getNumber()) {
                price = price / 3;
            }
            if (resource==Resource.LIFELESS.getNumber()) {
                price = price * 2;
            }
        }

        if (goods.equals(Goods.FOOD)) {
            if (resource==Resource.CROPFAIL.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.RICHSOIL.getNumber()) {
                price = price / 3;
            }
            if (resource==Resource.POORSOIL.getNumber()) {
                price = price * 2;
            }
        }
        if (goods.equals(Goods.ORE)) {

            if (resource==Resource.MINERALRICH.getNumber()) {
                price = price / 3;
            }
            if (resource==Resource.MINERALPOOR.getNumber()) {
                price = price * 2;
            }
        }
        if (goods.equals(Goods.GAMES)) {
            if (resource==Resource.BOREDOM.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.ARTISTIC.getNumber()) {
                price = price / 3;
            }
        }
        if (goods.equals(Goods.FIREARMS)) {
            if (resource==Resource.WAR.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.WARLIKE.getNumber()) {
                price = price / 3;
            }
        }
        if (goods.equals(Goods.MEDICINE)) {
            if (resource==Resource.PLAGUE.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.LOTSOFHERBS.getNumber()) {
                price = price / 3;
            }
        }
        if (goods.equals(Goods.MACHINES)) {
            if (resource==Resource.LACKOFWORKERS.getNumber()) {
                price = price * 3;
            }
        }
        if (goods.equals(Goods.NARCOTICS)) {
            if (resource==Resource.LACKOFWORKERS.getNumber()) {
                price = price * 3;
            }
            if (resource==Resource.WEIRDMUSHROOMS.getNumber()) {
                price = price / 3;
            }
        }
        if (goods.equals(Goods.ROBOTS)) {
            if (resource==Resource.LACKOFWORKERS.getNumber()) {
                price = price * 3;
            }
        }

        return price;
    }
    public void setupPrice() {
        String price;

        waterPrice = findViewById(R.id.buy_price_water);
        if (IsAble(Goods.WATER)){
            water = calculatePrice(Goods.WATER);
            price = water + " cr";
        }
        else {
            price = "N/A";
        }
        waterPrice.setText(price);

        furPrice = findViewById(R.id.buy_price_furs);
        if (IsAble(Goods.FURS)){
            fur = calculatePrice(Goods.FURS);
            price = fur + " cr";
        }  else {
            price = "N/A";
        }
        furPrice.setText(price);

        foodPrice = findViewById(R.id.buy_price_food);
        if (IsAble(Goods.FOOD)) {
            food = calculatePrice(Goods.FOOD);
            price = food +" cr";
        } else {
            price = "N/A";
        }
        foodPrice.setText(price);

        orePrice = findViewById(R.id.buy_price_ore);
        if (IsAble(Goods.ORE)) {
            ore = calculatePrice(Goods.ORE);
            price = ore + " cr";
        } else {
            price = "N/A";
        }
        orePrice.setText(price);

        gamePrice = findViewById(R.id.buy_price_games);
        if (IsAble(Goods.GAMES)) {
            game = calculatePrice(Goods.GAMES);
            price = game +" cr";
        } else {
            price = "N/A";
        }
        gamePrice.setText(price);

        firearmPrice = findViewById(R.id.buy_price_firearms);
        if (IsAble(Goods.FIREARMS)) {
            firearm = calculatePrice(Goods.FIREARMS);
            price = firearm+" cr";
        } else {
            price = "N/A";
        }
        firearmPrice.setText(price);

        medicinePrice = findViewById(R.id.buy_price_medicine);
        if (IsAble(Goods.MEDICINE)){
            medicine = calculatePrice(Goods.MEDICINE);
            price = medicine + " cr";
        } else {
            price = "N/A";
        }
        medicinePrice.setText(price);

        machinePrice = findViewById(R.id.buy_price_machine);
        if (IsAble(Goods.MACHINES)) {
            machine = calculatePrice(Goods.MACHINES);
            price = machine +" cr";
        } else {
            price = "N/A";
        }
        machinePrice.setText(price);

        narcorticsPrice = findViewById(R.id.buy_price_narcortics);
        if (IsAble(Goods.NARCOTICS)){
            narcortics = calculatePrice(Goods.NARCOTICS);
            price = narcortics+ " cr";
        } else {
            price = "N/A";
        }
        narcorticsPrice.setText(price);

        robotPrice = findViewById(R.id.buy_price_robots);
        if (IsAble(Goods.ROBOTS)){
            robot = calculatePrice(Goods.ROBOTS);
            price = robot +" cr";
        } else {
            price = "N/A";
        }
        robotPrice.setText(price);


    }
    boolean IsAble(Goods goods) {
        return (player.getCurrentplanet().getTechLevel() - goods.getMTLP() < 0)? false : true;
    }
    public void openAlert(){
        AlertWindow alert = new AlertWindow();
        alert.show(getSupportFragmentManager(), "alert");
    }
    public void openBuy(String goods, int price) {
        DialogFragment frag = new BuyDialog();

        Bundle bundle = new Bundle();
        bundle.putString("goodstype", goods);
        bundle.putInt("price", price);
        frag.setArguments(bundle);
        frag.show(getSupportFragmentManager(), "dialog");

    }

    @Override
    public void onInputData(String input) {
        Toast.makeText(this, input+" items is purchased", Toast.LENGTH_LONG).show();
        inputStr =(input);

    }

    @Override
    public void buyItem(String goods, int price) {
        int max = Math.min(player.getSpaceship().getBay() - player.getCargo(), player.getCredit() / price);

        if (max > 0) {
            player.setCargo(player.getCargo() + Integer.parseInt(inputStr));
            player.setCredit(player.getCredit() - Integer.parseInt(inputStr) * price);
            bay.setText(String.valueOf(player.getCargo()) + "/" + player.getSpaceship().getBay());
            credit.setText(String.valueOf(player.getCredit()) + " Cr");
            Toast.makeText(getApplicationContext(), "you bought " + inputStr + " " + goods, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "you can not buy anymore. check your bay or credit", Toast.LENGTH_LONG).show();
        }
    }
}


//to do
//need to change bay textview once we go with spaceship entity.