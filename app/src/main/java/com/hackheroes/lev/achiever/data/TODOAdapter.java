package com.hackheroes.lev.achiever.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.utilities.*;
import com.hackheroes.lev.achiever.data.dataManager;
import java.util.ArrayList;

/**
 * Created by lev on 21.10.2017.
 */

public class TODOAdapter extends ArrayAdapter<TODOData> implements View.OnClickListener
{
    public TODOAdapter(ArrayList<TODOData> data, Context context)
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
        TODOData data = getItem(position);
        controlsHolder viewHolder;
        final View result;

        if (convertView == null)
        {
            viewHolder = new controlsHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.todo_list_view_item, parent, false);

            viewHolder.textViewTask = (TextView)convertView.findViewById(R.id.TODOItemTaskStr);
            viewHolder.textViewGoal = (TextView)convertView.findViewById(R.id.TODOItemGoal);
            viewHolder.checkBoxDone = (CheckBox) convertView.findViewById(R.id.TODOItemCheckBox);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (controlsHolder) convertView.getTag();
            result = convertView;
        }

        try {
            viewHolder.textViewGoal.setText(data.getGoal());
            viewHolder.textViewTask.setText(data.getTask());
            viewHolder.checkBoxDone.setText(this.getContext().getResources().getString(R.string.TODOCheckBoxStr));

            final TODOAdapter adapter = this;
            // set some callback so the values will be removed from database
            // and transfered to achieved db
            viewHolder.checkBoxDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    // get controls from that item
                    final controlsHolder box = (controlsHolder)result.getTag();

                    // put some values into achieved and summary
                    // ....
                    TODOData item = adapter.getItem(position);
                    dataManager.addTODOToAchieved(item.getGoal(), item.getTask());
                    dataManager.addXP(10, true, false);

                    result.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // let's change checkbox state to false
                            box.checkBoxDone.setChecked(false);
                        }
                    }, getContext().getResources().getInteger(R.integer.delayUnselectingCheckBox));

                    // post delayed message [ the whole controls ( layout or whatever the android convention is ) is intepreted after some time ]
                    // to delay clicked control with that check box
                    result.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            // get data from item that we just have clicked
                            TODOData item = adapter.getItem(position);

                            // remove item ( really slow process)
                            adapter.remove(item);

                        }
                    }, getContext().getResources().getInteger(R.integer.timeMustPassToRemoveCheckBox));
                }
            });

        }
        catch (Exception e)
        {
            exceptionResponser.response(e);
        }

        return convertView;
    }


    class controlsHolder {
        TextView textViewGoal;
        TextView textViewTask;
        CheckBox checkBoxDone;
    }

    private Context context;
    private ArrayList<TODOData> dataset;
}
