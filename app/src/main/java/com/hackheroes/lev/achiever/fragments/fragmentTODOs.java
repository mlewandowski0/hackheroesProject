package com.hackheroes.lev.achiever.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.data.TODOAdapter;
import com.hackheroes.lev.achiever.data.TODOData;
import com.hackheroes.lev.achiever.utilities.*;
import java.util.ArrayList;
import com.hackheroes.lev.achiever.data.dataManager;

/**
 * Created by lev on 20.10.2017.
 */

public class fragmentTODOs extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.view = inflater.inflate(R.layout.fragment_todos, container,
                false);

        this.setupList();

        return view;
    }


    // method that will fill values from database
    private void setupList()
    {

        // setup database of TODOs
        // Proof of concept that it works !
        ArrayList<TODOData> dataset = dataManager.getTodoData();

        // lemme create an adapter so we have an amazing listView
        adapter = new TODOAdapter(dataset, this.view.getContext());

        this.listView = (ListView)this.view.findViewById(R.id.fragmentTODOSListView);
        if (listView != null) {
            // if db is empty we want to encourage user to add something
            if (dataset.size() == 0) {
                    // todo : a little bit debug text [ we should make some awesome info about setting some new goals]
                    // Toast.makeText(this.view.getContext(), "nope ", Toast.LENGTH_SHORT).show();
                    // TextView informer = new TextView(this.getContext());
                    // informer.setText(this.view.getResources().getString(R.string.fragmentNotMuchHereInfo));
                    // listView.addView(informer);

            } else {
                listView.setAdapter(adapter);
            }
        }
        else
            exceptionResponser.response(new appException("sth wrong with get list View at personal_TODO!", this.view.getContext()));
        dataManager.setAdapter(adapter);

    }
    public static TODOAdapter adapter;
    private View view;
    private ListView listView;

}
