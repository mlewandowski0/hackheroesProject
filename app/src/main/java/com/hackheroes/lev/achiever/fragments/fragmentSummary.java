package com.hackheroes.lev.achiever.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackheroes.lev.achiever.R;

/**
 * Created by lev on 20.10.2017.
 */

public class fragmentSummary extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_summary, container,
                false);
        return view;
    }
}
