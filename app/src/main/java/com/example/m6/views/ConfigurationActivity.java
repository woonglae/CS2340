package com.example.m6.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m6.R;
import com.example.m6.model.Player;

public class ConfigurationActivity extends AppCompatActivity {

    private EditText edit_name;
    private EditText edit_pilot_point;
    private EditText edit_fighter_point;
    private EditText edit_trader_point;
    private EditText edit_engineer_point;
    private Spinner difficultySpinner;

    private Button save;
    private Player player;
    private TextView remaintext;
    private final int totalPoint = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_configuration);

        edit_name = findViewById(R.id.name_text);
        edit_pilot_point = findViewById(R.id.pilot_point_text);
        edit_fighter_point = findViewById(R.id.fighter_point_text);
        edit_trader_point = findViewById(R.id.trader_point_text);
        edit_engineer_point = findViewById(R.id.engineer_point_text);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        save = findViewById(R.id.save_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Player.validDifficulty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                String sPilot_point = edit_pilot_point.getText().toString();
                String sFighter_point = edit_fighter_point.getText().toString();
                String sTrader_point = edit_trader_point.getText().toString();
                String sEngineer_point = edit_engineer_point.getText().toString();


                if(name.trim().isEmpty()||sPilot_point.trim().isEmpty()||sFighter_point.trim().isEmpty()||sTrader_point.trim().isEmpty()||sEngineer_point.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "input field can not be blank", Toast.LENGTH_LONG).show();
                    return;
                }

                int nPilot_point = Integer.parseInt(sPilot_point);
                int nFighter_point = Integer.parseInt(sFighter_point);
                int nTrader_point = Integer.parseInt(sTrader_point);
                int nEngineer_point = Integer.parseInt(sEngineer_point);
                int sum = nFighter_point + nPilot_point + nTrader_point + nEngineer_point;
                String difficulty = difficultySpinner.getSelectedItem().toString();

                if (sum != 16) {
                    Toast.makeText(getApplicationContext(), "the sum of skill point should be 16", Toast.LENGTH_LONG).show();
                } else {
                    player = new Player(name, nPilot_point, nFighter_point, nTrader_point, nEngineer_point, difficulty);
//                    String showStat = player.toString();
//                    Toast.makeText(getApplicationContext(), showStat, Toast.LENGTH_LONG).show();
                    Log.d("UniverseSystem", player.toString());
                    openUniverse();
                }


            }
        });
    }

    // open Next page which is Universe
    public void openUniverse() {
        Intent intent = new Intent(this, CurrentPlanetActivity.class);
        startActivity(intent);
    }

}


