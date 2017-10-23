package com.hackheroes.lev.achiever.activites;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;

public class personalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        this.setCallbacks();
    }

    private void setCallbacks() {
        Button statsButton = (Button) findViewById(R.id.UserStatsButton);
        Button settingsButton = (Button) findViewById(R.id.SettingsButton);

        // set callback that will switch to user stats activity
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalActivity.this, statsActivity.class);
                startActivity(intent);
            }
        });

        //set callback that will switch to settings activity
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalActivity.this, settingsActivity.class);
                startActivity(intent);
            }
        });

        this.tabHost = (TabHost)findViewById(R.id.tabHost);
        this.tabHost.setup();

        if (tabHost == null)
            Toast.makeText(this, "tab host was null!", Toast.LENGTH_SHORT).show();
        else
        {
            // setup all the things that user can do in personal activity
            // by calling appropriate methods
            this.setupTodoBar();
            this.setupGoalsBar();
            this.setupAchievedBar();
            this.setupSummaryBar();
        }
    }
    private void setupTodoBar()
    {
        // setup todo bar
        TabHost.TabSpec spec = this.tabHost.newTabSpec("TODOs");
        spec.setIndicator(getString(R.string.tab1Name));
        spec.setContent(R.id.TODO);
        tabHost.addTab(spec);
    }
    private void setupGoalsBar()
    {
        // setup goals bar
        TabHost.TabSpec spec = this.tabHost.newTabSpec("Goals");
        spec.setIndicator(getString(R.string.tab2Name));
        spec.setContent(R.id.GOALS);
        tabHost.addTab(spec);

    }
    private void setupAchievedBar()
    {
        // setup achieved bar
        TabHost.TabSpec spec = this.tabHost.newTabSpec("Achieved");
        spec.setIndicator(getString(R.string.tab3Name));
        spec.setContent(R.id.ACHIEVED);
        tabHost.addTab(spec);

    }
    private void setupSummaryBar()
    {
        // don't have time for that feature :(

        // setup summary bar
        //TabHost.TabSpec spec = this.tabHost.newTabSpec("Summary");
        //spec.setIndicator(getString(R.string.tab4Name));
        //spec.setContent(R.id.SummaryTab);
        // tabHost.addTab(spec);


    }

    private TabHost tabHost;
}
