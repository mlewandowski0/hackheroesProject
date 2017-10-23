package com.hackheroes.lev.achiever.activites;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.data.dataManager;

public class mainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.setupButtonsListeners();

        // initialization of singleton that handle all that data stuff [ because who cares ]
        dataManager.initialize(getResources(), getSharedPreferences(getResources().getString(R.string.preferencesKey),MODE_PRIVATE));
        this.sayForFirstTimeHiToUser();
    }

    // method that sets appropriate callbacks
    private void setupButtonsListeners()
    {
        // get context of android env
        final Context context = getApplicationContext();


        // get instance of "Personal version" button so we can
        // setup appropriate callback
        Button goPersonalVer = (Button)findViewById(R.id.goPersonal);

        // setup callback that switch to personal activity ( by anonymous function)
        goPersonalVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // make intent that switch to personal activity
                Intent switchToPersonal = new Intent(mainMenuActivity.this, personalActivity.class);

                startActivity(switchToPersonal);
            }
        });


        // get instance of "Community version" so we can
        // setup appropriate callback
        Button communityButton = (Button)findViewById(R.id.goCommunity);
        communityButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: debug message !
                        Toast.makeText(context, "Community version not yet supported", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // get instance of "settings button" so we can
        // setup appropriate callback
        ImageButton settingsButton = (ImageButton)findViewById(R.id.SettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make intent that will switch main menu activity to settings
                Intent intent = new Intent(mainMenuActivity.this, settingsActivity.class);

                startActivity(intent);
            }
        });

        // get instance of "stats button" so we can
        // setup appropriate callback
        Button    userStatsButton = (Button)findViewById(R.id.StatsButton);
        userStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make intent that will switch main menu activity to user stats
                Intent intent = new Intent(mainMenuActivity.this, statsActivity.class);

                startActivity(intent);
            }
        });

    }

    // method that is called when we know that is first launch of
    // application. Basically it says "hi" and it show how to use
    // this application ( some tutorial )
    private void sayForFirstTimeHiToUser()
    {
        Intent intent = new Intent(mainMenuActivity.this, welcomeNewUserActivity.class);
        startActivity(intent);
    }

}