package com.hackheroes.lev.achiever.data;

import java.util.List;

/**
 * Created by lev on 21.10.2017.
 */

public class GoalData
{
    public GoalData(String task, String why, List<TODOData> todoData,
                    String Date,String additionalNotes)
    {
        this.taskStr         = task;
        this.whyDoItStr      = why;
        this.additionalNotes = additionalNotes;
        this.todos           = todoData;
        this.date            = Date;
    }

    public List<TODOData> getTodos() {
        return todos;
    }

    public String getDate() {
        return date;
    }

    public String getTaskStr() {
        return taskStr;
    }

    public String getWhyDoItStr() {
        return whyDoItStr;
    }
    public String getAdditionalNotes() {
        return additionalNotes;
    }

    private String taskStr, whyDoItStr, additionalNotes;
    private List<TODOData> todos;
    private String date;
}
