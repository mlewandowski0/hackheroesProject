package com.hackheroes.lev.achiever.data;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.utilities.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by lev on 21.10.2017.
 */

public class addNewGoalAdapter extends ArrayAdapter<String> implements View.OnClickListener
{
    public addNewGoalAdapter(ArrayList<String> data, Context context)
    {
        super(context, R.layout.todo_list_view_item, data);
        this.dataset = data;
        this.context = context;
    }

    @Override
    public void onClick(View v)
    {
        int position       = (Integer)v.getTag();
        Object object      = getItem(position);
        TODOData dataOfObj =  (TODOData)object;

        switch (v.getId())
        {
            case R.id.TODOItemCheckBox:
                break;
        }
    }

    @Override
    public View getView(final int position , View convertView, final ViewGroup parent)
    {
        // get item data for position
        String data = getItem(position);
        controlsHolder  viewHolder;
        final View result;

        if (convertView == null)
        {
            viewHolder = new controlsHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.add_todo_to_goal_listview_item, parent, false);

            viewHolder.textViewTask = (TextView)convertView.findViewById(R.id.GoalTaskStr);
            viewHolder.removeTask   = (Button)  convertView.findViewById(R.id.Remove);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (controlsHolder) convertView.getTag();
            result = convertView;
        }

        try
        {
            viewHolder.textViewTask.setText(data);
            viewHolder.removeTask.setText(context.getResources().getString(R.string.SetNewGoalRemoveTODOStr));


            final addNewGoalAdapter adapter = this;
            // set some callback so the values will be removed from database
            // and transfered to achieved db
            viewHolder.removeTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    // get controls from that item
                    final controlsHolder box = (controlsHolder)result.getTag();

                    // get data item from adapter
                    String item = adapter.getItem(position);

                    // remove item from adapter
                    adapter.remove(item);
                }
            });

        }
        catch (Exception e)
        {
            exceptionResponser.response(e);
        }

        return convertView;
    }


    class controlsHolder
    {
        TextView textViewTask;
        Button   removeTask;
    }

    private Context context;
    private ArrayList<String> dataset;
}
