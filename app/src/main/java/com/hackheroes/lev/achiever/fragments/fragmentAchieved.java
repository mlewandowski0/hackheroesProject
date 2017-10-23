package com.hackheroes.lev.achiever.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.hackheroes.lev.achiever.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hackheroes.lev.achiever.data.AchievedAdapter;
import com.hackheroes.lev.achiever.data.dataManager;
import com.hackheroes.lev.achiever.data.GoalAdapter;

/**
 * Created by lev on 20.10.2017.
 */

public class fragmentAchieved extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_achieved, container,
                false);

        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.fragmentAchievedExpandable);


        ArrayList<String>             listDataHeader = dataManager.getAchievedNameOfGoals();
        HashMap<String, List<String>> listDataChild  = dataManager.getAchievedTODOs();

        // creating new instance of adapter
        AchievedAdapter adapter = new AchievedAdapter(listView.getContext(), listDataHeader, listDataChild);
        dataManager.setAdapter(adapter);

        // setting adapter
        listView.setAdapter(adapter);

        return view;
    }
}
