package com.hackheroes.lev.achiever.activites;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.EditText;
import android.widget.TextView;
import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.data.dataManager;
import com.hackheroes.lev.achiever.fragments.fragmentGoals;

public class addNewGoalDial extends AppCompatActivity implements View.OnClickListener
{

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(addNewGoalDial.this, todoEdtorActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal_dial);
        this.setTitle(getResources().getString(R.string.dialogName));

        this.setupControls();
    }
    private void setupControls()
    {
        // get the controls
        this.whatToDo                    = (EditText)findViewById(R.id.answerWhatToDo);
        this.whyDoIt                     = (EditText)findViewById(R.id.answerWhyDoThat);
        this.additionalNotes             = (EditText)findViewById(R.id.additionalNotes);
        this.approveGoalButton           = (Button)findViewById  (R.id.addNewGoalButton);
        this.editDateButton              = (Button)findViewById  (R.id.answerSetDate);
        this.addTodoButton               = (Button)findViewById  (R.id.addTodoButton);
        this.actualMarkedData            = (TextView)findViewById(R.id.actualDateTextView);
         context                          = getApplicationContext();

        // by default the button is disabled
        this.approveGoalButton.setEnabled(false);

        rightNow = Calendar.getInstance();
        actualMarkedData.setText(Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH))
                                 + "-" + Integer.toString(rightNow.get(Calendar.MONTH) + 1)
                                 + "-" +  Integer.toString(rightNow.get(Calendar.YEAR)));
        todosPlannedArray = new ArrayList<>();
        this.setupButtonsCallbacks();
        this.setTextWatchers();
    }
    private void setupButtonsCallbacks()
    {
        // save pointer to our class
        final addNewGoalDial thisActivity = this;

        // set callback to button that will commit our goal to database
        this.approveGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // get the string from textEdits and put it to the singleton
                String text = whatToDo.getText().toString();
                dataManager.getGoalHeaderData().add(text);

                // add data about goals !
                List<String> list = new ArrayList<>();
                list.add(whyDoIt.getText().toString());
                list.add(actualMarkedData.getText().toString());
                list.add(additionalNotes.getText().toString());
                dataManager.getGoalContentData().put(text,list);

                dataManager.clearTodosPlannedArray(text);
                dataManager.notifyOnDataChange();

                dataManager.addXP(5, false, true);

                // add data to database
                finish();
            }
        });

        // set callback to button that will open edit date dialog
        this.editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(thisActivity,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selYear, int selMonth, int selDayOfMonth) {
                                day = selDayOfMonth;
                                month = selMonth;
                                year = selYear;
                                actualMarkedData.setText(Integer.toString(day) + "-" + Integer.toString(month + 1)
                                        + "-" + Integer.toString(year));
                            }
                        },
                        rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH),
                        rightNow.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
    }
        });
        // set callback to button that will add will open dialog where we can modify todo
        this.addTodoButton.setOnClickListener(this);
    }
    public void setTextWatchers()
    {
        final addNewGoalDial thisAct = this;

        // set text listeners
        whatToDo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                thisAct.enableButtonIfNecessary();
            }
        });
        whyDoIt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                thisAct.enableButtonIfNecessary();
            }
        });
    }
    public void enableButtonIfNecessary()
    {
        if     (!approveGoalButton.isEnabled() && whatToDo.getText().length() != 0
                && whyDoIt.getText().length() != 0)
            approveGoalButton.setEnabled(true);

        else if (approveGoalButton.isEnabled() && whatToDo.getText().length() == 0)
            approveGoalButton.setEnabled(false);
    }
    // bunch of controls ( text edits) that are necessary to add goal
    private TextView     actualMarkedData;
    private EditText     whatToDo, whyDoIt, additionalNotes;
    private Button       approveGoalButton, editDateButton, addTodoButton;
    private int          day, month, year;
    private Calendar     rightNow = Calendar.getInstance();
    private Context      context;

    static public ArrayList<String> todosPlannedArray;
}
