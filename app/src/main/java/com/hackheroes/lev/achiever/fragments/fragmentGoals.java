package com.hackheroes.lev.achiever.fragments;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.data.AchievedAdapter;
import com.hackheroes.lev.achiever.data.GoalAdapter;
import com.hackheroes.lev.achiever.data.TODOData;
import com.hackheroes.lev.achiever.activites.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.hackheroes.lev.achiever.data.dataManager;

/**
 * Created by lev on 20.10.2017.
 */


public class fragmentGoals extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.view = inflater.inflate(R.layout.fragment_goals, container,
                false);

        this.setupExpandable();

        return view;
    }

    // method that will setup expandable list
    private void setupExpandable()
    {
        // let's save references to our controls
        FloatingActionButton startNewGoal       = (FloatingActionButton)this.view.findViewById(R.id.fragmentGoalsAddNewGoal);
        this.expandableListView                 = (ExpandableListView)this.view.findViewById(R.id.fragmentGoalsExpandable);

        startNewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zero-out values in fragmentGoals
                fragmentGoals.taskStr = fragmentGoals.whyIWantThisStr = fragmentGoals.extraNotesStr = "";
                fragmentGoals.todos   = new ArrayList<>();

                // start new intent so it will switch to dialog
                // in which user can add new goal
                Intent intent = new Intent(getActivity(), addNewGoalDial.class);
                startActivity(intent);
            }
        });

        listDataHeader = dataManager.getGoalHeaderData();
        listDataChild  = dataManager.getGoalContentData();

        // creating new instance of adapter
        GoalAdapter adapter = new GoalAdapter(this.expandableListView.getContext(), listDataHeader, listDataChild);
        dataManager.setAdapter(adapter);

        // setting adapter
        this.expandableListView.setAdapter(adapter);
    }

    // rather shitty solution but dang man, it's like 2:49 AM so i really
    // don't want to mess with some other solutions ( screw databases and other stuff)
    public static String    taskStr, whyIWantThisStr, extraNotesStr;
    public static Calendar  startingDate, finishingDate;
    public static ArrayList<TODOData> todos;


    private View view;
    private ExpandableListView expandableListView;
    private ArrayList<String>  listDataHeader;
    private HashMap<String, List<String>> listDataChild;
}
