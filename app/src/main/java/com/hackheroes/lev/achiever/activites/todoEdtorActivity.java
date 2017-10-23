package com.hackheroes.lev.achiever.activites;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.hackheroes.lev.achiever.data.addNewGoalAdapter;
import com.hackheroes.lev.achiever.R;
import com.hackheroes.lev.achiever.data.dataManager;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class todoEdtorActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    public void onClick(View v)
    {
        if (textEdit.getText().toString().length() != 0) {
            data.add(textEdit.getText().toString());
            this.adapter.notifyDataSetChanged();
        }
        textEdit.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_edtor);

        // setup list view
        todoEditorListView = (ListView)findViewById(R.id.todoEditorListVIew);
        final Button    todoEditorButton   = (Button)findViewById(R.id.todoEditorAddNewTodo);
        this.textEdit  = (EditText)findViewById(R.id.TODOEditorTextEdit);

        // get data
        data = dataManager.getTodosPlannedArray();

        // setup adapter
        this.adapter = new addNewGoalAdapter(data, todoEditorListView.getContext());
        this.todoEditorListView.setAdapter(adapter);
        todoEditorButton.setOnClickListener(this);

        // by default button is disabled
        todoEditorButton.setEnabled(false);

        //setup text watcher
        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s)
            {
                if (textEdit.getText().length() != 0 && !todoEditorButton.isEnabled())
                    todoEditorButton.setEnabled(true);
                else if (todoEditorButton.isEnabled() && textEdit.getText().length() == 0)
                    todoEditorButton.setEnabled(false);
            }
        });

    }
    addNewGoalAdapter adapter;
    ListView          todoEditorListView;
    EditText          textEdit;
    ArrayList<String> data;
}
