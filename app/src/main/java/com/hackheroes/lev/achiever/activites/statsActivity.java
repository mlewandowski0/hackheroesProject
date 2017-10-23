package com.hackheroes.lev.achiever.activites;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.utilities.appException;
import com.hackheroes.lev.achiever.utilities.exceptionResponser;
import com.hackheroes.lev.achiever.data.dataManager;

public class statsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        this.setViews();

        // call methods that will communicate with databases and load
        // values
        this.loadUserValuesAndAchivs();
        this.loadBadges();

    }

    // method that will set private variables ( pointers) to
    // instances of some views ( textViews etc.)
    private void setViews()
    {
        xpBar               = (ProgressBar)findViewById(R.id.progressBar);
        xpLevelLabel        = (TextView)findViewById(R.id.XPLevelStr);
        goalsAchievedLabel  = (TextView)findViewById(R.id.GoalsAchieved);
        todosDoneLabel      = (TextView)findViewById(R.id.TODOsDone);
    }



    // method that will add values loaded from shared preferences
    // ( level, experience, goals achieved, TODOs achieved ) and
    // load it to text views
    private void loadUserValuesAndAchivs()
    {
            int level = dataManager.getInteger(getResources().getString(R.string.level));
            if (level != -1)
                this.xpLevelLabel.append(" " + Integer.toString(level) + " " + getString(R.string.ExperienceLevel));

            int todosDone = dataManager.getInteger(getResources().getString(R.string.TODOsAchieved));
            if (todosDone != -1)
                this.todosDoneLabel.append(" "+ Integer.toString(todosDone) + " " + getString(R.string.TodosString));

            int goalsAchieved = dataManager.getInteger(getResources().getString(R.string.GoalsAchieved));
            if (goalsAchieved != -1)
                this.goalsAchievedLabel.append(" " + Integer.toString(goalsAchieved) + " " + getString(R.string.statsGoalsAchievedString));

            this.xpBar.setMax(dataManager.getInteger(getResources().getString(R.string.XPNeeded)));
            int userXP = dataManager.getInteger(getResources().getString(R.string.actualXP));

            if (userXP != -1)
                this.xpBar.setProgress(userXP, true);   // TODO: required API 24 so probably better remove this stuff in future
                                                        // and replace it by sth. that older APIs have


    }
    // method that will load all badges from sql database
    // so user can see what he achieved.

    private void loadBadges()
    {
        //TODO ! : create badges, achievments etc.
    }



    // Private Values
    private TextView           xpLevelLabel;
    private TextView           goalsAchievedLabel;
    private TextView           todosDoneLabel;
    private ProgressBar        xpBar;


}
